package com.neu.store.validator;

import org.springframework.validation.ValidationUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Validator;

import com.neu.store.pojo.Customer;

import org.springframework.validation.Errors;


public class UserValidator implements Validator {

	public boolean supports(Class aC) {
		return aC.equals(Customer.class);
	}

	 private Pattern pat;  
	 private Matcher matcher;  
	
	 private static final 
	 String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
			   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";    
	 String STRING_PATTERN = "[a-zA-Z]+";  
	
			 
			 
	public void validate(Object obj, Errors errors) {
		Customer customer = (Customer) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.user", "First Name Required");
		 if (!(customer.getFirstName() != null && customer.getFirstName().isEmpty())) {  
			   pat = Pattern.compile(STRING_PATTERN);  
			   matcher = pat.matcher(customer.getFirstName());  
			   if (!matcher.matches()) {  
			    errors.rejectValue("firstName", "firstName.containNonChar",  
			      "Enter a valid first name");  
			   }  
			  }  
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.user", "Last Name Required");
		if (!(customer.getLastName() != null && customer.getLastName().isEmpty())) {  
			   pat = Pattern.compile(STRING_PATTERN);  
			   matcher = pat.matcher(customer.getLastName());  
			   if (!matcher.matches()) {  
			    errors.rejectValue("lastName", "lastName.containNonChar",  
			      "Enter a valid last name");  
			   }  
			  } 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.user", "User Name Required");
		if (!(customer.getUsername() != null && customer.getUsername().isEmpty())) {  
			   pat = Pattern.compile(STRING_PATTERN);  
			   matcher = pat.matcher(customer.getUsername());  
			   if (!matcher.matches()) {  
			    errors.rejectValue("username", "username.containNonChar",  
			      "Enter a valid username");  
			   }  
			  }
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		if (!(customer.getPassword() != null && customer.getPassword().isEmpty())) {  
			   pat = Pattern.compile(STRING_PATTERN);  
			   matcher = pat.matcher(customer.getPassword());  
			   if (!matcher.matches()) {  
			    errors.rejectValue("password", "password.containNonChar",  
			      "Enter a valid password of strings");  
			   }  
			  }
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email.emailAddress", "error.invalid.email.emailAddress",
				"Email Required");
		 
		if("NONE".equals(customer.getUsertype())){
			errors.rejectValue("usertype", "error.invalid.usertype","User type is required");
		}
		// check if user exists
		
	}
}


