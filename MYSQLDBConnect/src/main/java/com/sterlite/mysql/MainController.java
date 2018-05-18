package com.sterlite.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demomysql")
public class MainController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(path="/user")
	public String getUserForm(Model model) {
		model.addAttribute("user", new User());
		return "user";
	}
	
	@PostMapping(path="/user")
	public String postUserForm(@ModelAttribute User user) {
		userRepository.save(user);
		return "result";
	}
	
	@RequestMapping(path="/add")
	public @ResponseBody String addUser(@RequestParam String name,@RequestParam String email) {
		User n=new User();
		n.setName(name);
		n.setEmail(email);
		userRepository.save(n);
		return "Inserted";
	}
	
	@RequestMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@RequestMapping(path="/update")
	public @ResponseBody String updateUser(@RequestParam String id,@RequestParam String name,@RequestParam String  email) {
		User entity=userRepository.findById(new Integer(id)).get();
		entity.setEmail(email);
		entity.setName(name);
		userRepository.save(entity);
		return "Updated";
	}
	
	@RequestMapping(path="/delete")
	public @ResponseBody String deleteUser(@RequestParam String id) {
		userRepository.deleteById(new Integer(id));
		return "Deleted";
	}
	
	@RequestMapping(path="deleteall")
	public @ResponseBody String deleteAllUser() {
		userRepository.deleteAll();
		return "All User Deleted";
	}
}
