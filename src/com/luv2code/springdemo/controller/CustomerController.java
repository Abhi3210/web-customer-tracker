package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.dao.CustomerDao;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	/*inject Customer DAO
	@Autowired
	private CustomerDao customerDAO;*/
	
	// need to inject Customer DAO
	@Autowired
	private CustomerService customerService;
	
	//@RequestMapping("/list")
	@GetMapping("/list")
	public String listCustomer(Model theModel)
	{   
		/*//get customer list from Customer DAO
		List<Customer> theCustomers=customerDAO.getCustomer();*/
		
		//get customer List from Customer Service
		List<Customer> theCustomers=customerService.getCustomer();
		
		//add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{   
		//create model attribute to bind form data
		Customer theCustomer=new Customer();
		theModel.addAttribute("customer",theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		// save the customer using our service
		customerService.saveCustomer(theCustomer);
		
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,Model theModel)
	{
		//get the Customer from the service
		  Customer theCustomer=customerService.getCustomer(theId);
		  
		//set the customer as a model to pre-populate the form
		  theModel.addAttribute("customer", theCustomer);
		
		//send the data over customer form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId)
	{
		//get the Customer from the service
		  customerService.deleteCustomer(theId);

		//send the data over customer form
		return "redirect:/customer/list";
	}
	
	@PostMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
									Model model) {

		// search customers from the service
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
				
		// add the customers to the model
		model.addAttribute("customers", theCustomers);

		return "serach-customers";
	}

}
