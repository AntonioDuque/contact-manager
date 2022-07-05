package com.programming.techie;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        /*String log_dir = System.getProperty("log_dir","/tmp/log");
        System.out.println(log_dir);

        System.getenv().forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });*/


        System.getProperties().put("DEV","ENV");
        System.out.println("HDSPM");
    }
}
