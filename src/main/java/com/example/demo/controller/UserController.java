package com.example.demo.controller;

import com.example.demo.SQL;
import com.example.demo.Student;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import java.sql.Date;
import java.util.List;

//make this as controller

@Controller
@RequestMapping(path="/user")
public class UserController {

    //autowiring user repository
    @Autowired
    UserRepository userRepository;

    //print the input page
    @GetMapping
    public String userGet(Model model) {
        model.addAttribute("SQL", new SQL());
        List<Student> oldStudentList = userRepository.select("select * from students;");
        model.addAttribute("oldStudentList", oldStudentList);
		return "input";
    }

    //print the output page
    @PostMapping
    public String userPost(@ModelAttribute SQL sql, Model model) {
        String updateSQL = sql.getUpdate();
        String selectSQL = sql.getSelect();
        userRepository.update(updateSQL);
        List<Student> newStudentList = userRepository.select(selectSQL);
        model.addAttribute("newStudentList", newStudentList);
        return "output";
    }
    

}