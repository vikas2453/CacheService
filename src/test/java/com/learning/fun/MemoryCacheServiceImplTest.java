package com.learning.fun;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learning.fun.model.Address;
import com.learning.fun.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemoryCacheServiceImplTest {

	@Autowired
	CacheService memoryCacheService;

	Address address1 = new Address(1, "address line 1", "Apt# myApt1", "myCity1", "myZipcode1");

	Customer cust1 = new Customer(1, "First Name 1", "Last Name 1", address1);

	@Test
	public void testPutNGet() {
		memoryCacheService.put("cust1", cust1);
		Customer cachedCustomer = (Customer) memoryCacheService.get("cust1");
		assertNotNull(cachedCustomer);
		assert (cachedCustomer.equals(cust1));

	}
}
