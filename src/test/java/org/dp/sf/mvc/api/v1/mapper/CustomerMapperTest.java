package org.dp.sf.mvc.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.dp.sf.mvc.api.v1.model.CustomerDTO;
import org.dp.sf.mvc.domain.Customer;
import org.junit.jupiter.api.Test;

class CustomerMapperTest {

	public static final String FIRSTNAME = "Jimmy";
	public static final String LASTNAME = "Fallon";
	CustomerMapper customerMapper = CustomerMapper.INSTANCE;

	@Test
	public void customerToCustomerDTO() throws Exception {
		// given
		Customer customer = new Customer();
		customer.setFirstName(FIRSTNAME);
		customer.setLastName(LASTNAME);

		// when
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

		// then
		assertEquals(FIRSTNAME, customerDTO.getFirstName());
		assertEquals(LASTNAME, customerDTO.getLastName());

	}

}
