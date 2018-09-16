package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDao {
	
	//need to inject session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	//@Transactional
	public List<Customer> getCustomer() {
			
		//get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//create a query
		Query<Customer> theQuery=currentSession.createQuery("from Customer order by id asc", Customer.class);
		
		//execute query and get result list
		List<Customer> customers=theQuery.getResultList();
		
		//return the result
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		//get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
	   //save new customer
		//currentSession.save(theCustomer);
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		//get the current hibernate session
	    Session currentSession=sessionFactory.getCurrentSession();
	    
	    //now retrieve/read the customer data from database
	    Customer theCustomer=currentSession.get(Customer.class, theId);
	    
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		//get the current hibernate session
	    Session currentSession=sessionFactory.getCurrentSession();
	    
	    //delete the customer with the provided primary key
	    Query theQuery=currentSession.createQuery("delete from Customer where id=:theCustomerId");
	    
	    theQuery.setParameter("theCustomerId", theId);
	    theQuery.executeUpdate();
		
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		// get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(first_name) like :theName or lower(last_name) like :theName",Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer order by id", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
	
	}

}




