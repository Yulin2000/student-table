package com.example.demo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Student {
    private int id;
    private String name;
    private String sex;
    private int course;
    private String phone_number;
    private String birth;
    private String note;

    
    public Student() {
        this.id = 0;
    }
    


    //set id
    public Student setId(int id) {
        this.id = id;
        return this;
    }

    //get id
    public int  getId() {
        return this.id;
    }

    //set name
    public Student setName(String name) {
        this.name = name;
        return this;
    }

    //get name
    public String getName() {
        return this.name;
    }

    //set sex
    public Student setSex(String sex) {
        this.sex = sex;
        return this;
    }

    //get sex
    public String getSex() {
        return this.sex;
    }

    //set course
    public Student setCourse(int course) {
        this.course = course;
        return this;
    }

    //get course
    public int getCourse() {
        return this.course;
    }

    //set phone number
    public Student setPhone_number(String phone) {
        this.phone_number = phone;
        return this;
    }

    //get phone number
    public String getPhone_number() {
        return this.phone_number;
    }

    //set birth 
    public Student setBirth(Date birth) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        this.birth = dateFormat.format(birth);
        return this;
    }

    //get birth
    public String getBirth() {
        return this.birth;
    }

    //set note
    public Student setNote(String note) {
        this.note = note;
        return this;
    }

    //get note
    public String getNote() {
        return this.note;
    }

    //get student info
    public String getAll() {
        String result = "";
        result = result + "'" + this.name + "'";
        result = result + ", '" + this.sex + "'";
        result = result + ", " + Integer.toString(this.course);
        result = result + ", '" + this.phone_number + "'"; 
        result = result + ", '" + this.birth + "'";
        result = result + ", '" + this.note + "'";
        return result;
    }

}