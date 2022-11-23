package com.mindhub.ecommerce;

import com.mindhub.ecommerce.models.Client;
import com.mindhub.ecommerce.models.Product;
import com.mindhub.ecommerce.models.Ticket;
import com.mindhub.ecommerce.repositories.ClientRepository;
import com.mindhub.ecommerce.repositories.ProductRepository;
import com.mindhub.ecommerce.repositories.TicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sound.sampled.Port;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}


	@Bean
	public CommandLineRunner initData(ProductRepository productRepository, ClientRepository clientRepository, TicketRepository ticketRepository){
		return args -> {


			Client client1 = new Client("Rodrigo","Gonzales",24,"rodri@mail.com","1234",123456);
			Client client2 = new Client("Franco","Rodriguez",24,"franco@mail.com","2345",43546);
			Product product1 = new Product("cocina","silla",3000.00,"x",10,"madera");
			Product product2 = new Product("comedor","sofa",5000.00,"x",15,"madera");




			clientRepository.save(client2);
			clientRepository.save(client1);
			productRepository.save(product1);
			productRepository.save(product2);

			Ticket ticket1 = new Ticket(LocalDateTime.now(),3000,client1,"silla");
			Ticket ticket2 = new Ticket(LocalDateTime.now(),5000,client1,"sofa");


			ticketRepository.save(ticket1);
			ticketRepository.save(ticket2);




		};
	}

}
