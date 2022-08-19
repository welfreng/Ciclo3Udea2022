package com.UdeA.Ciclo3;

import com.UdeA.Ciclo3.modelos.Empleado;
import com.UdeA.Ciclo3.modelos.Empresa;
import org.hibernate.annotations.GeneratorType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Ciclo3Application {

	@GetMapping("/hello")
	public String hello() {
		return "Hello Mundo Prueba";
	}

	@GetMapping("/test")
	public String test() {
		Empresa emp =new Empresa("Solar SAS", "calle la geta", "321321321", "800181923");
		emp.setNombre("SOLAR LTDA.");
	return emp.getNombre();
	}

	/*
	@GetMapping("/test1")
	public String test1() {
		Empleado emp = new Empleado("welfren Garcia", "welfreng@gmail.com","Administrador");

		return emp.getCorreo();
	}

	 */
	public static void main(String[] args) {

		SpringApplication.run(Ciclo3Application.class, args);

	}

}
