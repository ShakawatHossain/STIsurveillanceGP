package com.shakawat.stisurveillancegp.model;

import java.util.HashMap;

public class MainModel {
    public static String user_id="";
    public static HashMap<String,String> mainParam = new HashMap<String, String>();
    public static HashMap<String,String> demoParam = new HashMap<String, String>();
    public static HashMap<String,String> clinParam = new HashMap<String, String>();
    public static HashMap<String,String> pastParam = new HashMap<String, String>();
    public static HashMap<String,String> riskParam = new HashMap<String, String>();
    public static HashMap<String,String> visitParam = new HashMap<String, String>();
    public static void clear(){
        mainParam.clear();
        demoParam.clear();
        clinParam.clear();
        pastParam.clear();
        riskParam.clear();
        visitParam.clear();
    }
}
