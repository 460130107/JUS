package com.plter.jus.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by plter on 6/23/15.
 */
public abstract class Function{
    public abstract void execute(HttpServletRequest request,HttpServletResponse response);
}
