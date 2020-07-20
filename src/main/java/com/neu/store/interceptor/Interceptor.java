package com.neu.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.neu.store.pojo.Customer;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

public class Interceptor extends  HandlerInterceptorAdapter {

	 private String userlogout;
	    
	   
	    
	    public String getLogout() {
		return userlogout;
	}

	public void setLogout(String userlogout) {
		this.userlogout = userlogout;
	}

		/**
	     * This implementation always returns <code>true</code>.
	     */
	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	        throws Exception {
	        
	        System.out.println("Inside User Interceptor");
	        HttpSession session = request.getSession();
	        if(session != null){
	            
	        
	        
	        Object o = session.getAttribute("customer");
	        if(o != null){
	            
	        try{
	            Customer customer = (Customer) o;
	            return true;
	        }
	        catch(Exception e){
	            System.out.println("Exception in Recruiterinterceptor");
	            e.printStackTrace();
	        }}else{
	            response.sendRedirect(userlogout);
	            return false;
	        }
	        
	        }
	        
	        response.sendRedirect(userlogout);
	        return false;
	    }

	    /**
	     * This implementation is empty.
	     */
	    @Override
	    public void postHandle(
	            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
	            throws Exception {
	        super.postHandle(request, response, handler, modelAndView);
	    }    
}

