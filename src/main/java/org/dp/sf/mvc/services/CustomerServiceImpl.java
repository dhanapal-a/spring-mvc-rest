package org.dp.sf.mvc.services;

import java.util.List;
import java.util.stream.Collectors;

import org.dp.sf.mvc.api.v1.mapper.CustomerMapper;
import org.dp.sf.mvc.api.v1.model.CustomerDTO;
import org.dp.sf.mvc.domain.Customer;
import org.dp.sf.mvc.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerMapper customerMapper;
	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
		this.customerMapper = customerMapper;
		this.customerRepository = customerRepository;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		return customerRepository.findAll().stream().map(customer -> {
			CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
			customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
			return customerDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {

		return customerRepository.findById(id).map(customerMapper::customerToCustomerDTO)
				.orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

		return saveAndReturnDTO(customerMapper.CustomerDTOToCustomer(customerDTO));
	}

	private CustomerDTO saveAndReturnDTO(Customer customer) {
		Customer savedCustomer = customerRepository.save(customer);

		CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);
		returnDto.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());
		return returnDto;
	}

	@Override
	public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
		Customer customer = customerMapper.CustomerDTOToCustomer(customerDTO);
		customer.setId(id);

		return saveAndReturnDTO(customer);
	}

	@Override
	public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
		return customerRepository.findById(id).map(customer -> {

			if (customerDTO.getFirstName() != null) {
				customer.setFirstName(customerDTO.getFirstName());
			}

			if (customerDTO.getLastName() != null) {
				customer.setLastName(customerDTO.getLastName());
			}

			CustomerDTO returnDto = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
			returnDto.setCustomerUrl("/api/v1/customer/" + id);
			return returnDto;
		}).orElseThrow(ResourceNotFoundException::new); 
	}

	@Override
	public void deleteCustomerById(Long id) {
		customerRepository.deleteById(id);
	}
}
