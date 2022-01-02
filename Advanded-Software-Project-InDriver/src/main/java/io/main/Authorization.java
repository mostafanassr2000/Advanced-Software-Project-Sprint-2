package io.main;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
	

@RestController
@RequestMapping("/authorization")
public class Authorization implements IAuthorize{
	
	/*Attributes*/
	@Autowired
	IPersistence persistence;
	
	/*Constructor*/
	Authorization() {

	}
	
	/*Methods*/
	@GetMapping("/login/{username}/{password}")
	public ApplicationUser login(@PathVariable String username, @PathVariable String password) {
		return persistence.login(username, password);
	}
	
	@PostMapping("/register")
	public boolean register(@RequestBody ApplicationUser AU) {
		return persistence.register(AU);
	}
	
	@GetMapping("/get/users")
	public ArrayList<ApplicationUser> printUsers() {
		return persistence.getUsers();
	}
}
