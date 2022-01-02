package io.main;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/animal")
public class Animal {
	
	int id;
	String name;
	
	@Autowired
	IPersistence persistence;
	
	
	//int id = 0;
	
	Animal(){
		//this.id++;
		//persistence = Main.p;
	}
	
	@PostMapping("/register")
	public boolean register(@RequestBody ApplicationUser AU/*, @PathVariable IPersistence persistence*/) {
		return persistence.register(AU);
	}
	
	
	@GetMapping("/get/users")
	public ArrayList<ApplicationUser> printUsers() {
		return persistence.getUsers();
	}
	
	/*
	@GetMapping("/get")
	public int get() {
		return this.id;
	}
	*/
	
	
	/*
	ArrayList<String> s = new ArrayList<String>();
	int id;
	String name;
	

	@RequestMapping("/SandraHello")
	public String sayHello1() {
		return "Hi, SANDOOO";
	}
	
	
	@RequestMapping("/MostafaHello")
	public ArrayList<String> sayHello2() {
		s.add("mostafa");
		s.add("sandra");
		s.add("magdy");
		return s;
	}
	
	@PostMapping("/add/{name}")
	public void try1(@PathVariable String name , @RequestBody int id) {
		this.id = id;
		this.name = name;
	}
	
	
	@GetMapping("/get")
	public String get() {
		return this.name + " " + this.id;
	}
	*/
	
	
}
