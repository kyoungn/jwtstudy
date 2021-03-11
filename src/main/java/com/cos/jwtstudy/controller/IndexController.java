package com.cos.jwtstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("/api/v1")
public class IndexController {

	@GetMapping("/")
	public String index(Model model) {
		
		return "index";
	}
	
	@GetMapping("/home")
	public @ResponseBody String home() {
		return "<h1>home</h1>";
	}
	

	@PostMapping("/token")
	public @ResponseBody String token() {
		return "<h1>token</h1>";
	}
	
	@GetMapping("/api/v1/user")
	public @ResponseBody String user() {
		return "<h1>user</h1>";
	}
	

	@GetMapping("/api/v1/manager")
	public @ResponseBody String manager() {
		return "<h1>manager</h1>";
	}
	

	@GetMapping("/api/v1/admin")
	public @ResponseBody String admin() {
		return "<h1>admin</h1>";
	}
}
