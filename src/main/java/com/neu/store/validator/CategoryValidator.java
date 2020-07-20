package com.neu.store.validator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

import com.neu.store.dao.CategoryDAO;
import com.neu.store.exception.CategoryException;
import com.neu.store.pojo.Category;



@Component
public class CategoryValidator implements Validator {

	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDAO;
	
	public boolean supports(Class aC) {
		return Category.class.equals(aC);
	}

	public void validate(Object ob, Errors err) {
		Category newCategory = (Category) ob;

		ValidationUtils.rejectIfEmptyOrWhitespace(err, "title", "error.invalid.category", "Category Required");
		
		if (err.hasErrors()) {
            return;//Skip the rest of the validation rules
        }
        
	
		try {
			Category cat = categoryDAO.get(newCategory.getTitle());
			if(cat !=null)
				err.rejectValue("title", "error.invalid.category", "Category already Exists");
			
		} catch (CategoryException e) {
			System.err.println("Exception in Category Validator");
		}
		
		
		
	
	}
}

