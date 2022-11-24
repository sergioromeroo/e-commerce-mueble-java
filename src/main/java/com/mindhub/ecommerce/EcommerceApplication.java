package com.mindhub.ecommerce;

import com.mindhub.ecommerce.models.Client;
import com.mindhub.ecommerce.models.Product;
import com.mindhub.ecommerce.models.Ticket;
import com.mindhub.ecommerce.models.TicketProduct;
import com.mindhub.ecommerce.repositories.ClientRepository;
import com.mindhub.ecommerce.repositories.ProductRepository;
import com.mindhub.ecommerce.repositories.TicketProductRepository;
import com.mindhub.ecommerce.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sound.sampled.Port;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class EcommerceApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}


	@Bean
	public CommandLineRunner initData(ProductRepository productRepository, ClientRepository clientRepository, TicketRepository ticketRepository, TicketProductRepository ticketProductRepository){
		return args -> {


			Client client1 = new Client("Rodrigo","Gonzales","rodri@mail.com",passwordEncoder.encode("1234"),123456);
			Client client2 = new Client("Franco","Rodriguez","franco@mail.com",passwordEncoder.encode("234"),43546);
			Client client3=new Client("admin","admin","admin@admin.com",passwordEncoder.encode("123"),1234);
			Product product1 = new Product("table","silla",3000.00,"x",10,"wood","prueba");
			Product product2 = new Product("sofa","sofa",5000.00,"x",15,"iron","prueba");


			clientRepository.save(client2);
			clientRepository.save(client1);
			clientRepository.save(client3);
			productRepository.save(product1);
			productRepository.save(product2);

			Ticket ticket1 = new Ticket(LocalDateTime.now(),3000,client1,"efectivo");
			Ticket ticket2 = new Ticket(LocalDateTime.now(),5000,client1,"debito");

			ticketRepository.save(ticket1);
			ticketRepository.save(ticket2);


			TicketProduct ticketProduct1 = new TicketProduct(ticket1,product1);
			TicketProduct ticketProduct2 = new TicketProduct(ticket2,product1);

			ticketProductRepository.save(ticketProduct1);
			ticketProductRepository.save(ticketProduct2);



							for(int  b = 0 ; b < 200 ; b++) {
					String type[] = {"sofa","table","storage","garden" };
					int ramodType = (int)(Math.random()*(3-0+1)+0);

					String  materialType[] = {"wood" , "plastico", "iron"};

					int ramdomMaterialType = (int)(Math.random()*(2-0+1)+0);
					double  raomdPrice = (double)(Math.random()*(9000-0+1)+0);
					int sotok = (int)(Math.random()*(60-0+1)+0);

					String name[] = {"sillar relinablre ","table artesnal ","sofa","FRIHETEN ","FRIHETEN" ,"ÄPPLARYD","STOCKSUND", "Ikea", "con chaise longue" ,"VISCAFORS" ,"VALLENTUNA" ," Video Playlist "};

					int nameraodm = (int)(Math.random()*(11-0+1)+0);

//					productRepository.save(new Product(type[ramodType],name[nameraodm],raomdPrice,"x",sotok,materialType[ramdomMaterialType]),"prueba");
				}

		};
	}

}
