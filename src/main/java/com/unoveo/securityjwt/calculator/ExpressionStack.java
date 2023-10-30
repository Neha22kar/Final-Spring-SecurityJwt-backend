package com.unoveo.securityjwt.calculator;


import java.util.List;

public class ExpressionStack {
    private String type;
    private String value;

    public ExpressionStack() {
        this.type = type;
        this.value = value;
    }

    public
    ExpressionStack(String number, String number1) {
    }

    public static double calculate(double number1, double number2, String operator) {
//        if (operator == null) {
//            throw new IllegalArgumentException("Operator cannot be null");
//        }
        switch (operator) {
            case "ADD":
                return add(number1, number2);
            case "SUBTRACT":
                return subtract(number1, number2);
            case "MULTIPLY":
                return multiply(number1, number2);
            case "DIVIDE":
                return divide(number1, number2);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }


    public static double add(double number1, double number2) {
        return number1 + number2;
    }

    public static double subtract(double number1, double number2) {
        return number1 - number2;
    }

    public static double multiply(double number1, double number2) {
        return number1 * number2;
    }

    public static double divide(double number1, double number2) {
        if (number2 != 0) {
            return number1 / number2;
        } else {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }


}



//public
//class ExpressionStack {
//    private  String type;
//    private  String value;
//
//    public ExpressionStack(int num, int operation){
//
//    }
//    public ExpressionStack(String type, String value) {
//        this.type = type;
//        this.value = value;
//    }
//
//    public ExpressionStack(String jsonString) {
//    }
//
//    public
//    String getType() {
//        return type;
//    }
//
//    public
//    String getValue() {
//        return value;
//    }
//
//    public
//    void setValue(String value) {
//        this.value = value;
//    }
//
//    public
//    void setType(String type) {
//        this.type = type;
//    }
//
//}
