package com.excel.data.helper;

import com.excel.data.model.Employee;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static boolean CheckExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {

            System.out.println("return True");
            return true;

        } else {
            return false;
        }

    }

    public static List<Employee> ConvertExcelToListFormat(InputStream inputStream) {
        List<Employee> employees = new ArrayList<>();

        try {

            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("100 Record");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();
                Employee emp = new Employee();
                int cid = 0;
                while (cells.hasNext()) {


                    Cell cell = cells.next();

                    switch (cid) {
                        case 0:
                            emp.setEmpId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            emp.setNamePrefix(cell.getStringCellValue());
                            break;
                        case 2:
                            emp.setFirstName(cell.getStringCellValue());
                            break;
                        case 3:
                            emp.setLastName(cell.getStringCellValue());
                            break;
                        case 4:
                            emp.setGender(cell.getStringCellValue());
                            break;
                        case 5:
                            emp.setEmail(cell.getStringCellValue());
                            break;
                        case 6:
                            emp.setFatherName(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                employees.add(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return employees;

    }
}
