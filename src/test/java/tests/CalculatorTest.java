package tests;

import com.google.gson.Gson;
import com.unoveo.securityjwt.calculator.CalServlet1;
import com.unoveo.securityjwt.calculator.ExpressionStack;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

        @Test
        void testCalculateAndDoCalculate() throws IOException {
            FileInputStream fileInputStream = new FileInputStream("C:\\Projects\\UNO Projects\\Web Applications\\backend\\React Backend\\React Calculator with security and jwt backend\\react-cal-backend\\react-cal-backend\\test2.xlsx");
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                double expectedResult = row.getCell(2).getNumericCellValue();

                // Parse requestBody into ExpressionStack[] (Assuming Gson is used for deserialization)
                String requestBody = row.getCell(1).getStringCellValue();
                Gson gson = new Gson();
                ExpressionStack[] expressions = gson.fromJson(requestBody, ExpressionStack[].class);

                // Calculate using CalServlet
                CalServlet1 calServlet = new CalServlet1();
                double calculatedResult = calServlet.calculate(expressions);

                // Assertions
                assertEquals(expectedResult, calculatedResult, 0.01); // Add a delta for double comparison
            }

            workbook.close();
            fileInputStream.close();
        }
    }


