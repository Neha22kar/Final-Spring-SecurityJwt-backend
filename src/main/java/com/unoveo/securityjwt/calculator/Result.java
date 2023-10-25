package com.unoveo.securityjwt.calculator;

public
class Result {
    public  double result;

    public
    double getResult() {
        return result;
    }

    public
    void setResult(double result) {
        this.result = result;
    }

    @Override
    public
    String toString() {
        return "Result{" +
                "Result=" + result +
                '}';
    }
}
