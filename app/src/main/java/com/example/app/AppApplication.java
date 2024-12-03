package com.example.app;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jakarta.annotation.PreDestroy;

@SpringBootApplication
public class AppApplication {

	
	/** 
	 * @param args main
	 */
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	/**
	 * Testing account for user
	 * @param userRepository database to save to
	 * @return saved user
	 */
	@Bean
	CommandLineRunner userCommandLineRunner(UserRepository userRepository){
		return args->{
			User user = new User("user", "123ABC!");
			userRepository.save(user);
		};
	}

	/**
	 * Testing account for admin
	 * @param adminRepository database to save to
	 * @return saved admin
	 */
	@Bean
	CommandLineRunner adminCommandLineRunner(AdminRepository adminRepository){
		return args->{
			Admin admin = new Admin("admin", "123ABC!");
			adminRepository.save(admin);
		};
	}

	/**
	 * Books to be saved in database
	 * @param bookInventory database to save to
	 * @return saved books
	 */
	@Bean
	CommandLineRunner bookCommandLineRunner(BookInventory bookInventory){
		return args ->{
			Book billySummers = new Book(80211, "Billy Summers", "Stephen King");
			billySummers.setCoverImageOnInstantiation(billySummers, "static/BookCovers/Billy Summers.jpg");
			billySummers.setQuantity(5);
			billySummers.setPrice(14.99);
			bookInventory.save(billySummers);

			Book whereTheCrawdadsSing = new Book(11711, "Where The Crawdads Sing", "Delia Owens");
			whereTheCrawdadsSing.setCoverImageOnInstantiation(whereTheCrawdadsSing, "static/BookCovers/WhereTheCrawdadsSing.jpg");
			whereTheCrawdadsSing.setQuantity(3);
			whereTheCrawdadsSing.setPrice(16.99);
			bookInventory.save(whereTheCrawdadsSing);

			Book verity = new Book(22615, "Verity", "Colleen Hoover");
			verity.setCoverImageOnInstantiation(verity, "static/BookCovers/Verity.jpg");
			verity.setQuantity(9);
			verity.setPrice(12.99);
			bookInventory.save(verity);

			Book theSilentPatient = new Book(22962, "The Silent Patient", "Alex Michaelides");
			theSilentPatient.setCoverImageOnInstantiation(theSilentPatient, "static/BookCovers/TheSilentPatient.jpg");
			theSilentPatient.setQuantity(7);
			theSilentPatient.setPrice(14.49);
			bookInventory.save(theSilentPatient);

			Book elevenTwentyTwoSixtyThree = new Book(46410, "11/22/63", "Stephen King");
			elevenTwentyTwoSixtyThree.setCoverImageOnInstantiation(elevenTwentyTwoSixtyThree, "static/BookCovers/ElevenTwentyTwoSixtyThree.jpg");
			elevenTwentyTwoSixtyThree.setQuantity(4);
			elevenTwentyTwoSixtyThree.setPrice(18.99);
			bookInventory.save(elevenTwentyTwoSixtyThree);

			Book darkMatter = new Book(59419, "Dark Matter", "Blake Crouch");
			darkMatter.setCoverImageOnInstantiation(darkMatter, "static/BookCovers/DarkMatter.jpg");
			darkMatter.setQuantity(6);
			darkMatter.setPrice(15.49);
			bookInventory.save(darkMatter);

			Book neverLie = new Book(12312, "Never Lie", "Freida McFadden");
			neverLie.setCoverImageOnInstantiation(neverLie, "static/BookCovers/NeverLie.jpg");
			neverLie.setQuantity(10);
			neverLie.setPrice(11.99);
			bookInventory.save(neverLie);

			Book goneGirl = new Book(78438, "Gone Girl", "Gillian Flynn");
			goneGirl.setCoverImageOnInstantiation(goneGirl, "static/BookCovers/GoneGirl.jpg");
			goneGirl.setQuantity(15);
			goneGirl.setPrice(13.49);
			bookInventory.save(goneGirl);

			Book theCoworker = new Book(90212, "The Coworker", "Freida McFadden");
			theCoworker.setCoverImageOnInstantiation(theCoworker, "static/BookCovers/TheCoworker.jpg");
			theCoworker.setQuantity(8);
			theCoworker.setPrice(10.99);
			bookInventory.save(theCoworker);

			Book misery = new Book(62010, "Misery", "Stephen King");
			misery.setCoverImageOnInstantiation(misery, "static/BookCovers/Misery.jpg");
			misery.setQuantity(13);
			misery.setPrice(13.99);
			bookInventory.save(misery);

			Book theAliceNetwork = new Book(78451, "The Alice Network", "Kate Quinn");
			theAliceNetwork.setCoverImageOnInstantiation(theAliceNetwork, "static/BookCovers/TheAliceNetwork.jpg");
			theAliceNetwork.setQuantity(12);
			theAliceNetwork.setPrice(14.99);
			bookInventory.save(theAliceNetwork);

			Book localWomanMissing = new Book(65432, "Local Woman Missing", "Mary Kubica");
			localWomanMissing.setCoverImageOnInstantiation(localWomanMissing, "static/BookCovers/LocalWomanMissing.jpg");
			localWomanMissing.setQuantity(5);
			localWomanMissing.setPrice(12.49);
			bookInventory.save(localWomanMissing);

			Book behindClosedDoors = new Book(98765, "Behind Closed Doors", "B.A. Paris");
			behindClosedDoors.setCoverImageOnInstantiation(behindClosedDoors, "static/BookCovers/BehindClosedDoors.jpg");
			behindClosedDoors.setQuantity(10);
			behindClosedDoors.setPrice(13.99);
			bookInventory.save(behindClosedDoors);

			

		};
	}

	/**
	 * Clean up database
	 */
	@PreDestroy
    public void onExit() {
        File dbFile = new File("./data/mydb.mv.db");
        if (dbFile.exists()) {
            if (dbFile.delete()) {
                System.out.println("Database file deleted successfully.");
            } else {
                System.out.println("Failed to delete database file.");
            }
        }
    }

}
