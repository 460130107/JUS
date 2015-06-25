package com.plter.jus.auth.tools;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by plter on 6/24/15.
 */
public class PasswordTool {

    static public String translatePassword(String pass){
        for (int i=0;i<15;i++) {
            pass = DigestUtils.md5Hex(pass);
        }
        return pass;
    }
}
