package com.example.demo.service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dto.CategoryDto;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private CategoryDao categoryDao;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        List<Map<String, Object>> categories = categoryDao.listCategory();
        File file = ResourceUtils.getFile("classpath:categories.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(categories);
        Map<String, Object> paramaters = new HashMap<>();
        paramaters.put("createdBy", "Demo");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramaters, dataSource);
        String path = "/Users/user/Downloads/demo/src/main/resources/templates";
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "/categories.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/categories.pdf");
        }

            return "path: " + path;
    }
}
