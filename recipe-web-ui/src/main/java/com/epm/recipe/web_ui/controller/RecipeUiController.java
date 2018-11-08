package com.epm.recipe.web_ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeUiController {
    private static final String commonUrlPattern = "recipe/";

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("operation", "Add new recipe");
        model.addAttribute("pattern", commonUrlPattern + "add");
        return "two_param_request";
    }

    @GetMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("operation", "Delete recipe");
        model.addAttribute("pattern", commonUrlPattern + "delete");
        return "one_param_request";
    }

    @GetMapping("/update")
    public String updateById(Model model) {
        model.addAttribute("operation", "Update recipe");
        model.addAttribute("pattern", commonUrlPattern + "update");
        return "two_param_request";
    }

    @GetMapping("/get")
    public String getById(Model model) {
        model.addAttribute("operation", "Get recipe by id");
        model.addAttribute("pattern", commonUrlPattern + "get");
        return "get";
    }
}
