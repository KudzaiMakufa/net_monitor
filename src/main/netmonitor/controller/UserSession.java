package main.netmonitor.controller;

import java.util.HashSet;
import java.util.Set;

public  class UserSession {
    private static String myVariable;

    public static String getMyVariable() {
        return myVariable;
    }

    public static void setMyVariable(String myVariable) {
        UserSession.myVariable = myVariable;
    }

}