package com.dg.sigco.common;

public class UserSession {

    public static String user = "";
    public static String name = "";
    public static boolean isAdmin = false;

    public static void clean(){
        user = "";
        name = "";
        isAdmin = false;
    }

}
