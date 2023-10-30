package com.unoveo.securityjwt.calculator;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
@WebFilter("/")
@CrossOrigin
public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse request=(HttpServletResponse) resp;
        request.setHeader("Access-Control-Allow-Origin", "*");

//      //  resp.setHeader("Access-Control-Allow-Origin", "*");
        request.setHeader("Access-Control-Allow-Methods", "POST");
        request.setHeader("Access-Control-Allow-Headers", "Content-Type");
        chain.doFilter(req,resp);
    }

    @Override
    public void destroy() {
    }
}


//package com.unoveo.securityjwt.calculatorAPI;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebFilter(asyncSupported = true, urlPatterns = { "/*" })
//public class CORSFilter implements Filter {
//
//    public CORSFilter(){
//
//    }
//    public void  destroy(){
//
//    }
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        System.out.println("CORSFilter HTTP Request: " + request.getMethod());
//
//        // Authorize (allow) all domains to consume the content
//        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
//        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
//
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//
//        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
//        if (request.getMethod().equals("OPTIONS")) {
//            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
//            return;
//        }
//
//        // pass the request along the filter chain
//        chain.doFilter(request, servletResponse);
//    }
//
//
//    public void init(FilterConfig fConfig) throws ServletException {
//
//    }
//
//}