package com.neu.store.controller;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.web.bind.annotation.RequestMapping;

import com.neu.store.dao.AdvertDAO;
import com.neu.store.dao.CategoryDAO;
import com.neu.store.dao.CustomerDAO;
import com.neu.store.exception.AdvertException;
import com.neu.store.pojo.Advert;
import com.neu.store.pojo.Book;
import com.neu.store.pojo.Category;
import com.neu.store.pojo.Customer;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/advert/*")
public class AdvertController {
		
//	applying IOC 
	@Autowired
	ServletContext servletContext;
	
		@Autowired
		@Qualifier("advertDao")
		AdvertDAO advertDao;
		
		@Autowired
		@Qualifier("customerDao")
		CustomerDAO cutomerDao;
		
		@Autowired
		@Qualifier("categoryDao")
		CategoryDAO categoryDao;
		
		@RequestMapping(value = "/advert/add", method = RequestMethod.POST)
		public ModelAndView addCategory(@ModelAttribute("advert") Advert advert, BindingResult result) throws Exception {

			try {			
				
				
				Customer cust = cutomerDao.get(advert.getPostedBy());
				
				advert.setUser(cust);
				
				advert = advertDao.create(advert);
				
	            
				//Many to many relationship is maintained 
	            for(Category cat: advert.getCategories()){
	            	cat = categoryDao.get(cat.getTitle());
	            	cat.getAdverts().add(advert);
	            	categoryDao.update(cat); 
				
	            }
	            
	            if (advert.getFilename().trim() != "" || advert.getFilename() != null) {
					File dir;
					String check = File.separator; 
					String pathWay = null;
					if (check.equalsIgnoreCase("\\")) {
						pathWay = servletContext.getRealPath("").replace("build\\", ""); 
																							}

					// fOC OS mAC trailing slash IS ADDED.
					if (check.equalsIgnoreCase("/")) {
						pathWay = servletContext.getRealPath("").replace("build/", "");
						pathWay += "/"; 
					}
					dir = new File(pathWay + "\\" + advert.getFilename());
					boolean temp = dir.exists();
					if (!temp) {
						temp = dir.mkdir();
					}
					if (temp) {
						
					
						CommonsMultipartFile memoryPhoto = advert.getPhoto();

						String fileName = memoryPhoto.getOriginalFilename();
						

						File fileL = new File(dir.getPath(), fileName);

					

						memoryPhoto.transferTo(fileL);
						advert.setFilename(fileL.getPath());
						System.out.println("File is stored at" + fileL.getPath());
						System.out.print("registerNewUser");
						Advert a = advertDao.create(advert);

					} else {
						System.out.println("DIRECTORY CANNOT BE CRAETED");
					}
					
				}
				
	            return new ModelAndView("advert-success", "advert", advert);
	            
			} catch (AdvertException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "Login error");
			}
			
			
		}
		
		@RequestMapping(value = "/advert/list", method = RequestMethod.GET)
		public ModelAndView addCategory(HttpServletRequest request) throws Exception {

			ModelAndView modelAndView = new ModelAndView("advert-list");
			List<Advert> adverts = advertDao.list();
			modelAndView.addObject("adverts", adverts);
			modelAndView.addObject("book", new Book());
	        return modelAndView;
			
		}
		
		@RequestMapping(value = "/advert/sellerlist", method = RequestMethod.GET)
		public ModelAndView addCategories(HttpServletRequest request) throws Exception {

			try {			
				
				List<Advert> listOfadverts = advertDao.list();
				return new ModelAndView("seller-advert-list", "adverts", listOfadverts);
				
			} catch (AdvertException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "login error");
			}
			
			
		}

		@RequestMapping(value="/advert/add", method = RequestMethod.GET)
		public ModelAndView initializeForm(HttpServletRequest request) throws Exception {		
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("categories", categoryDao.list());			
			modelView.addObject("advert", new Advert());
			modelView.setViewName("advert-form");
			return modelView;
		}


}
