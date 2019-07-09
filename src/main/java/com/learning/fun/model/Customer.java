package com.learning.fun.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


//@XmlRootElement(name = "customer")

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Customer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private long customerId;
	
	private String firstName;
	
	private String LastName;
		
	private Address address;

	

	
}
