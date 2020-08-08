package com.awesomecalc;

import java.text.DecimalFormat;

public class InputData {
    private float leftVal;
    private float rightVal;
    private Character operator;
    private String  username;
    public String getResult() {
        try{
            String tempResult = calculate(leftVal,rightVal,operator);
            return "User "+username+": "+leftVal+" "+operator+" "+rightVal+" = "+tempResult;
        }catch (Exception ex){
            return  ex.getMessage();
        }

    }

    private String calculate(float l, float r, char op) throws Exception{
        DecimalFormat df = new DecimalFormat("0.00");
        float result =0;
        switch (op){
            case '+':
                result = l+r;
                break;
            case '-':
                result =l-r;
                break;
            case '*':
                result = l*r;
                break;
            case '/':
                if(r==0){
                    throw new Exception("Divided by zero exception");
                }
                result =l/r*1.0f;
                break;
            default:
                result =0;
        }

        return df.format(result);

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public float getRightVal() {
        return rightVal;
    }

    public void setRightVal(Integer rightVal) {
        this.rightVal = rightVal;
    }

    public Character getOperator() {
        return operator;
    }

    public void setOperator(Character operator) {
        this.operator = operator;
    }

    public float getLeftVal() {
        return leftVal;
    }

    public void setLeftVal(Integer leftVal) {
        this.leftVal = leftVal;
    }
}
