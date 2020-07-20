package com.neu.store.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import javax.persistence.metamodel.BasicType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import com.captcha.botdetect.web.servlet.Captcha;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.ModelAndView;

//import com.captcha.botdetect.web.servlet.Captcha;
import com.neu.store.dao.CustomerDAO;
import com.neu.store.exception.UserException;
import com.neu.store.pojo.Customer;
import com.neu.store.validator.UserValidator;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	String useremail = "vinaygarg456789@gmail.com";
	private String  captcha, msg;
	public static final String CAPTCHA_KEY = "";
	

	

	
	public void sendEmail(String useremail, String message) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("vinaygarg456789@gmail.com", "Vinaygarg1234"));
            email.setSSLOnConnect(true);
            email.setFrom("no-reply@msis.neu.edu"); // This user email does not
                                                    // exist
            email.setSubject("Registratiom Successfull!!!!");
            email.setMsg("Congratulations!! 1 new user has registed on your site"); // Retrieve email from the DAO and send this
            email.addTo("kangokar.v@husky.neu.edu");
            email.send();
        } catch (EmailException e) {
            System.out.println("Email cannot be sent");
        }
    }

	
	@Autowired
	@Qualifier("customerDao")
	CustomerDAO customerDao;

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		return "user-home";
	}
	

	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected String loginUser(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		 
		
		try {

			System.out.print("loginUser");

			Customer u = customerDao.get(request.getParameter("username"), request.getParameter("password"));
			
			if(u == null){
				System.out.println("No UserName/Password found");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return "error";
			}
			
			else if(u.getUsertype().equals("Buyer")){
				session.setAttribute("customer", u);
				return "buyer-home";
			}
			
			else if(!(u.getUsertype().equals("Buyer"))&&!(u.getUsertype().equals("Seller"))){
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return "error";
			}
			
			else{
				session.setAttribute("customer", u);
				return "user-home";
			}
			
			//return "user-home";

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return "error";
		}

	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		ModelAndView mav = new ModelAndView("register-user");
		Map<String,String> usertype = new LinkedHashMap<String,String>();
		usertype.put("Buyer", "Buyer");
		usertype.put("Seller", "Seller");
		
		mav.addObject("usertype", usertype);
        mav.addObject("customer", new Customer());
        return mav;
		//return new ModelAndView("", "user", new User());

	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("customer") Customer customer, BindingResult result) {

		validator.validate(customer, result);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("register-user");
			Map<String,String> usertype = new LinkedHashMap<String,String>();
			usertype.put("Buyer", "Buyer");
			usertype.put("Seller", "Seller");

			mav.addObject("usertype", usertype);
	        mav.addObject("customer", customer);
	        return mav;
		}

		try {

			System.out.print("registerNewUser");

			Customer u = customerDao.register(customer);

			request.getSession().setAttribute("customer", u);

			Random rand = new Random();
	        int randomNum1 = rand.nextInt(5000000);
	        int randomNum2 = rand.nextInt(5000000);
	        try {
	            String str = "vinayrk100@gmail.com" + "&key1="
	                    + randomNum1 + "&key2=" + randomNum2;
	            
	            sendEmail("vinayrk100@gmail.com",
	                    "Click on this link to activate your account : "+ str);
			
			}
			catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

			return new ModelAndView("account-success", "customer", u);

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while registering");
		}
	}

		protected Map referenceData(HttpServletRequest request) throws Exception {

		Map referenceData = new HashMap();

		Map<String,String> usertype = new LinkedHashMap<String,String>();
		usertype.put("Buyer", "Buyer");
		usertype.put("Seller", "Seller");
		referenceData.put("usertype", usertype);
		return referenceData;
	}
}

