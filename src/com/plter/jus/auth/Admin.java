package com.plter.jus.auth;

/**
 * Created by plter on 6/23/15.
 */
public class Admin {

    public static boolean isSuperAdmin(long userId){
        return userId == 1;
    }
}
