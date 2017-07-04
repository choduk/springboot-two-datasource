package com.example.demo;

import com.example.demo.blue.entity.BlueEntity;
import com.example.demo.blue.entity.BlueEntityRepository;
import com.example.demo.green.entity.GreenEntity;
import com.example.demo.green.entity.GreenEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	// Test용 더미데이터
	@Bean
	public CommandLineRunner addDefaultData(BlueEntityRepository blueEntityRepository, GreenEntityRepository greenEntityRepository) {

		return args -> {
			blueEntityRepository.save(Arrays.asList(
					new BlueEntity("blue1", "blue1"),
					new BlueEntity("blue2", "blue2"),
					new BlueEntity("blue3", "blue3")
					));

			greenEntityRepository.save(Arrays.asList(
					new GreenEntity("green1", "blue1"),
					new GreenEntity("green2", "green2"),
					new GreenEntity("green3", "green3")
			));
		};
	}
}
