package com.plter.jus.errors;

/**
 * Created by plter on 6/30/15.
 */
public class ErrorCodes {

    static public final int NO_ERROR = 0;


    //用户相关1开头>>>>>>>>>>>>>>>>>>>>>>>>>>>
    static public final int NO_UID = 100000;
    public static final int NU_SUCH_USER_IN_DB = 100001;
    public static final int NO_USER_NAME = 100002;
    public static final int NO_NICENAME = 100003;
    public static final int NO_EMAIL = 100004;
    public static final int NO_PHONE = 100005;
    public static final int NO_URL = 100006;
    public static final int NO_USER_STATUS = 100007;
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    //数据库相关2开头>>>>>>>>>>>>>>>>>>>>>>>>>
    public static final int CAN_NOT_WRITE_TO_DB = 200000;
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
}
