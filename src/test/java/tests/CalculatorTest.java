//package tests;
//
//import com.google.gson.Gson;
//import com.unoveo.securityjwt.calculator.CalServlet1;
//import com.unoveo.securityjwt.calculator.ExpressionStack;
//        import jakarta.servlet.ServletException;
//        import jakarta.servlet.http.HttpServletRequest;
//        import jakarta.servlet.http.HttpServletResponse;
//        import org.apache.poi.ss.usermodel.*;
//        import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//        import org.junit.jupiter.api.BeforeEach;
//        import org.junit.jupiter.api.Test;
//        import org.mockito.Mockito;
//
//        import java.io.*;
//        import java.util.ArrayList;
//        import java.util.List;
//
//        import static org.junit.jupiter.api.Assertions.assertEquals;
//        import static org.mockito.Mockito.when;
//
//public class CalculatorTest {
//
//    private CalServlet1 servlet;
//    private HttpServletRequest request;
//    private HttpServletResponse response;
//
//    @BeforeEach
//    public void setUp() {
//        servlet = new CalServlet1();
//        request = Mockito.mock(HttpServletRequest.class);
//        response = Mockito.mock(HttpServletResponse.class);
//    }
//
//    @Test
//    public void testExpressionsFromExcel() throws IOException, ServletException {
//        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\neha bawiskar\\OneDrive\\Desktop\\FinalJwt\\react-cal-backend\\test1.xlsx");
//        Workbook workbook = new XSSFWorkbook(fileInputStream);
//        Sheet sheet = workbook.getSheetAt(0);
//
//        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//            Row row = sheet.getRow(i);
//            Cell expressionsCell = row.getCell(2);
//            Cell resultCell = row.getCell(3);
//
//            String expressionsJson = expressionsCell.getStringCellValue();
//            double expectedResult = resultCell.getNumericCellValue();
//
//            ExpressionStack expressions = new ExpressionStack();
//
//            // Mock the HttpServletRequest and HttpServletResponse
//            when(request.getReader()).thenReturn(new BufferedReader(new StringReader(expressionsJson)));
//            StringWriter stringWriter = new StringWriter();
//            PrintWriter writer = new PrintWriter(stringWriter);
//            when(response.getWriter()).thenReturn(writer);
//            expressions.calculate(number1,number2,operator);
//            // Call the servlet doPost method
//            servlet.doPost(request, response);
//            writer.flush();
//
//            // Compare the calculated result with the expected result
//            assertEquals(expectedResult, Double.parseDouble(stringWriter.toString().trim()));
//        }
//
//        workbook.close();
//        fileInputStream.close();
//    }
//
//    @Test
//    public void testCalculate() {
//
//        ExpressionStack expressionStack = new ExpressionStack();
//        double result = expressionStack.calculate(number1,number2, operator);
//
//        // Expected result from adding 5 and 3
//        double expectedResult = 8.0;
//
//        // Compare the calculated result with the expected result
//        assertEquals(expectedResult, result);
//    }
//}
//


//import com.unoveo.securityjwt.calculator.ExpressionStack;
//import org.apache.poi.EncryptedDocumentException;
//import org.apache.poi.ss.usermodel.*;
//import org.junit.jupiter.api.Test;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class ExpressionStackTest {
//
//    @Test
//    public void testCalculateMethodWithExcelData() {
//        String excelFilePath = "C:\\Users\\neha bawiskar\\OneDrive\\Desktop\\FinalJwt\\react-cal-backend\\test1.xlsx"; // Replace with the actual path to your Excel file
//
//        try (FileInputStream inputStream = new FileInputStream(excelFilePath);
//             Workbook workbook = WorkbookFactory.create(inputStream)) {
//
//            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
//
//            for (Row row : sheet) {
//                Cell firstNumberCell = row.getCell(0);
//                Cell operatorCell = row.getCell(1);
//                Cell secondNumberCell = row.getCell(2);
//                Cell expectedResultCell = row.getCell(3);
//
//                double firstNumber = firstNumberCell.getNumericCellValue();
//                String operator = operatorCell.getStringCellValue();
//                double secondNumber = secondNumberCell.getNumericCellValue();
//                double expected = expectedResultCell.getNumericCellValue();
//
//                ExpressionStack expressionStack = new ExpressionStack();
//                double actualResult = expressionStack.calculate(firstNumber, secondNumber, operator);
//
//                assertEquals(expected, actualResult, 0.001); // Tolerance for double comparison
//            }
//
//        } catch (IOException | EncryptedDocumentException ex) {
//            ex.printStackTrace();
//        }
//    }
//}

//import com.google.gson.Gson;
//import com.unoveo.securityjwt.calculator.ExpressionStack;
//import org.apache.poi.ss.usermodel.*;
//import org.junit.jupiter.api.Test;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class CalServlet1Test {
//
//    @Test
//    void testExpressionEvaluationFromExcel() throws IOException {
//        String excelFilePath = "C:\\Users\\neha bawiskar\\OneDrive\\Desktop\\FinalJwt\\react-cal-backend\\test1.xlsx";
//        FileInputStream inputStream = new FileInputStream(excelFilePath);
//        Workbook workbook = WorkbookFactory.create(inputStream);
//        Sheet sheet = workbook.getSheetAt(0); // Assuming you are reading from the first sheet (index 0)
//
//        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {

