package com.palette.model;

import java.util.HashMap;

public class AdminstatisticsDto {

    //for Gender Rate count
    private int ManCount;
    private int WomanCount;
    

    //InterBig count
    private int big1;
    private int big2;
    private int big3;
    private int big4;
    
    
    
    //Inter Samll Count
    private HashMap<String, String> HashInterSmall;

    // //InterBig count
    // private HashMap<String, String> HashInterBig = new HashMap<String, String>();
    
    // //Inter Samll Count
    // private HashMap<String, String> HashInterSmall  = new HashMap<String, String>();


    public AdminstatisticsDto(){

    }

    public AdminstatisticsDto(int manCount, int womanCount, int big1, int big2, int big3, int big4,
            HashMap<String, String> hashInterSmall) {
        ManCount = manCount;
        WomanCount = womanCount;
        this.big1 = big1;
        this.big2 = big2;
        this.big3 = big3;
        this.big4 = big4;
        HashInterSmall = hashInterSmall;
    }

    public int getManCount() {
        return ManCount;
    }

    public void setManCount(int manCount) {
        ManCount = manCount;
    }

    public int getWomanCount() {
        return WomanCount;
    }

    public void setWomanCount(int womanCount) {
        WomanCount = womanCount;
    }

    public int getBig1() {
        return big1;
    }

    public void setBig1(int big1) {
        this.big1 = big1;
    }

    public int getBig2() {
        return big2;
    }

    public void setBig2(int big2) {
        this.big2 = big2;
    }

    public int getBig3() {
        return big3;
    }

    public void setBig3(int big3) {
        this.big3 = big3;
    }

    public int getBig4() {
        return big4;
    }

    public void setBig4(int big4) {
        this.big4 = big4;
    }

    public HashMap<String, String> getHashInterSmall() {
        return HashInterSmall;
    }

    public void setHashInterSmall(HashMap<String, String> hashInterSmall) {
        HashInterSmall = hashInterSmall;
    }

    @Override
    public String toString() {
        return "AdminstatisticsDto [HashInterSmall=" + HashInterSmall + ", ManCount=" + ManCount + ", WomanCount="
                + WomanCount + ", big1=" + big1 + ", big2=" + big2 + ", big3=" + big3 + ", big4=" + big4 + "]";
    }





    

}// end of AdminstatisticsDto class