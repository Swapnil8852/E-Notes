package com.becoder.controller;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.becoder.entity.Notes;
import com.becoder.entity.User;
import com.becoder.repository.UserRepository;
import com.becoder.service.NotesService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private NotesService notesService;
	

	@ModelAttribute
	public User getUser(Principal p, Model m) {
		String email = p.getName();
		User user = userRepo.findByEmail(email);
		m.addAttribute("user", user);
		return user;
	}

	@GetMapping("/addNotes")
	public String addNotes() {
		return "add_notes";
	}

	@GetMapping("/viewNotes")
	public String viewNotes() {
		return "view_notes";
	}

	@GetMapping("/editNotes")
	public String editNotes() {
		return "edit_notes";
	}
	
	@PostMapping("/saveNotes")
	public String saveNotes(@ModelAttribute Notes notes,HttpSession session,Principal p,Model m)
	{
		notes.setDate(LocalDate.now());
		notes.setUser(getUser(p,m));
		
		Notes saveNotes = notesService.saveNotes(notes);
		if (saveNotes != null) {
			session.setAttribute("msg", "Notes Save success");
		} else {
			session.setAttribute("msg", "Something wrong on server");
		}
		return "redirect:/user/addNotes";
	}
}
