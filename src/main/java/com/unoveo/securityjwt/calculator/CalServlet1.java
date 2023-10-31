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

                double result = calculate(expressions);

                gson = new Gson();
                resp.setContentType("application/json");
                Result resultObject = new Result();
                resultObject.setResult(result);
                String json = gson.toJson(resultObject);
                printWriter.write(json);
            }

            public double calculate(ExpressionStack[] expressions) {
                List<Double> numbers = new ArrayList<>();
                List<String> operators = new ArrayList<>();

                for (ExpressionStack expression : expressions) {
                    if ("NUMBER".equals(expression.getType())) {
                        double value = Double.parseDouble(expression.getValue());
                        numbers.add(value);
                    } else if ("OPERATOR".equals(expression.getType())) {
                        operators.add(expression.getValue());
                    }
                }

                return doCalculate(numbers, operators);
            }

            private double doCalculate(List<Double> numbers, List<String> operators) {
                double result = numbers.get(0);
                for (int i = 0; i < operators.size(); i++) {
                    String operator = operators.get(i);
                    double nextNumber = numbers.get(i + 1);

                    switch (operator) {
                        case "ADD":
                            result += nextNumber;
                            break;
                        case "SUBTRACT":
                            result -= nextNumber;
                            break;
                        case "MULTIPLY":
                            result *= nextNumber;
                            break;
                        case "DIVIDE":
                            if (nextNumber != 0) {
                                result /= nextNumber;
                            } else {
                                // Handle division by zero error if needed
                                // For now, returning 0 in case of division by zero
                                return 0;
                            }
                            break;
                    }
                }

                return result;
            }
        }

