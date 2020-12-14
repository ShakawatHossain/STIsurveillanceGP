package com.shakawat.stisurveillancegp.model;

import java.util.ArrayList;

public class UtilModel {
    private ArrayList<String> username = new ArrayList<>();
    private ArrayList<String> password = new ArrayList<>();
    public UtilModel(){
        for (int i=1;i<30;i++){
            username.add("user"+i);
            password.add("sti"+i);
        }
    }
    public String getLogin(String un,String pass){
        for (int i=0;i<username.size();i++){
            if (un.compareTo(username.get(i))==0 && pass.compareTo(password.get(i))==0){
                if (i<10)
                    return "0"+String.valueOf(i+1);
                else
                    return String.valueOf(i+1);
            }
        }
        return null;
    }
}
