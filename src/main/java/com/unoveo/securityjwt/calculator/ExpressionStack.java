package com.unoveo.securityjwt.calculator;

public
class ExpressionStack {
    private  String type;
    private  String value;

    public ExpressionStack(int num, int operation){

    }
    public ExpressionStack(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public ExpressionStack(String jsonString) {
    }

    public
    String getType() {
        return type;
    }

    public
    String getValue() {
        return value;
    }

    public
    void setValue(String value) {
        this.value = value;
    }

    public
    void setType(String type) {
        this.type = type;
    }

}
