package com.hujianbo.baseandroid;

public class demo {

    public static void main(String[] args) {

        System.out.println(ddd("张三"));
        System.out.println(ddd(null));
        System.out.println(ddd("张大大"));
    }

    private static String ddd(String name){
        String userName = "***";
        if(name != null && name.length() > 0){
            userName = name.substring(0,1);
            for (int i = 1; i < name.length(); i++) {
                userName = userName + "*";
            }
        }
        return userName;
    }
}



