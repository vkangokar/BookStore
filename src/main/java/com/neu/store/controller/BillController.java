package com.neu.store.controller;

import java.text.DateFormat;
import javax.servlet.ServletContext;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import org.springframework.web.bind.ServletRequestUtils;
import java.util.Map;
import org.springframework.web.servlet.View;

import com.neu.store.dao.AdvertDAO;
import com.neu.store.dao.BookDAO;
import com.neu.store.dao.CategoryDAO;
import com.neu.store.dao.DAO;
import com.neu.store.dao.CustomerDAO;
import com.neu.store.pojo.Book;
import com.neu.store.pojo.PDFView;
import com.neu.store.pojo.Customer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;


import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/cart/*")
public class BillController extends PDFView{

	
	
	@Autowired
	@Qualifier("advertDao")
	AdvertDAO advertDao;
	
	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDao;
	
	@Autowired
	@Qualifier("customerDao")
	CustomerDAO customerDao;
	
	@Autowired
	@Qualifier("bookDao")
	BookDAO  bookDao;
	
	@Autowired
	ServletContext servletContext;
	
	
	@RequestMapping(value = "/cart/checkout", method = RequestMethod.POST)
	public ModelAndView showPdfReport(@ModelAttribute("book") Book book,
									  ModelMap model,
			                          BindingResult result, 
			                          HttpServletRequest request) throws Exception
	{
		List<Book> view=bookDao.list();
		model.addAttribute("cartitems", view);
		
		
		View vi = new PDFView();
		return new ModelAndView(vi);
	}
	
}

