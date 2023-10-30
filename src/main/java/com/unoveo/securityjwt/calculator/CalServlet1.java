package com.unoveo.securityjwt.calculator;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/calServlet1")
public class CalServlet1 extends HttpServlet {

    @Override
    @CrossOrigin
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter();
        StringBuilder requestBody = new StringBuilder();
        String line = "";
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }

        Gson gson = new Gson();
        ExpressionStack[] expressions = gson.fromJson(requestBody.toString(), ExpressionStack[].class);
        List<Double> numbers = new ArrayList<>();
        List<String> operators = new ArrayList<>();

        double result = 0;

        for (ExpressionStack expression : expressions) {
            if ("NUMBER".equals(expression.getType())) {
                double value = Double.parseDouble(expression.getValue());
                numbers.add(value);
            } else if ("OPERATOR".equals(expression.getType())) {
                operators.add(expression.getValue());
            }
        }

        if (numbers.size() < 2 || numbers.size() != operators.size() + 1) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            printWriter.write("Invalid expression format.");
            return;
        }

        result = numbers.get(0);

        for (int i = 0; i < operators.size(); i++) {
            String operator = operators.get(i);
            double nextNumber = numbers.get(i + 1);
            result = new ExpressionStack().calculate(result, nextNumber, operator);


        }

        numbers.clear();

        resp.setContentType("application/json");
        Result resultObject = new Result();
        resultObject.setResult(result);
        String json = gson.toJson(resultObject);
        printWriter.write(json);
    }

}


//        resp.setContentType("application/json");
//        PrintWriter printWriter = resp.getWriter();
//        StringBuilder requestBody = new StringBuilder();
//        String line = "";
//        try (BufferedReader reader = req.getReader()) {
//            while ((line = reader.readLine()) != null) {
//                requestBody.append(line);
//            }
//        }
//
//        Gson gson = new Gson();
//        ExpressionStack[] expressions = (ExpressionStack[]) gson.fromJson(requestBody.toString(), ExpressionStack[].class);
//        List<Double> numbers = new ArrayList<>();
//        System.out.println("numbers Arraylist  " +numbers);
//        List<String> operators = new ArrayList<>();
//
//        double result = 0;
//
//        for (ExpressionStack expression : expressions) {
//            if ("NUMBER".equals(expression.getType())) {
//                double value = Double.parseDouble(expression.getValue());
//                numbers.add(value);
//            } else if ("OPERATOR".equals(expression.getType())) {
//                operators.add(expression.getValue());
//            }
//        }
//
//        result = numbers.get(0);
//        System.out.println("First value :"+result);
//
//        for (int i=0;i<operators.size();i++){
//            String operator = operators.get(i);
//            double nextNumber = numbers.get(i+1);
//            System.out.println("operator :"+operator+" "+"\n"+"nextNumber(Second Value) :"+ nextNumber);
//
//            switch (operator){
//                case "ADD":
//                    result += nextNumber;
//                    break;
//                case "SUBTRACT":
//                    result -= nextNumber;
//                    break;
//                case "MULTIPLY":
//                    result *= nextNumber;
//                    break;
//                case "DIVIDE":
//                    if (nextNumber != 0) {
//                        result /= nextNumber;
//                    } else {
//                        printWriter.write("Error: Division by zero is not allowed.");
//                        return;
//                    }
//                    break;
//            }
//
//        }
//        numbers.clear();
//        System.out.println(result);
//        gson = new Gson();
//        resp.setContentType("application/json");
//        Result result1 = new Result();
//        result1.setResult(result);
//        System.out.println(result1);
//        String json = gson.toJson(result1);
//        printWriter.write(json);
//        System.out.println("RESULT : "+ result );
//
//    }



