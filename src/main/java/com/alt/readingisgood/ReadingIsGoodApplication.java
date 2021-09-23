package com.alt.readingisgood;

import com.alt.readingisgood.customer.Customer;
import com.alt.readingisgood.role.Role;
import com.alt.readingisgood.role.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.retry.annotation.EnableRetry;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ReadingIsGoodApplication implements CommandLineRunner {
	@Autowired
	private MongoTemplate mongoTemplate;

	public static void main(String[] args) {

		SpringApplication.run(ReadingIsGoodApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role roleUser = new Role();
		roleUser.setName(RoleName.USER);
		Role roleAdmin = new Role();
		roleAdmin.setName(RoleName.ADMIN);
		Set<Role> roleSet = new HashSet<>();
		roleSet.add(roleAdmin);

		Customer admin = new Customer();
		admin.setName("admin");
		admin.setPassword("$2a$10$aq.9zbPjjH4Ha8LSi8LApuj14XRcEfRsyiz1uTqU/c3GNtO.6befe");
		admin.setRoles(roleSet);
		admin.setUsername("admin");

		mongoTemplate.getCollection("roles").drop();
		mongoTemplate.getCollection("customer").drop();

		mongoTemplate.save(roleUser, "roles");
		mongoTemplate.save(roleAdmin, "roles");
		mongoTemplate.save(admin, "customer");
	}

}