//            Row row = sheet.getRow(rowIndex);
//            Cell expressionCell = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//            Cell expectedRe
//            sultCell = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//
//            String expressionJson = expressionCell.getStringCellValue();
//            double expectedResult = expectedResultCell.getNumericCellValue();
//
//            Gson gson = new Gson();
//            ExpressionStack[] expressions = gson.fromJson(expressionJson, ExpressionStack[].class);
//            double result = evaluateExpression(expressions);
//
//            assertEquals(expectedResult, result);
//        }
//
//        inputStream.close();
//    }
//
//    private double evaluateExpression(ExpressionStack[] expressions) {
//        ExpressionStack expressionStack = new ExpressionStack();
//        double result = 0;
//
//        for (ExpressionStack expression : expressions) {
//            if ("NUMBER".equals(expression.getType())) {
//                double value = Double.parseDouble(expression.getValue());
//                if (result == 0) {
//                    result = value;
//                } else {
//                    throw new IllegalStateException("Invalid expression format.");
//                }
//            } else if ("OPERATOR".equals(expression.getType())) {
//                String operator = expression.getValue();
//              double resultIndex = 0;
//                double nextNumber = Double.parseDouble(expressions[(int) ++resultIndex].getValue());
//                result = expressionStack.calculate(result, nextNumber, operator);
//            } else {
//                throw new IllegalStateException("Invalid expression type.");
//            }
//        }
//
//        return result;
//    }
//}
//
//import com.google.gson.Gson;
//        import com.unoveo.securityjwt.calculator.CalServlet1;
//        import com.unoveo.securityjwt.calculator.ExpressionStack;
//        import jakarta.servlet.ServletException;
//        import jakarta.servlet.http.HttpServletRequest;
//        import jakarta.servlet.http.HttpServletResponse;
//        import org.apache.poi.ss.usermodel.*;
//        import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//        import org.junit.jupiter.api.BeforeEach;
//        import org.junit.jupiter.api.Test;
//        import org.mockito.Mockito;
//
//        import java.io.*;
//        import java.util.ArrayList;
//        import java.util.List;
//
//        import static org.junit.jupiter.api.Assertions.*;
//        import static org.mockito.Mockito.when;
//
//public class CalculatorTest {
//
//    private CalServlet1 servlet;
//    private HttpServletRequest request;
//    private HttpServletResponse response;
//
//    @BeforeEach
//    public void setUp() {
//        servlet = new CalServlet1();
//        request = Mockito.mock(HttpServletRequest.class);
//        response = Mockito.mock(HttpServletResponse.class);
//    }
//
//    @Test
//    public void testExpressionsFromExcel() throws IOException, ServletException {
//        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\neha bawiskar\\OneDrive\\Desktop\\FinalJwt\\react-cal-backend\\test1.xlsx");
//        Workbook workbook = new XSSFWorkbook(fileInputStream);
//        Sheet sheet = workbook.getSheetAt(0);
//
//        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//            Row row = sheet.getRow(i);
//            Cell expressionsCell = row.getCell(1);
//            Cell resultCell = row.getCell(2);
//
//            String expressionsJson = expressionsCell.getStringCellValue();
//            double expectedResult = resultCell.getNumericCellValue();
//
//            ExpressionStack[] expressions = new Gson().fromJson(expressionsJson, ExpressionStack[].class);
//
//            // Mock the HttpServletRequest and HttpServletResponse
//            when(request.getReader()).thenReturn(new BufferedReader(new StringReader(expressionsJson)));
//            String StringWriter = null;
//            PrintWriter writer = new PrintWriter(StringWriter);
//            when(response.getWriter()).thenReturn(writer);
//
//            // Call the servlet doPost method
//            servlet.doPost(request, response);
//            writer.flush();
//
//            // Compare the calculated result with the expected result
//            assertEquals(expectedResult, Double.parseDouble(writer.toString().trim()));
//        }
//
//        workbook.close();
//        fileInputStream.close();
//    }
//
//    private double calculateExpression(ExpressionStack[] expressions) {
//        if (expressions == null || expressions.length < 3) {
//            throw new IllegalArgumentException("Invalid expression format");
//        }
//
//        double result = Double.parseDouble(expressions[0].getValue());
//
//        for (int i = 1; i < expressions.length; i += 2) {
//            String operator = expressions[i].getValue();
//            double operand = Double.parseDouble(expressions[i + 1].getValue());
//
//            switch (operator) {
//                case "+":
//                    result += operand;
//                    break;
//                case "-":
//                    result -= operand;
//                    break;
//                case "*":
//                    result *= operand;
//                    break;
//                case "/":
//                    if (operand != 0) {
//                        result /= operand;
//                    } else {
//                        throw new ArithmeticException("Division by zero is not allowed");
//                    }
//                    break;
//                default:
//                    throw new IllegalArgumentException("Invalid operator: " + operator);
//            }
//        }
//
//        return result;
//    }
//}