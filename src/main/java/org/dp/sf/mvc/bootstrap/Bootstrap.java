package org.dp.sf.mvc.bootstrap;

import org.dp.sf.mvc.domain.Category;
import org.dp.sf.mvc.domain.Customer;
import org.dp.sf.mvc.domain.Vendor;
import org.dp.sf.mvc.repositories.CategoryRepository;
import org.dp.sf.mvc.repositories.CustomerRepository;
import org.dp.sf.mvc.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

	private CategoryRepository categoryRepository;
	private CustomerRepository customerRepository;
	private VendorRepository vendorRepository;

	public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
		this.vendorRepository = vendorRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		loadCategories();
		loadCustomers();
		// TODO Auto-generated method stub

	}

	private void loadCustomers() {
		// given
		Customer customer1 = new Customer();
		customer1.setId(1l);
		customer1.setFirstName("Michale");
		customer1.setLastName("Weston");
		customerRepository.save(customer1);

		Customer customer2 = new Customer();
		customer2.setId(2l);
		customer2.setFirstName("Sam");
		customer2.setLastName("Axe");

		customerRepository.save(customer2);

		System.out.println("Customers Loaded: " + customerRepository.count());
	}

	private void loadCategories() {
		Category fruits = new Category();
		fruits.setName("Fruits");

		Category dried = new Category();
		dried.setName("Dried");

		Category fresh = new Category();
		fresh.setName("Fresh");

		Category exotic = new Category();
		exotic.setName("Exotic");

		Category nuts = new Category();
		nuts.setName("Nuts");

		categoryRepository.save(fruits);
		categoryRepository.save(dried);
		categoryRepository.save(fresh);
		categoryRepository.save(exotic);
		categoryRepository.save(nuts);

		System.out.println("Data Loaded = " + categoryRepository.count());

	}
	private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor 1");
        vendorRepository.save(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor 2");
        vendorRepository.save(vendor2);

    }

}
