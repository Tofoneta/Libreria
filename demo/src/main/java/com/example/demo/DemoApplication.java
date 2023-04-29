package com.example.demo;

import com.example.demo.Servicios.LibroServicio;
import com.example.demo.controladoras.LibroControladora;
import com.example.demo.modelos.Libro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

}
