package org.dp.sf.mvc.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.dp.sf.mvc.api.v1.mapper.CustomerMapper;
import org.dp.sf.mvc.api.v1.model.CustomerDTO;
import org.dp.sf.mvc.domain.Customer;
import org.dp.sf.mvc.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CustomerServiceImplTest {

	@Mock
	CustomerRepository customerRepository;

	CustomerMapper customerMapper = CustomerMapper.INSTANCE;

	CustomerService customerService;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);

		customerService = new CustomerServiceImpl(customerMapper, customerRepository);
	}

	@Test
	public void getAllCustomers() throws Exception {
		// given
		Customer customer1 = new Customer();
		customer1.setId(1l);
		customer1.setFirstName("Michale");
		customer1.setLastName("Weston");

		Customer customer2 = new Customer();
		customer2.setId(2l);
		customer2.setFirstName("Sam");
		customer2.setLastName("Axe");

		when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

		// when
		List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

		// then
		assertEquals(2, customerDTOS.size());

	}

	@Test
	public void getCustomerById() throws Exception {
		// given
		Customer customer1 = new Customer();
		customer1.setId(1l);
		customer1.setFirstName("Michale");
		customer1.setLastName("Weston");

		when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer1));

		// when
		CustomerDTO customerDTO = customerService.getCustomerById(1L);

		assertEquals("Michale", customerDTO.getFirstName());
	}
	
	@Test
    public void createNewCustomer() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(1l);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
        assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
    }
	
	@Test
    public void saveCustomerByDTO() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(1l);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.saveCustomerByDTO(1L, customerDTO);

        //then
        assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
        assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
    }
}
