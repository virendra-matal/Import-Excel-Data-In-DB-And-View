package com.excel.data.Service;

import com.excel.data.Dao.EmployeeRepo;
import com.excel.data.helper.ExcelHelper;
import com.excel.data.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public void SaveExcelFile(MultipartFile file) throws IOException {
        List<Employee> employees = ExcelHelper.ConvertExcelToListFormat(file.getInputStream());
        this.employeeRepo.saveAll(employees);


    }


    public List<Employee> GetAllEmployee(){
        return employeeRepo.findAll();
    }


}
