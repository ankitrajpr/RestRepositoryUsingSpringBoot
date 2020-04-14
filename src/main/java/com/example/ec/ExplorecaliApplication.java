package com.example.ec;

import com.example.ec.domain.Difficulty;
import com.example.ec.domain.Region;
import com.example.ec.service.TourPackageService;
import com.example.ec.service.TourService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/*
the Main method doesnt have ACCCESS to services bcz main method is static in nature in it's class scope
For object scope, need to implement CommandLineRunner and it's subsequent run method
 */


@SpringBootApplication
public class ExplorecaliApplication implements CommandLineRunner {

	@Autowired
	private TourPackageService tourPackageService;
	@Autowired
	private TourService tourService;


	public static void main(String[] args) {
		SpringApplication.run(ExplorecaliApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Create the Tour Packages
		createTourPackages();
		long numOfPackages = tourPackageService.total();

		//Load the tours from an external Json File
		createTours("ExploreCalifornia.json");
		long numOfTours = tourService.total();
	}

	/**
	 * Initialize all the known tour packages
	 */
	private void createTourPackages(){
		tourPackageService.createTourPackage("BC", "Backpack Cal");
		tourPackageService.createTourPackage("CC", "California Calm");
		tourPackageService.createTourPackage("CH", "California Hot springs");
		tourPackageService.createTourPackage("CY", "Cycle California");
		tourPackageService.createTourPackage("DS", "From Desert to Sea");
		tourPackageService.createTourPackage("KC", "Kids California");
		tourPackageService.createTourPackage("NW", "Nature Watch");
		tourPackageService.createTourPackage("SC", "Snowboard Cali");
		tourPackageService.createTourPackage("TC", "Taste of California");
	}

	/**
	 * Create tour entities from an external file
	 */
	private void createTours(String fileToImport) throws IOException {
		TourFromFile.read(fileToImport).forEach(importedTour ->
				tourService.createTour(importedTour.getTitle(),
						importedTour.getDescription(),
						importedTour.getBlurb(),
						importedTour.getPrice(),
						importedTour.getLength(),
						importedTour.getBullets(),
						importedTour.getKeywords(),
						importedTour.getPackageType(),
						importedTour.getDifficulty(),
						importedTour.getRegion()));
	}

	/**
	 * Helper class to import ExploreCalifornia.json
	 */
	private static class TourFromFile {
		//fields
		private String packageType, title, description, blurb, price, length,
				bullets, keywords, difficulty, region;
		//reader  -- returns the Object as List after reading JSON data
		static List<TourFromFile> read(String fileToImport) throws IOException {
			return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
					readValue(new FileInputStream(fileToImport), new TypeReference<List<TourFromFile>>() {});
		}
		protected TourFromFile(){}

		String getPackageType() { return packageType; }

		String getTitle() { return title; }

		String getDescription() { return description; }

		String getBlurb() { return blurb; }

		Integer getPrice() { return Integer.parseInt(price); }

		String getLength() { return length; }

		String getBullets() { return bullets; }

		String getKeywords() { return keywords; }

		Difficulty getDifficulty() { return Difficulty.valueOf(difficulty); }

		Region getRegion() { return Region.findByLabel(region); }
	}


}
