package com.codegym.controller;

import com.codegym.model.School;
import com.codegym.model.Student;
import com.codegym.service.SchoolService;
import com.codegym.service.StudentService;
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
public class StudentController {

    @Autowired
    public StudentService studentService;

    @Autowired
    public SchoolService schoolService;

    @ModelAttribute("schools")
    public Iterable<School> schools(){
        return schoolService.findAll();
    }

    @GetMapping("/create-student")
    public ModelAndView showCreateStudent(){
        ModelAndView modelAndView = new ModelAndView("/student/create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveStudent(@Validated @ModelAttribute("student") Student student, BindingResult bindingResult){
        new Student().validate(student, bindingResult);
        ModelAndView modelAndView = new ModelAndView("/student/create");

        if(!bindingResult.hasFieldErrors()){
            studentService.save(student);
            modelAndView.addObject("student", student);
            modelAndView.addObject("message", "Congratulations! Create new Student successfully");
            return modelAndView;
        }
        return modelAndView;
    }

    @GetMapping("/students")
    public ModelAndView listStudents(){
        Iterable<Student> students = studentService.findAll();
        ModelAndView modelAndView = new ModelAndView("/student/list");
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/edit-student/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id){
        Student student = studentService.findById(id);
        if(student != null){
            ModelAndView modelAndView = new ModelAndView("/student/edit");
            modelAndView.addObject("student", student);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-student")
    public ModelAndView updateStudent(@Validated @ModelAttribute("student") Student student, BindingResult bindingResult){
        new Student().validate(student, bindingResult);
        ModelAndView modelAndView = new ModelAndView("/student/edit");

        if(!bindingResult.hasFieldErrors()){
            studentService.save(student);
            modelAndView.addObject("student", student);
            modelAndView.addObject("message", "Congratulations! Student updated successfully");
            return modelAndView;
        }
        return modelAndView;
    }

    @GetMapping("/delete-student/{id}")
    public ModelAndView showDeleteForm(@PathVariable Integer id){
        Student student = studentService.findById(id);

        if(student != null){
            ModelAndView modelAndView = new ModelAndView("/student/delete");
            modelAndView.addObject("student", student);
            modelAndView.addObject("message", "Student delete successfully");
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-student")
    public String deleteStudent(@ModelAttribute("student") Student student){
        studentService.remove(student.getId());
        return "redirect:students";
    }

}
