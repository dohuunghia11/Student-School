package com.codegym.controller;

import com.codegym.model.School;
import com.codegym.model.Student;
import com.codegym.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SchoolController {

    @Autowired
    public SchoolService schoolService;

    @GetMapping("/schools")
    public ModelAndView listSchool(){
        Iterable<School> schools = schoolService.findAll();
        ModelAndView modelAndView = new ModelAndView("/school/list");
        modelAndView.addObject("schools", schools);
        return modelAndView;
    }

    @GetMapping("/create-school")
    public ModelAndView showCreateSchool(){
        ModelAndView modelAndView = new ModelAndView("/school/create");
        modelAndView.addObject("school", new School());
        return modelAndView;
    }

    @PostMapping("/create-school")
    public ModelAndView saveSchool(@Validated @ModelAttribute("school")School school, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("/school/create");
            return modelAndView;
        }
        schoolService.save(school);

        ModelAndView modelAndView = new ModelAndView("/school/create");
        modelAndView.addObject("school", school);
        modelAndView.addObject("message", "Create new school successfully");
        return modelAndView;
    }

    @GetMapping("/edit-school/{id}")
    public ModelAndView showEditSchool(@PathVariable Integer id){
        School school = schoolService.findById(id);
        if(school != null){
            ModelAndView modelAndView = new ModelAndView("/school/edit");
            modelAndView.addObject("school", school);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-school")
    public ModelAndView editSchool(@ModelAttribute("school") School school){
        schoolService.save(school);

        ModelAndView modelAndView = new ModelAndView("/school/edit");
        modelAndView.addObject("school", school);
        modelAndView.addObject("message", "Update School successfully");
        return modelAndView;
    }

    @GetMapping("/delete-school/{id}")
    public ModelAndView showDeleteSchool(@PathVariable Integer id){
        School school = schoolService.findById(id);

        if(school != null){
            ModelAndView modelAndView = new ModelAndView("/school/delete");
            modelAndView.addObject("school", school);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-school")
    public String deleteSchool(@ModelAttribute("school") School school){
        schoolService.remove(school.getId());
        return "redirect:schools";
    }
}
