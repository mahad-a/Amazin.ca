package com.example.app;

import java.io.File;

import org.hibernate.annotations.DialectOverride.Where;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jakarta.annotation.PreDestroy;

@SpringBootApplication
public class AppApplication {

	
	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	CommandLineRunner userCommandLineRunner(UserRepository userRepository){
		return args->{

			User user = new User("user", "123ABC!");
			userRepository.save(user);

		};
	}

	@Bean
	CommandLineRunner adminCommandLineRunner(AdminRepository adminRepository){
		return args->{
			Admin admin = new Admin("admin", "123ABC!");
			adminRepository.save(admin);
		};
	}
	@Bean
	CommandLineRunner bookCommandLineRunner(BookInventory bookInventory){
		return args ->{
			Book billySummers = new Book(80211, "Billy Summers", "Stephen King");
			billySummers.setCoverImageOnInstantiation(billySummers, "static/BookCovers/Billy Summers.jpg");
			billySummers.setQuantity(5);
			bookInventory.save(billySummers);

			Book whereTheCrawdadsSing = new Book(11711, "Where The Crawdads Sing", "Delia Owens");
			whereTheCrawdadsSing.setCoverImageOnInstantiation(whereTheCrawdadsSing, "static/BookCovers/WhereTheCrawdadsSing.jpg");
			whereTheCrawdadsSing.setQuantity(3);
			bookInventory.save(whereTheCrawdadsSing);

			Book verity = new Book(22615, "Verity", "Colleen Hoover");
			verity.setCoverImageOnInstantiation(verity, "static/BookCovers/Verity.jpg");
			verity.setQuantity(9);
			bookInventory.save(verity);
			
			Book theSilentPatient = new Book(22962, "The Silent Patient", "Alex Michaelides");
			theSilentPatient.setCoverImageOnInstantiation(theSilentPatient, "static/BookCovers/TheSilentPatient.jpg");
			theSilentPatient.setQuantity(7);
			bookInventory.save(theSilentPatient);
			
			Book elevenTwentyTwoSixtyThree = new Book(46410, "11/22/63", "Stephen King");
			elevenTwentyTwoSixtyThree.setCoverImageOnInstantiation(elevenTwentyTwoSixtyThree, "static/BookCovers/ElevenTwentyTwoSixtyThree.jpg");
			elevenTwentyTwoSixtyThree.setQuantity(4);
			bookInventory.save(elevenTwentyTwoSixtyThree);
			
			Book darkMatter = new Book(59419, "Dark Matter", "Blake Crouch");
			darkMatter.setCoverImageOnInstantiation(darkMatter, "static/BookCovers/DarkMatter.jpg");
			darkMatter.setQuantity(6);
			bookInventory.save(darkMatter);

			Book neverLie = new Book (12312, "Never Lie", "Freida McFadden");
			neverLie.setCoverImageOnInstantiation(neverLie, "static/BookCovers/NeverLie.jpg");
			neverLie.setQuantity(10);
			bookInventory.save(neverLie);
			
			Book goneGirl = new Book(78438, "Gone Girl", "Gillian Flynn");
			goneGirl.setCoverImageOnInstantiation(goneGirl, "static/BookCovers/GoneGirl.jpg");
			goneGirl.setQuantity(15);
			bookInventory.save(goneGirl);

			Book theCoworker = new Book(90212, "The Coworker", "Freida McFadden");
			theCoworker.setCoverImageOnInstantiation(theCoworker, "static/BookCovers/TheCoworker.jpg");
			theCoworker.setQuantity(8);
			bookInventory.save(theCoworker);

		};
	}

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
