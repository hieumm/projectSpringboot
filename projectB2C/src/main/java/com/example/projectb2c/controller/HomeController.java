package com.example.projectb2c.controller;


import com.example.projectb2c.entity.*;
import com.example.projectb2c.repository.CategoryRepository;
import com.example.projectb2c.repository.ProductRepository;
import com.example.projectb2c.repository.ReviewRepository;
import com.example.projectb2c.service.ShoppingCartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

//trang hiển thị cho người dùng
@Controller
@Scope("session")
public class HomeController {
    @Autowired
    ShoppingCartServiceImpl shoppingCartService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ReviewRepository reviewRepository;

    //hiển thị giao diện bán hàng có phân trang
    @GetMapping("/checkpage")
    public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 2);
        Page<Product> page = productRepository.findAll(pageable);
        int totalPages = page.getTotalPages();
        int pre;
        if (page.getNumber() == 0) {
            pre = 0;
        } else {
            pre = page.getNumber() - 1;
        }
        int next;
        if (page.getNumber() < totalPages - 1) {
            next = page.getNumber() + 1;
        } else {
            next = totalPages - 1;
        }
        model.addAttribute("pageNumbers", totalPages - 1);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);

        model.addAttribute("listPro", page);
        List<Category> listCate = (List<Category>) categoryRepository.findAll();
        model.addAttribute("listCate", listCate);
        List<Chart> listCateCount = productRepository.sumOfCategory();

        model.addAttribute("listCateCount", listCateCount);

        return "ad-list-view";
    }

    //tìm kiếm sản phẩm theo tên có phân trang
    @GetMapping("/findbyname")
    public String findbyname(Model model, @RequestParam("namepro") String name, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 2);
        Page<Product> page = productRepository.findProductByProductName(pageable, name);
        int totalPages = page.getTotalPages();
        int pre;
        if (page.getNumber() == 0) {
            pre = 0;
        } else {
            pre = page.getNumber() - 1;
        }
        int next;
        if (page.getNumber() < totalPages - 1) {
            next = page.getNumber() + 1;
        } else {
            next = totalPages - 1;
        }
        model.addAttribute("pageNumbers", totalPages - 1);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);

        model.addAttribute("listPro", page);
        List<Category> listCate = (List<Category>) categoryRepository.findAll();
        model.addAttribute("listCate", listCate);
        List<Chart> listCateCount = productRepository.sumOfCategory();
        model.addAttribute("listCateCount", listCateCount);

        return "ad-list-view";
    }

    //hiển thị sản phẩm theo combobox category có phân trang
    @GetMapping("/loadbycate")
    public String loadByCate(Model model, @RequestParam("id") Long cateId, @RequestParam("p") Optional<Integer> p) {

        Pageable pageable = PageRequest.of(p.orElse(0), 2);
        Page<Product> page = productRepository.findProductByCategoryId(pageable, cateId);
        int totalPages = page.getTotalPages();
        int pre;
        if (page.getNumber() == 0) {
            pre = 0;
        } else {
            pre = page.getNumber() - 1;
        }
        int next;
        if (page.getNumber() < totalPages - 1) {
            next = page.getNumber() + 1;
        } else {
            next = totalPages - 1;
        }
        model.addAttribute("pageNumbers", totalPages - 1);
        model.addAttribute("pre", pre);
        model.addAttribute("next", next);

        model.addAttribute("listPro", page);
        List<Category> listCate = (List<Category>) categoryRepository.findAll();
        model.addAttribute("listCate", listCate);
        List<Chart> listCateCount = productRepository.sumOfCategory();
        model.addAttribute("listCateCount", listCateCount);
        return "ad-list-view";
    }
   /* @GetMapping("/displayListCount")
    public String barGraph(Model model) {
       return "ad-list-view";
    }*/

    //hiển thị thông tin sản phẩm
    @GetMapping("/updatesale")
    public ModelAndView update(Model model, @RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("single");
        Product product = productRepository.findById(id).get();
        mav.addObject("pro", product);
        //hiển thị số lượng star trong form đánh giá
        Integer[] liststart = new Integer[]{1, 2, 3, 4, 5, 6};
        model.addAttribute("liststart", liststart);
        //load data review về sản phẩm
        /*Review listReview = (Review) reviewRepository.findAllByProduct(product.getId());
        mav.addObject("listReview", listReview);*/
        return mav;

    }

    //hiển thị giỏ hàng
    @GetMapping("viewcart")
    public String viewCart(Model model) {
        model.addAttribute("cartitem", shoppingCartService.getAll());
        model.addAttribute("totalmoney", shoppingCartService.getAmount());
        return "viewcart";
    }

    //thêm sản phẩm vào giỏ hàng
    @GetMapping("addCart")
    public String addCart(@RequestParam("id") Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product != null) {
            CartProduct item = new CartProduct();
            item.setProductId(Math.toIntExact(product.get().getId()));
            item.setName(product.get().getProductName());
            item.setPrice(product.get().getPrice());
            shoppingCartService.add(item);

        }
        return "redirect:/viewcart";
    }

    //xóa sản phẩm trong giỏ hàng
    @GetMapping("delCart")
    public String deleteCart(@RequestParam("id") Integer id) {
        shoppingCartService.remove(id);
        return "redirect:/viewcart";
    }

    //xóa giỏ hàng
    @GetMapping("clear-cart")
    public String clearCart() {
        shoppingCartService.clear();
        return "redirect:/viewcart";
    }

    //review product
    @GetMapping("/reviewpro")
    public String reviewProduct(Review review) {
        reviewRepository.save(review);
        return "redirect:/checkpage";
    }

    @PostMapping("/sendHoaDonAndReview")
    public String sendHoaDonAndReview() {
        return "single";
    }
  /*  @Autowired
    DefaulService defaulService;

    @PostMapping("/sendHoaDon")
    public String forgotPassword(@RequestParam("money") String money, Model model, HttpServletRequest request) {

       String email= (String) request.getAttribute("userName");
        defaulService.sendThanhToan( "maiminhhieu1999@gmail.com", Double.parseDouble(money));
        return "home";
    }*/
}