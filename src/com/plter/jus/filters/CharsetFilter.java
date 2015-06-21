package com.plter.jus.filters;

import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.annotation.WebFilter;

/**
 * Created by plter on 6/17/15.
 */
@WebFilter(filterName = "CharsetFilter",urlPatterns = "/*")
public class CharsetFilter extends CharacterEncodingFilter{

    public CharsetFilter(){
        setEncoding("UTF-8");
    }

}
