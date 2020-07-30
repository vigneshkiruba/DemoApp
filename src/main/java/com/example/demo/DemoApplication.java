package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@SpringBootApplication
public class DemoApplication {
	public static Logger demoLog = Logger.getLogger("My Log");

	public static void main(String[] args) {

		FileHandler fh;

		try
		{
			fh = new FileHandler("C:\\Users\\USER\\Desktop\\SpringBoot\\demo\\log\\Log1.log");
			demoLog.addHandler(fh);
			SimpleFormatter simple = new SimpleFormatter();
			fh.setFormatter(simple);
			demoLog.setUseParentHandlers(false);
			demoLog.info("Working **");
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpringApplication.run(DemoApplication.class, args);
	}

}

