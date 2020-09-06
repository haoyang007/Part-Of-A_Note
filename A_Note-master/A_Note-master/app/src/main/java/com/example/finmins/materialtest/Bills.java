package com.example.finmins.materialtest;

import java.util.Date;

public class Bills {
    private Date date;      //账单时间
    private String type;    //账单类型
    private double amount;  //账单金额
    public Bills(){

    }
    public Bills(Date date,String type,double amount){
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    public Date getDate(){
        return date;
    }

    public String getType(){
        return type;
    }

    public double getAmount(){
        return amount;
    }
}
