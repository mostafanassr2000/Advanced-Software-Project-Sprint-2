package io.main;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Animal {
	
	ArrayList<String> s = new ArrayList<String>();
	int id;
	
	
	
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
	
	/*/
	@PostMapping("/add")
	public void try1(@RequestBody int id) {
		this.id = id;
	}
	
	@GetMapping("/get")
	public int get() {
		return this.id;
	}
	*/
	
	
}
