package com.mindhub.ecommerce;

import com.mindhub.ecommerce.models.Client;
import com.mindhub.ecommerce.models.Product;
import com.mindhub.ecommerce.models.Ticket;
import com.mindhub.ecommerce.models.TicketProduct;
import com.mindhub.ecommerce.repositories.ClientRepository;
import com.mindhub.ecommerce.repositories.ProductRepository;
import com.mindhub.ecommerce.repositories.TicketProductRepository;
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
	public CommandLineRunner initData(ProductRepository productRepository, ClientRepository clientRepository, TicketRepository ticketRepository, TicketProductRepository ticketProductRepository){
		return args -> {
				//esto es un comentario

			Client client1 = new Client("Rodrigo","Gonzales",24,"rodri@mail.com","1234",123456);
			Client client2 = new Client("Franco","Rodriguez",24,"franco@mail.com","2345",43546);

			clientRepository.save(client1);
			clientRepository.save(client2);

			Product product1 = new Product("table","silla",3000.00,"x",10,"wood");
			Product product2 = new Product("sofa","sofa",5000.00,"x",15,"iron");
			productRepository.save(product1);
			productRepository.save(product2);

			/*SOFA*/
			/*productRepository.save(new Product("","",00.00,"x",1,""));
			productRepository.save(new Product("","",00.00,"x",1,""));
			productRepository.save(new Product("","",00.00,"x",1,""));
			productRepository.save(new Product("","",00.00,"x",1,""));

			productRepository.save(new Product("","",00.00,"x",1,""));
			productRepository.save(new Product("","",00.00,"x",1,""));
			productRepository.save(new Product("","",00.00,"x",1,""));
			productRepository.save(new Product("","",00.00,"x",1,""));

			productRepository.save(new Product("","",00.00,"x",1,""));
			productRepository.save(new Product("","",00.00,"x",1,""));
			productRepository.save(new Product("","",00.00,"x",1,""));
			productRepository.save(new Product("","",00.00,"x",1,""));*/

			/*TABLE*/
			/*productRepository.save(new Product("table","Low table",20000.00,"https://drommel.com.ua/image/cache/catalog/235/drom235-wh_br-610x610-610x610.jpeg",15,"iron"));
			productRepository.save(new Product("table","Dining table",50000.00,"https://drommel.com.ua/image/cache/catalog/products/%D0%9E%D0%BF%D0%BE%D1%80%D0%B0%20%D0%BA%20%D1%81%D1%82%D0%BE%D0%BB%D1%83%20R1/DROM146-BL-610x610.jpg",13,"iron"));
			productRepository.save(new Product("table","Desk iron",30000.00,"https://drommel.com.ua/image/cache/catalog/products/%D0%9E%D0%BF%D0%BE%D1%80%D0%B0%20%D0%BA%20%D1%81%D1%82%D0%BE%D0%BB%D1%83%20Y/DROM155-BL-610x610.jpg",6,"iron"));
			productRepository.save(new Product("table","Desk & store",35000.00,"https://drommel.com.ua/image/cache/catalog/products/%D0%9E%D0%BF%D0%BE%D1%80%D0%B0%20%D0%BA%20%D1%81%D1%82%D0%BE%D0%BB%D1%83%20%D1%81%20%D0%B4%D0%B2%D0%BE%D0%B9%D0%BD%D1%8B%D0%BC%D0%B8%20%D0%BF%D0%BE%D0%BB%D0%BA%D0%B0%D0%BC%D0%B8/DROM152-BL-610x610.jpg",1,"iron"));

			productRepository.save(new Product("table","Coffee table",1500.00,"https://drommel.com.ua/image/cache/catalog/el15black-610x610-610x610.png",10,"wood"));
			productRepository.save(new Product("table","Large coffee table",10000.00,"https://drommel.com.ua/image/cache/catalog/050/drom50new-610x610-610x610.jpeg",40,"wood"));
			productRepository.save(new Product("table","Small table",2000.00,"https://drommel.com.ua/image/cache/catalog/227/drom277_ph6-610x610.jpg",20,"wood"));
			productRepository.save(new Product("table","Wood desk",30000.00,"https://drommel.com.ua/image/cache/catalog/products/%D0%9F%D0%B8%D1%81%D1%8C%D0%BC%D0%B5%D0%BD%D0%BD%D1%8B%D0%B9%20%D1%81%D1%82%D0%BE%D0%BB%20Scandic/DROM215-WH_N-610x610.jpg",19,"wood"));

			productRepository.save(new Product("table","Notebook table",7000.00,"https://www.ikea.com/es/es/images/products/bjorkasen-soporte-portatil-blanco__1122204_pe874546_s5.jpg?f=xl",30,"plastic"));
			productRepository.save(new Product("table","Dining table",20000.00,"https://www.ikea.com/es/es/images/products/docksta-mesa-negro-negro__0979422_pe814531_s5.jpg?f=xl",22,"plastic"));
			productRepository.save(new Product("table","Plastic kit",9000.00,"https://www.ikea.com/es/es/images/products/fridnas-juego-2-mesas-nido-2-taburetes-negro-efecto-abedul__1038547_pe839730_s5.jpg?f=xl",32,"plastic"));
			*///productRepository.save(new Product("table","",00.00,"x",10,"plastic"));

			/*STORAGE*/ /*ARREGLAR PRODUCTO 1 STORAGE*/
			/*productRepository.save(new Product("storage","Cafe point",1500.00,"https://drommel.com.ua/image/cache/catalog/drom02-bl_wh-610x610.jpg",10,"iron"));
			productRepository.save(new Product("storage","Perfect Match",8000.00,"https://drommel.com.ua/image/cache/catalog/002/drom02_ph3-610x610.jpg",5,"iron"));
			productRepository.save(new Product("storage","Set of shelves",1500.00,"https://drommel.com.ua/image/cache/catalog/186/drom186_ph1-610x610.jpg",5,"iron"));
			productRepository.save(new Product("storage","Support hanger",6000.00,"https://drommel.com.ua/image/cache/catalog/045/drom45_ph4-610x610.jpg",1,"iron"));

			productRepository.save(new Product("storage","Chest of drawers",15000.00,"https://drommel.com.ua/image/cache/catalog/342/drom342_ph1_2-610x610.jpg",10,"wood"));
			productRepository.save(new Product("storage","Shelving",20000.00,"https://drommel.com.ua/image/cache/catalog/196/drom09-wh_n-610x610-610x610.jpeg",3,"wood"));
			productRepository.save(new Product("storage","Shelf with doors",25000.00,"https://drommel.com.ua/image/cache/catalog/379/drom379-bl-n-610x610.jpg",1,"wood"));
			productRepository.save(new Product("storage","Night stand",00.00,"https://drommel.com.ua/image/cache/catalog/332/drom322_bln-610x610.jpg",70,"wood"));

			productRepository.save(new Product("storage","Plastic boxes",3000.00,"https://www.ikea.com/es/es/images/products/trofast-combinacion-almacenaje-con-cajas-blanco__0471486_pe613433_s5.jpg?f=xl",5,"plastic"));
			productRepository.save(new Product("storage","Corner shelf",1500.00,"https://www.ikea.com/es/es/images/products/vesken-estanteria-esquina-pared-blanco__0832000_pe777544_s5.jpg?f=xl",10,"plastic"));
			productRepository.save(new Product("storage","Base cabinet",3000.00,"https://www.ikea.com/es/es/images/products/lilltjarn-armario-bajo-lavabo-2-prtas-blanco__1031288_pe836465_s5.jpg?f=xl",6,"plastic"));
			productRepository.save(new Product("storage","Box with lid",1000.00,"https://www.ikea.com/es/es/images/products/kuggis-caja-con-tapa-blanco__0713059_pe729163_s5.jpg?f=xl",50,"plastic"));
*/

			/*GARDEN*/
			/*productRepository.save(new Product("","",00.00,"x",1,""));
			productRepository.save(new Product("","",00.00,"x",1,""));
			productRepository.save(new Product("","",00.00,"x",1,""));
			productRepository.save(new Product("","",00.00,"x",1,""));

			productRepository.save(new Product("","",00.00,"x",1,""));
			productRepository.save(new Product("","",00.00,"x",1,""));
			productRepository.save(new Product("","",00.00,"x",1,""));
			productRepository.save(new Product("","",00.00,"x",1,""));

			productRepository.save(new Product("","",00.00,"x",1,""));
			productRepository.save(new Product("","",00.00,"x",1,""));
			productRepository.save(new Product("","",00.00,"x",1,""));
			productRepository.save(new Product("","",00.00,"x",1,""));*/


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

					productRepository.save(new Product(type[ramodType],name[nameraodm],raomdPrice,"x",sotok,materialType[ramdomMaterialType]));
				}

		};
	}

}
