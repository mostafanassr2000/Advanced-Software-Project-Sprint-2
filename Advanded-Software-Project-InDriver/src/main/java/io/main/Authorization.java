package io.main;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Authorization implements IAuthorize{
	@Override
	public ApplicationUser login(String un, String pw, IPersistence persistence) {
		return persistence.login(un, pw);
	}
	
	@Override
	
	//@PostMapping("/register/")
	public boolean register(@RequestBody ApplicationUser AU, @RequestBody IPersistence persistence) {
		return persistence.register(AU);
	}
	
	@RequestMapping("/check")
	public String sayHello1() {
		return "Hi, SANDOOO";
	}
}
