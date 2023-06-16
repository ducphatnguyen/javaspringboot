package com.example.demo.resource;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dto.base.PostResponseBaseDto;
import com.example.demo.dto.request.AddCategoryDto;
import com.example.demo.dto.request.EditCategoryDto;
import com.example.demo.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api")
public class DemoResource {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ReportService reportService;

    @GetMapping("/category")
    public ResponseEntity listCategory(){
        return ResponseEntity.ok().body(categoryDao.listCategory());
    }

    @PostMapping("/category")
    public ResponseEntity addCategory(@RequestBody AddCategoryDto request){
        categoryDao.addCategory(request.getName());
        return ResponseEntity.ok().body(
                PostResponseBaseDto.builder().message("Thêm thành công").statusCode(HttpStatus.OK.value()).build());
    }

    @PutMapping("/category")
    public ResponseEntity editCategory(@RequestBody EditCategoryDto request){
        categoryDao.editCategory(request.getId(), request.getName());
        return ResponseEntity.ok().body(
                PostResponseBaseDto.builder().message("Sửa thành công").statusCode(HttpStatus.OK.value()).build());
    }

    @DeleteMapping("/category")
    public ResponseEntity deleteCategory(@RequestParam int id){
        categoryDao.deleteCategory(id);
        return ResponseEntity.ok().body(
                PostResponseBaseDto.builder().message("Xoá thành công").statusCode(HttpStatus.OK.value()).build());
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return reportService.exportReport(format);
    }
}
