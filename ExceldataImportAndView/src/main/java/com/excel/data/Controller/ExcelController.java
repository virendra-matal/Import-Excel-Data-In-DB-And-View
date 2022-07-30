package com.excel.data.Controller;

import com.excel.data.Service.ExcelService;
import com.excel.data.helper.ExcelHelper;
import com.excel.data.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequestMapping("/excel")
@RestController
@CrossOrigin(origins = "*")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<?> SaveExcel(@RequestParam("file")MultipartFile file) throws IOException, InterruptedException {
        Thread.sleep(1000);
        System.out.println("File is : "+file.getOriginalFilename());
        if (ExcelHelper.CheckExcelFormat(file)){

            this.excelService.SaveExcelFile(file);
            return ResponseEntity.ok(Map.of("Message","File is stored successfully !!!"));

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload file In Excel formate... Thank-You !!!");
    }

    @GetMapping("/employees")
    public List<Employee> Getall(){
        return  this.excelService.GetAllEmployee();
    }

}
