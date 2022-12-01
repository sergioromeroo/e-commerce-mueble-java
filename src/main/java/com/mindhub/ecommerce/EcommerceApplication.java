package com.mindhub.ecommerce;

import com.mindhub.ecommerce.models.*;
import com.mindhub.ecommerce.repositories.*;
import com.mindhub.ecommerce.service.ProductService;
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
import java.util.Set;

@SpringBootApplication
public class EcommerceApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}


	@Bean
	public CommandLineRunner initData(ProductRepository productRepository, ClientRepository clientRepository, TicketRepository ticketRepository, TicketProductRepository ticketProductRepository, ProductFavoriteRepository productFavoriteRepository,ClientProductFavRepository clientProductFavRepository){
		return args -> {

			Client client1 = new Client("Mike","Smith","mike01@gmail.com",passwordEncoder.encode("1234"),2127709991,"Austin","3651 S (South) INTERSTATE 35","Texas");
			Client client2 = new Client("John","Brown","johnbrown@outlook.com",passwordEncoder.encode("234"),2128903332,"Boston"," 55 Fruit S","Massachusetts");
			Client client3=  new Client("admin","admin","admin@admin.com",passwordEncoder.encode("123"),1234,"admin","admin","admin");

			clientRepository.save(client2);
			clientRepository.save(client1);
			clientRepository.save(client3);

			/*SOFA*/

			Product product1 = new Product("sofa","Modular sofa",1245.00,"https://www.ikea.com/es/es/images/products/hogsten-sofa-2-plazas-exterior-blanco__1028707_pe835497_s5.jpg?f=xl",10,"iron","Enjoy the outdoors all year long. This sofa has a low-maintenance material that will allow you to enjoy great moments with friends and family.");
			Product product2 = new Product("sofa","Hammock",1345.00,"https://www.ikea.com/es/es/images/products/jutholmen-sofa-3-esquina-modular-exter-gris-oscuro-kuddarna-beige__0933026_pe791715_s5.jpg?f=xl",3,"iron","Combine different sections to create a sofa that perfectly fits your outdoor space in size and shape.");
			Product product3 = new Product("sofa","Modular sofa",1245.00,"https://www.ikea.com/es/es/images/products/jutholmen-sofa-jardin-modular-3-plazas-gris-oscuro-kuddarna-beige__0835243_pe778361_s5.jpg?f=xl",7,"iron","All sections of the sofa can be used independently or combined to create a modular sofa in the right size for your balcony or patio.");

			productRepository.save(product1);
			productRepository.save(product2);


			productRepository.save(product3);
			productRepository.save(new Product("sofa","Hammock",1345.00,"https://d3ugyf2ht6aenh.cloudfront.net/stores/001/232/181/products/111-44ed3d1867ce194b3516482549866260-1024-1024.png",4,"iron","Hanging Chair Simil Rattan Myanmar"));

			productRepository.save(new Product("sofa","Confort armchair",565.00,"https://www.ikea.com/es/es/images/products/pello-sillon-holmby-natural__0841137_pe600889_s5.jpg?f=xl",11,"wood","This armchair is a comfortable seat that transmits relaxation and with which you can create comfort zones in any part of the house. The secret is good back support and a slightly flexible structure."));
			productRepository.save(new Product("sofa","Double sofa",1230.00,"https://www.ikea.com/es/es/images/products/landskrona-sofa-2-plazas-gunnared-verde-claro-madera__0828967_pe680176_s5.jpg?f=xl",4,"wood","Thanks to the simple, warm and welcoming style, the good support of the seat cushions, the soft finish of the cover and its perfect adherence, this sofa offers comfort, function and style."));
			productRepository.save(new Product("sofa","Sofa with chaise longue",986.00,"https://www.ikea.com/es/es/images/products/landskrona-sofa-3-plazas-chaiselongue-gunnared-gris-oscuro-madera__0602368_pe680306_s5.jpg?f=xl",9,"wood","Thanks to the simple, warm and welcoming style, the good support of the seat cushions, the soft finish of the cover and its perfect adherence, this sofa offers comfort, function and style."));
			productRepository.save(new Product("sofa","Five seater",1699.00,"https://www.ikea.com/es/es/images/products/landskrona-sofa-5-plazas-con-chaiselongues-grann-bomstad-marron-dorado-madera__0825363_pe680401_s5.jpg?f=xl",5,"wood","We've used resiliency foam padding for comfort, thick full-grain leather on contact areas for added flair, and highly durable coated fabric on the rest of the furniture to keep the price down."));

			productRepository.save(new Product("sofa","White armchair",453.00,"https://www.ikea.com/es/es/images/products/skarpo-sillon-jardin-blanco__0979042_ph175831_s5.jpg?f=xl",17,"plastic","This chair stays in top condition for a long time because the plastic resists fading and is UV stable to prevent cracking and drying out."));
			/*productRepository.save(new Product("sofa","",00.00,"x",14,"plastic",""));
			productRepository.save(new Product("sofa","",00.00,"x",9,"plastic",""));
			productRepository.save(new Product("sofa","",00.00,"x",2,"plastic",""));*/

			/*TABLE*/
			productRepository.save(new Product("table","Low table",700.00,"https://drommel.com.ua/image/cache/catalog/235/drom235-wh_br-610x610-610x610.jpeg",15,"iron", "This laconic coffee table is like a basic wardrobe for your home. It goes well with most styles, does not draw attention to itself, but with proper use can become the central element of the space."));
			productRepository.save(new Product("table","Dining table",634.00,"https://drommel.com.ua/image/cache/catalog/products/%D0%9E%D0%BF%D0%BE%D1%80%D0%B0%20%D0%BA%20%D1%81%D1%82%D0%BE%D0%BB%D1%83%20R1/DROM146-BL-610x610.jpg",13,"iron", "The strength of this design is its versatility. This support, separate from the tabletop, makes it possible to install a rectangular, square, round or oval surface on top. The support is collapsible, and the legs are adjustable."));
			productRepository.save(new Product("table","Desk iron",689.00,"https://drommel.com.ua/image/cache/catalog/products/%D0%9E%D0%BF%D0%BE%D1%80%D0%B0%20%D0%BA%20%D1%81%D1%82%D0%BE%D0%BB%D1%83%20Y/DROM155-BL-610x610.jpg",6,"iron", "A minimalist desk support. Made of steel strip, that is why it is very stable and at the same time looks light and does not overload the space at all. Quality in the embodiment of verified geometry, welding seams cleaned and polished.."));
			productRepository.save(new Product("table","Desk & store",900.00,"https://drommel.com.ua/image/cache/catalog/products/%D0%9E%D0%BF%D0%BE%D1%80%D0%B0%20%D0%BA%20%D1%81%D1%82%D0%BE%D0%BB%D1%83%20%D1%81%20%D0%B4%D0%B2%D0%BE%D0%B9%D0%BD%D1%8B%D0%BC%D0%B8%20%D0%BF%D0%BE%D0%BB%D0%BA%D0%B0%D0%BC%D0%B8/DROM152-BL-610x610.jpg",1,"iron", "Very light and functional design. You can turn these supports into a minimalist desktop or a pair of consoles. Such a table is well suited for home and office space. A sufficient number of shelves is suitable for those who need to keep everything at hand."));

			productRepository.save(new Product("table","Coffee table",399.00,"https://drommel.com.ua/image/cache/catalog/el15black-610x610-610x610.png",10,"wood", "A coffee table is an important detail in the living room. In addition to practical functions, it also performs aesthetic functions. This is the same case when functionality and decorative needs are combined in furniture."));
			productRepository.save(new Product("table","Large coffee table",599.00,"https://drommel.com.ua/image/cache/catalog/050/drom50new-610x610-610x610.jpeg",40,"wood", "A universal round coffee table, which can become the center of gathering those closest to you for a board game, viewing old photos or playing with a child. It will also serve as the basis for a stylized Japanese-style meal that requires a low position."));
			productRepository.save(new Product("table","Small table",350.00,"https://drommel.com.ua/image/cache/catalog/227/drom277_ph6-610x610.jpg",20,"wood", "Looking at the items separately, we will see items. Each of these objects will play in a new way if this table becomes the point of union. And the roundness of the tabletop will dilute the harshest atmosphere or support the softness of the room."));
			productRepository.save(new Product("table","Wood desk",870.00,"https://drommel.com.ua/image/cache/catalog/products/%D0%9F%D0%B8%D1%81%D1%8C%D0%BC%D0%B5%D0%BD%D0%BD%D1%8B%D0%B9%20%D1%81%D1%82%D0%BE%D0%BB%20Scandic/DROM215-WH_N-610x610.jpg",19,"wood", "The Scandic table combines a minimalist design with a closed storage area. The steel frame and sheet metal facades do not overload the structure, and the three closed drawers have an inexhaustible storage potential, they can contain everything."));

			productRepository.save(new Product("table","Notebook table",200.00,"https://www.ikea.com/es/es/images/products/bjorkasen-soporte-portatil-blanco__1122204_pe874546_s5.jpg?f=xl",30,"plastic", "This white laptop stand is very stable and will be of great help when studying, working or holding the sheet music of an instrument. It is easy to adjust in height, tilt and carry from one side of the house to the other, as well as fold it."));
			productRepository.save(new Product("table","Dining table",765.00,"https://www.ikea.com/es/es/images/products/docksta-mesa-negro-negro__0979422_pe814531_s5.jpg?f=xl",22,"plastic", "A round table without sharp edges gives a relaxing air to the room."));
			productRepository.save(new Product("table","Plastic kit",600.00,"https://www.ikea.com/es/es/images/products/fridnas-juego-2-mesas-nido-2-taburetes-negro-efecto-abedul__1038547_pe839730_s5.jpg?f=xl",32,"plastic", "This practical set of two nesting tables and two matching stools/side tables lets you make the most of tight spaces. You can take them out to eat, study, work or do crafts, and quickly put them in to save space."));
			productRepository.save(new Product("table","Folding table",856.00,"https://www.ikea.com/es/es/images/products/kallhall-mesa-plegable-con-almacenaje-blanco-gris-claro__1015317_pe829990_s5.jpg?f=xl",10,"plastic","This versatile folding table for 2 to 4 people can store chairs, computers, game boards, napkins, tablecloths and other everyday essentials that you need to keep close at hand."));

			/*STORAGE*/
			productRepository.save(new Product("storage","Cafe point",500.00,"https://drommel.com.ua/image/cache/catalog/drom02-bl_wh-610x610.jpg",10,"iron","A console that accommodates important elements of everyday life. You can place a device on its top shelf, and paper on the bottom. Or a coffee machine upstairs, and below - the entire range of office \"goodies\".It will also come in handy in the home space."));
			productRepository.save(new Product("storage","Perfect Match",1100.00,"https://drommel.com.ua/image/cache/catalog/002/drom02_ph3-610x610.jpg",5,"iron","The elegant steel structure can serve as a decorative element by itself. Rail your capsule wardrobe and be sure every day that this step will save you time for a coffee in the morning or 15 minutes of sleep. The structure below is convenient as a shelf."));
			productRepository.save(new Product("storage","Set of shelves",1200.00,"https://drommel.com.ua/image/cache/catalog/186/drom186_ph1-610x610.jpg",5,"iron","Hanging shelves are a good basic element for storage. They are made of metal with a polymer coating, so they are very strong. They can be used in almost any space: in showrooms, offices, living room or bedroom as hanging bedside tables."));
			productRepository.save(new Product("storage","Support hanger",800.00,"https://drommel.com.ua/image/cache/catalog/045/drom45_ph4-610x610.jpg",1,"iron","The laconic metal graphics are good for any room, even with high humidity, so you can easily use it as a towel rack in the bathroom. In a bedroom or living room, any thing thrown over the crossbar of a hanger will look like something conceptual."));

			productRepository.save(new Product("storage","Chest of drawers",450.00,"https://drommel.com.ua/image/cache/catalog/342/drom342_ph1_2-610x610.jpg",10,"wood","A functional cabinet is an indispensable office or home office accessory! A4 format documents can easily be placed in the drawers, thanks to the wheels it can be easily moved to any convenient place."));
			productRepository.save(new Product("storage","Shelving",700.00,"https://drommel.com.ua/image/cache/catalog/196/drom09-wh_n-610x610-610x610.jpeg",3,"wood","Compactly located near the wall, the rack will hold tools and materials, and for small products it can become a warehouse. In the house, it can become a successful delimiter of space or serve as just a large shelf in the living room."));
			productRepository.save(new Product("storage","Shelf with doors",800.00,"https://drommel.com.ua/image/cache/catalog/379/drom379-bl-n-610x610.jpg",1,"wood","The Steady rack is the best solution for organizing space if you want to hide some things. In the closed section you can put things that are not needed every day. And on the open shelves, place books, decor and everything that pleases the eye."));
			productRepository.save(new Product("storage","Night stand",300.00,"https://drommel.com.ua/image/cache/catalog/332/drom322_bln-610x610.jpg",70,"wood","A concise cabinet is a find for those who like to hide something in a drawer so as not to accumulate things on the cabinet itself. "));

			productRepository.save(new Product("storage","Plastic boxes",400.00,"https://www.ikea.com/es/es/images/products/trofast-combinacion-almacenaje-con-cajas-blanco__0471486_pe613433_s5.jpg?f=xl",5,"plastic","Children need space to play and a place to store their toys, such as this, a series with sturdy wooden structures and lightweight plastic boxes that your child can easily move in, out of and around."));
			productRepository.save(new Product("storage","Corner shelf",350.00,"https://www.ikea.com/es/es/images/products/vesken-estanteria-esquina-pared-blanco__0832000_pe777544_s5.jpg?f=xl",10,"plastic","Ideal for small bathrooms, there is enough space on the shelves for toiletries from shampoo bottles to small objects."));
			productRepository.save(new Product("storage","Base cabinet",350.00,"https://www.ikea.com/es/es/images/products/lilltjarn-armario-bajo-lavabo-2-prtas-blanco__1031288_pe836465_s5.jpg?f=xl",6,"plastic","In small bathrooms, not an inch of space can be wasted. This cabinet allows you to take advantage of the space left under the sink to store towels, toilet paper, the hair dryer and toiletries."));
			productRepository.save(new Product("storage","Box with lid",200.00,"https://www.ikea.com/es/es/images/products/kuggis-caja-con-tapa-blanco__0713059_pe729163_s5.jpg?f=xl",50,"plastic","This medium box is ideal for storing papers and various objects, both at home and in the office. It is made of durable plastic."));


			/*GARDEN*/
			productRepository.save(new Product("garden","Outdoor table and chairs",760.00,"https://www.ikea.com/es/es/images/products/tarno-mesa-con-2-sillas-exterior-negro-tinte-marron-claro__0667583_pe713986_s5.jpg?f=xl",10,"iron","Foldable and resistant, it is made of solid acacia and powder-coated steel. Its measurements are ideal for a balcony or to create a cozy corner in the garden."));
			productRepository.save(new Product("garden","Outdoor table and chairs",600.00,"https://www.ikea.com/es/es/images/products/lacko-mesa-con-2-sillas-exterior-gris__0735585_pe740066_s5.jpg?f=xl",3,"iron","This table is inspired by traditional furniture made of forged steel, and adds a romantic touch to your outdoor space. Its pieces do not require any care, so you have more time to daydream in your garden."));
			/*productRepository.save(new Product("garden","",00.00,"x",6,"iron",""));
			productRepository.save(new Product("garden","",00.00,"x",8,"iron",""));*/

			productRepository.save(new Product("garden","Armrests & table",900.00,"https://www.ikea.com/es/es/images/products/falholmen-mesa-con-4-sillas-reposabrazos-ext-tinte-marron-claro__0735584_pe740065_s5.jpg?f=xl",11,"wood","Made of sustainably sourced acacia wood, allows you to enjoy cozy meals outdoors. Stackable chairs allow you to have more space when you do not have visitors."));
			productRepository.save(new Product("garden","Children's picnic table",850.00,"https://www.ikea.com/es/es/images/products/reso-mesa-picnic-p-ninos-tinte-marron-claro__0476279_pe616201_s5.jpg?f=xl",13,"wood","To extend the life and preserve the natural appearance of the wood, the furniture has been treated with a layer of semi-transparent wood varnish."));
			productRepository.save(new Product("garden","Table and 4 chairs",999.00,"https://www.ikea.com/es/es/images/products/hattholmen-fanbyn-mesa-con-4-sillas-exterior-eucalipto-roble-claro-blanco__0754669_pe748025_s5.jpg?f=xl",23,"wood","Includes an extension board."));
			productRepository.save(new Product("garden","Armrests & table",850.00,"https://www.ikea.com/es/es/images/products/bondholmen-mesa-con-4-sillas-reposabrazos-ext-tinte-gris__0806227_pe769863_s5.jpg?f=xl",7,"wood","Relax at this traditionally designed garden table and chairs set for four with generous proportions in solid wood. The round table is great for socializing as everyone can see each other."));

			productRepository.save(new Product("garden","Kid's table",400.00,"https://www.ikea.com/es/es/images/products/utter-mesa-ninos-int-ext-blanco__0673563_pe717323_s5.jpg?f=xl",13,"plastic","Perfect for children to sit and play, draw, do crafts or set the table for a picnic in the garden."));
			productRepository.save(new Product("garden","Garden storage",1500.00,"https://arcencohogar.vtexassets.com/arquivos/ids/289442-1200-1200?v=637651733785630000&width=1200&height=1200&aspect=true",9,"plastic","Its imitation wood texture and the extrusion of the garden sheds ensure durability and resistance to any weather condition, with a maintenance-free design. They are perfect for storing all kinds of home and garden products."));
			productRepository.save(new Product("garden","Hanging Chair",1000.00,"https://arcencohogar.vtexassets.com/arquivos/ids/313963-1200-1200?v=637794354974270000&width=1200&height=1200&aspect=true",7,"plastic","Hanging Chair Simil Rattan Myanmar"));
			productRepository.save(new Product("garden","Hanging Chair",1200.00,"https://arcencohogar.vtexassets.com/arquivos/ids/335763-1200-1200?v=638004057229730000&width=1200&height=1200&aspect=true",17,"plastic","Metal hanging chair with beige roof"));




			Ticket ticket1 = new Ticket(LocalDateTime.now(),1245,client1,"credit");
			Ticket ticket2 = new Ticket(LocalDateTime.now(),1345,client1,"debit");
			Ticket ticket3 = new Ticket(LocalDateTime.now(),2590,client1,"credit");
			Ticket ticket4 = new Ticket(LocalDateTime.now(),1245,client1,"credit");
			ticketRepository.save(ticket1);
			ticketRepository.save(ticket2);
			ticketRepository.save(ticket3);
			ticketRepository.save(ticket4);

			TicketProduct ticketProduct1 = new TicketProduct(ticket1,product1, 1);
			TicketProduct ticketProduct2 = new TicketProduct(ticket2,product2, 1);
			TicketProduct ticketProduct3 = new TicketProduct(ticket3,product3,1);
			TicketProduct ticketProduct4 = new TicketProduct(ticket3,product2,1);
			TicketProduct ticketProduct5 = new TicketProduct(ticket4,product3,1);

			ticketProductRepository.save(ticketProduct1);
			ticketProductRepository.save(ticketProduct2);
			ticketProductRepository.save(ticketProduct3);
			ticketProductRepository.save(ticketProduct4);
			ticketProductRepository.save(ticketProduct5);


			ProductFavorite producFav = new ProductFavorite(product1.getId(),product1.getName(),product1.getUrlImg(),product1.getPrice());

			productFavoriteRepository.save(producFav);

			ClientProducFav clientProducFav1 = new ClientProducFav(client1,producFav);

			clientProducFav1.setEnable(true);

			clientProductFavRepository.save(clientProducFav1);

//			ClientProducFav clientProducFav = new ClientProducFav(client1, producFav);



		};
	}

}
