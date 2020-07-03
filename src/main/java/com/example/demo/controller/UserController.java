package com.example.demo.controller;

import com.example.demo.Student;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import java.sql.Date;
import java.util.List;

//make this as controller

@Controller
public class UserController {

    //autowiring user repository
    @Autowired
    UserRepository userRepository;

    //display the home page
    @GetMapping("/home")
    public String userGet(Model model) {
        List<Student> oldStudentList = userRepository.select("select * from students;");
        model.addAttribute("oldStudentList", oldStudentList);
        //for create model
        model.addAttribute("newStudent", new Student());
		return "home";
    }

    //refresh the home page
    @PostMapping("/home")
    public String refreshHome(@RequestParam(value = "button", required = false) String button, 
                              //create model
                              @ModelAttribute("newStudent") Student newOne,
                              //modify model
                              @RequestParam(value = "id", required = false) int[] id,
                              @RequestParam(value = "name", required = false) String[] name,
                              @RequestParam(value = "sex", required = false) String[] sex,
                              @RequestParam(value = "course", required = false) int[] course,
                              @RequestParam(value = "phone_number", required = false) String[] phone_number,
                              @RequestParam(value = "birth", required = false) String[] birth,
                              @RequestParam(value = "note", required = false) String[] note,
                              //delete model
                              @RequestParam(value = "sql", required = false) String sql,
                              //page search model
                              @RequestParam(value = "page", required = false) Integer page,
                              //search model
                              @RequestParam(value = "sex_search", required = false) String sex_search,
                              @RequestParam(value = "name_search", required = false) String name_search,
                              @RequestParam(value = "phone_search", required = false) String phone_search,
                              @RequestParam(value = "birth_search", required = false) String birth_search,
                              //model
                              Model model) {

        //case1: submit create form
        if(button.equals("Create")) {
            String insert_sql = "insert into students(name,sex,class,phone_number,birth,note) values(";
            insert_sql = insert_sql + newOne.getAll() + ");";
            userRepository.update(insert_sql);
            model.addAttribute("createSubmitted", true); //tell home this is child page
        }

        //case2: submit modify form
        else if(button.equals("Modify")) {
            String update_sql = "Unchanged";
            for(int i = 0; i < id.length; ++i) {
                update_sql = "update students set";
                update_sql = update_sql + " name = '" + name[i] + "',";
                update_sql = update_sql + " sex = '" + sex[i] + "',";
                update_sql = update_sql + " class = " + Integer.toString(course[i]) + ",";
                update_sql = update_sql + " phone_number = '" + phone_number[i] + "',";
                update_sql = update_sql + " birth = '" + birth[i] + "',";
                update_sql = update_sql + " note = '" + note[i] + "'";
                update_sql = update_sql + " where id = " + Integer.toString(id[i]) + ";";
                userRepository.update(update_sql);
            }
            model.addAttribute("modifySubmitted", true); //tell home this is child page
        }

        //case3: submit delete form
        else if(button.equals("Delete")) {
            userRepository.update(sql);
        }

        //case4: get page search form
        else if(button.equals("page_search")) {
            int pageValue = page.intValue();
            int targetPage = (pageValue - 1) * 10;
            String page_sql = "select * from students limit 10 offset " + Integer.toString(targetPage) + ";";
            List<Student> oldStudentList = userRepository.select(page_sql);
            model.addAttribute("oldStudentList", oldStudentList);
            model.addAttribute("newStudent", new Student());
            return "home";
        }

        //case5: submit search form
        else if(button.equals("Search")) {
            String search_sql = "select * from students where ";

            //check sex
            if(sex_search.equals("male")) {
                search_sql = search_sql + "sex = 'm'";
            }
            else {
                search_sql = search_sql + "sex = 'f'";
            }
            
            //check name
            if(!name_search.isEmpty()) {
                search_sql = search_sql + " and SOUNDEX(name) = SOUNDEX('" + name_search + "')";
            }
        
            //check phone number
            if(!phone_search.isEmpty()) {
                search_sql = search_sql + " and phone_number = '" + phone_search + "'";
            }
        
            //check birthday
            if(!birth_search.isEmpty()) {
                search_sql = search_sql + " and birth = '" + birth_search + "'";
            }
        
            search_sql = search_sql + ";";
            List<Student> oldStudentList = userRepository.select(search_sql);
            model.addAttribute("oldStudentList", oldStudentList);
            model.addAttribute("newStudent", new Student());
            model.addAttribute("search_result", true);
            return "home";
        }
        List<Student> oldStudentList = userRepository.select("select * from students;");
        model.addAttribute("oldStudentList", oldStudentList);
        model.addAttribute("newStudent", new Student());
        return "home";                          

    }

    //display the delete page
    @PostMapping("/delete")
    public String updateStudent(@RequestParam(value = "checked_ones", required = false) int[] checked_ones,
                                Model model) {
        //select all cheked data                            
        String sql = "select * from students where ";
        for(int i = 0; i < checked_ones.length; ++i) {
            if(i == 0) {
                sql = sql + "id = " + Integer.toString(checked_ones[i]);
            }
            else {
                sql = sql + " or id = " + Integer.toString(checked_ones[i]);
            }
        }
        sql = sql + ";";
        //set delete sql
        String delete_sql = sql.substring(8);
        delete_sql = "delete" + delete_sql;
        model.addAttribute("delete_sql", delete_sql);
        model.addAttribute("delete_ones", userRepository.select(sql));
        return "delete";
    }

    //display the select result
    @PostMapping("/select/result")
    public String selectPost(@RequestParam(value = "sex", required = false) String sex,
                             @RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "birth", required = false) String birth,
                             Model model) {
    String sql = "select * from students where ";

    //check sex
    if(sex.equals("male")) {
        sql = sql + "sex = 'm'";
    }
    else {
        sql = sql + "sex = 'f'";
    }
    
    //check name
    if(!name.isEmpty()) {
        sql = sql + " and SOUNDEX(name) = SOUNDEX('" + name + "')";
    }

    //check phone number
    if(!phone.isEmpty()) {
        sql = sql + " and phone_number = '" + phone + "'";
    }

    //check birthday
    if(!birth.isEmpty()) {
        sql = sql + " and birth = '" + birth + "'";
    }

    sql = sql + ";";
    List<Student> newStudentList = userRepository.select(sql);
    model.addAttribute("newStudentList", newStudentList);
    return "result";
    }

}