package com.example.cloudtestapp.controller;

import com.example.cloudtestapp.model.Address;
import com.example.cloudtestapp.model.User;
import com.example.cloudtestapp.repository.AddressRepository;
import com.example.cloudtestapp.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/*
  This controller keeps the flow very basic.
  I list addresses for a user, I show a form to add one,
  I save it, and I can delete it. That is enough for this assignment.
*/
@Controller
@RequestMapping("/users/{userId}/addresses")
public class AddressController {

    private final AddressRepository addressRepo;
    private final UserRepository userRepo;

    public AddressController(AddressRepository addressRepo, UserRepository userRepo) {
        this.addressRepo = addressRepo;
        this.userRepo = userRepo;
    }

    // Show all addresses for a user
    @GetMapping
    public String list(@PathVariable Long userId, Model model) {
        User user = userRepo.findById(userId).orElseThrow();
        model.addAttribute("user", user);
        model.addAttribute("addresses", addressRepo.findByUserId(userId));
        return "addresses";
    }

    // Show the add address form
    @GetMapping("/new")
    public String newForm(@PathVariable Long userId, Model model) {
        User user = userRepo.findById(userId).orElseThrow();
        Address addr = new Address();
        // I pre attach the user so the form knows which user we are working with
        addr.setUser(user);
        model.addAttribute("user", user);
        model.addAttribute("address", addr);
        model.addAttribute("formTitle", "Add Address");
        return "address_form";
    }

    // Save the address
    @PostMapping("/save")
    public String save(@PathVariable Long userId,
                       @Valid @ModelAttribute("address") Address address,
                       BindingResult result,
                       Model model) {
        User user = userRepo.findById(userId).orElseThrow();

        if (result.hasErrors()) {
            // If something is wrong I just show the same form again
            model.addAttribute("user", user);
            model.addAttribute("formTitle", "Add Address");
            return "address_form";
        }

        // Make sure the address belongs to this user
        address.setUser(user);
        addressRepo.save(address);

        // Back to the list for this user
        return "redirect:/users/" + userId + "/addresses";
    }

    // Delete one address
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long userId, @PathVariable Long id) {
        addressRepo.deleteById(id);
        return "redirect:/users/" + userId + "/addresses";
    }
}
