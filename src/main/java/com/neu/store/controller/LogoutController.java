package com.neu.store.controller;

import javax.servlet.ServletContext;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.store.dao.AdvertDAO;
import com.neu.store.dao.CategoryDAO;
import com.neu.store.dao.CustomerDAO;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/advert/*")
public class LogoutController {

	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDao;
	
	@Autowired
	@Qualifier("advertDao")
	AdvertDAO advertDao;
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	@Qualifier("customerDao")
	CustomerDAO customerDao;
	
	
	
	@RequestMapping(value = "/advert/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "logout";
    }
}

