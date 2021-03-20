package org.dp.sf.mvc.api.v1.mapper;

import org.dp.sf.mvc.api.v1.model.CustomerDTO;
import org.dp.sf.mvc.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

	CustomerDTO customerToCustomerDTO(Customer customer);
	
	Customer CustomerDTOToCustomer(CustomerDTO customerDTO);

}
