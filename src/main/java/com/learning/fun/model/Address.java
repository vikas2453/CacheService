package com.learning.fun.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 19L;

	private long addressId;

	private String firstLine;
	private String secondline;
	private String City;
	private String zipCode;

	
}
