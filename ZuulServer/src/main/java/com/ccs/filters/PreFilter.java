//package com.ccs.filters;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.exception.ZuulException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PreFilter extends ZuulFilter {
//
//    @Autowired
//    FilterUtils filterUtils;
//
//    @Override
//    public String filterType() {
//        return null;
//    }
//
//    @Override
//    public int filterOrder() {
//        return 0;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return false;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        return null;
//    }
//}
