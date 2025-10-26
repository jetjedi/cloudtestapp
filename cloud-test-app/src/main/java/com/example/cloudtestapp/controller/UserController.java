package com.example.cloudtestapp.controller;

import com.example.cloudtestapp.model.User;
import com.example.cloudtestapp.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    // List page
    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", repo.findAll());
        return "users";
    }

    // Show create form
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("formTitle", "Create User");
        return "user_form";
    }

    // Show edit form
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        User user = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user id"));
        model.addAttribute("user", user);
        model.addAttribute("formTitle", "Edit User");
        return "user_form";
    }

    // Create or update
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("formTitle", (user.getId() == null) ? "Create User" : "Edit User");
            return "user_form";
        }
        repo.save(user);
        return "redirect:/users";
    }

    // Delete
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/users";
    }
}
