package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.demo.Student;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    //use properties from application.properties to automatically create jdbc
    //template object
    @Autowired
    JdbcTemplate jdbcTemplate;

    //return list of select results
    public List<Student> select(String sql) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        List<Student> studentList = new ArrayList<Student>();
        for(Map<String, Object> map : list) {
            Student student = new Student();
            student = student.setId((int) map.get("id"))
                             .setName((String) map.get("name"))
                             .setSex((String) map.get("sex"))
                             .setCourse((int) map.get("class"))
                             .setPhone_number((String) map.get("phone_number"))
                             .setBirth((Date) map.get("birth"))
                             .setNote((String) map.get("note"));
            studentList.add(student);
        }
        return studentList;
    }

    //update the database
    public void update(String sql) {
        if(sql.equals("Nah")) {
            return;
        }
        jdbcTemplate.update(sql);
    }

}