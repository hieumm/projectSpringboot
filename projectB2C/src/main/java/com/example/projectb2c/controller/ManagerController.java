package com.example.projectb2c.controller;


import com.example.projectb2c.entity.Category;
import com.example.projectb2c.entity.Chart;
import com.example.projectb2c.entity.Product;
import com.example.projectb2c.entity.Review;
import com.example.projectb2c.repository.CategoryRepository;
import com.example.projectb2c.repository.ProductRepository;
import com.example.projectb2c.repository.ReviewRepository;
import com.example.projectb2c.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
//chức năng quản lý
@Controller
@MultipartConfig
public class ManagerController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
@Autowired
    ReviewRepository reviewRepository;
    //hiển thị thông tin sản phẩm
    @GetMapping("/manager")
    public String list(Model model) {
        List<Product> list = (List<Product>) productRepository.findAll();
        model.addAttribute("listPro", list);
        List<Category> listCate = (List<Category>) categoryRepository.findAll();
        model.addAttribute("listCate", listCate);
        return "dashboard";
    }

    //xóa sản phẩm
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/manager";
    }

    //update sản phẩm
    @GetMapping("/update")
    public ModelAndView update(Model model, @RequestParam("id") Long id) {
        List<Category> listCate = (List<Category>) categoryRepository.findAll();
        model.addAttribute("listCate", listCate);
        ModelAndView mav = new ModelAndView("detail");
        Product product = productRepository.findById(id).get();
        mav.addObject("pro", product);
        return mav;
    }


    @GetMapping("/addcategory")
    public String addCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/manager";
    }

    //add product
    @Value("${config.upload_folder}")
    String UPLOAD_FOLDER;
    @PostMapping("addpro")
    public String addPro(@ModelAttribute("obj") Product product, @RequestParam("pic1") MultipartFile file1, @RequestParam("pic2") MultipartFile file2) {
        String relativeFilePath1 = null;
        String relativeFilePath2 = null;
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getDayOfMonth();
        String subFolder = month + "_" + year + "/";
        String fullUploadDir = UPLOAD_FOLDER + subFolder;
        File checkDir = new File(fullUploadDir);
        if (!checkDir.exists() || checkDir.isFile()) {
            checkDir.mkdir();

        }
        try {
            relativeFilePath1 = subFolder + Instant.now().getEpochSecond() + file1.getOriginalFilename();
            Files.write(Paths.get(UPLOAD_FOLDER + relativeFilePath1), file1.getBytes());
            relativeFilePath2 = subFolder + Instant.now().getEpochSecond() + file2.getOriginalFilename();
            Files.write(Paths.get(UPLOAD_FOLDER + relativeFilePath2), file2.getBytes());
            product.setImg1(relativeFilePath1);
            product.setImg2(relativeFilePath2);
        } catch (IOException e) {
            System.out.println("ko upload duoc");
            e.printStackTrace();
        }

        productRepository.save(product);
        return "redirect:/manager";
    }

    //thống kê
    @GetMapping("/displayBarGraph")
    public String barGraph(Model model) {
        Map<String, Long> surveyMap = new LinkedHashMap<>();
        List<Chart> listCateCount = productRepository.sumOfCategory();
        for (Chart c : listCateCount) {
            surveyMap.put(c.getNameCate(), c.getCounByCate());
        }
        model.addAttribute("surveyMap", surveyMap);
        return "bargraph";
    }
    @GetMapping("/displayPieChart")
    public String pieChart(Model model) {
        //List<Product> pieChartData = productRepository.countProductByCategory();
        List<Review> listRate = reviewRepository.findAll();
        int hight = 0, low=0;
        for (Review review:listRate) {
            if (review.getRate()>3){
                hight+=review.getRate();
            }else {
                low+=review.getRate();
            }
        }
        int perHight=(hight*100)/(hight+low);
        int perLow=100-perHight;
       // model.addAttribute("pieChartData", pieChartData);
      model.addAttribute("pass", perHight);
        model.addAttribute("fail", perLow);
        return "pieChart";
    }
//export to excel
@GetMapping("/export")
public void exportToExcel(HttpServletResponse response) throws IOException {
    response.setContentType("application/octet-stream");
    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    String currentDateTime = dateFormatter.format(new Date());

    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
    response.setHeader(headerKey, headerValue);

    List<Product> productList =productRepository.findAll();

    UserExcelExporter excelExporter = new UserExcelExporter(productList);

    excelExporter.export(response);
}
@Autowired
UserRepository userRepository;
//
//gửi mail thông báo
//long start = System.currentTimeMillis();
//    @Autowired
//    DefaulService defaulService;
//    @GetMapping("/sendmail")
//    public String getform(){
//        return "sendEMail";
//    }
//    //  @GetMapping("/forgot-password")
//    @PostMapping("/forgot")
//    public String forgotPassword(@RequestParam("notes") String note, Model model) {
//        ArrayList<UserEntity> arrayList= (ArrayList<UserEntity>) userRepository.findAll();
//
//        System.out.println(note);
//        for (UserEntity email : arrayList) {
//            defaulService.sendMailAll(email.getUsername(),note);
//        }
//        // some time passes
//        long end = System.currentTimeMillis();
//        long elapsedTime = end - start;
//
//        System.out.println(elapsedTime);
//        return "sendEMail";
//    }
}

