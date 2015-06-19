package com.plter.jus.filters;

import org.springframework.web.filter.CharacterEncodingFilter;

import java.io.IOException;

/**
 * Created by plter on 6/17/15.
 */
@javax.servlet.annotation.WebFilter(filterName = "CharsetFilter",urlPatterns = "/*")
public class CharsetFilter extends CharacterEncodingFilter{
    public void destroy() {
        setEncoding("UTF-8");
    }
}
