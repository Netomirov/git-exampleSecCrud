package com.example.example4test.controller;

import com.example.example4test.entity.User;
import com.example.example4test.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/")
    public String vieHomePage(Model model) {
        return findPaginated(1, "name", "asc", model);
    }

    @GetMapping(value = "/home")
    public String project(Model model, @Param("keyword") String keyword) {
        List<User> listUser = userService.getAllUser(keyword);
        model.addAttribute("listUser", listUser);
        model.addAttribute("keyword", keyword);
        return "home";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "/registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
         if(result.hasErrors()) {
             model.addAttribute("user", user);
             model.addAttribute("errors", result.getAllErrors());
             return "registration";
         }

        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping(value = "/user-delete/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

    @GetMapping(value = "/user-update/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public String userUpdateForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/user-update";
    }

    @PostMapping(value = "/user-update")
    public String userUpdate(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<User> listUser = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listUser", listUser);
        return "home";
    }




}
