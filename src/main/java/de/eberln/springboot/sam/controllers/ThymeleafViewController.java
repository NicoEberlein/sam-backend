package de.eberln.springboot.sam.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.eberln.springboot.sam.dao.SubscriberDAO;
import de.eberln.springboot.sam.orm.Subscriber;


@Controller
@RequestMapping("/subscribers")
public class ThymeleafViewController {

	@Autowired
	private SubscriberDAO subscriberDao;
	
	@RequestMapping
	public String subscribers(Model model) {
		
		List<Subscriber> list = subscriberDao.findAll();
		
		model.addAttribute("subscribers" , list);
		
		return "subscribers";
	}
	
	@GetMapping("/edit/{username}")
	public String edit(@PathVariable String username, Model model) {
		
		model.addAttribute("subscriber", subscriberDao.findById(username));
		
		return "edit";
	}
	
	@PostMapping("/edit/{username}")
	public String processEdit(@PathVariable String username, @Valid @ModelAttribute Subscriber sub, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			
			return "edit";
		}
		
		subscriberDao.save(sub);
		
		return "redirect:/subscribers/?successfully-changed";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		
		model.addAttribute("subscriber", new Subscriber());
		
		return "add";
	}
	
	@PostMapping("/add")
	public String processAdd(@Valid @ModelAttribute Subscriber sub, BindingResult result) {
		
		if(result.hasErrors()) {
			return "add";
		}
		
		subscriberDao.save(sub);
		
		return "redirect:/subscribers/?sucessfully-created";
		
	}
	
	@GetMapping("emergency/{username}")
	public String emergency(@PathVariable String username, Model model) {
		
		model.addAttribute(subscriberDao.findById(username));
		
		return "emergency";
		
	}
	
}
