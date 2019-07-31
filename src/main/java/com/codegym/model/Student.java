package com.codegym.model;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Students")
@Component
public class Student implements Validator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 3, max = 30)
    private String name;

    @Min(18)
    private int age;

    @NotEmpty
    private String address;
    private String sex;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String numberPhone;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    public Student(){}

    public Student(String name, int age, String address, String sex ,String email, String numberPhone){
        this.name = name;
        this.age = age;
        this.address = address;
        this.sex = sex;
        this.email = email;
        this.numberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", school=" + school +
                '}';
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Student.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Student student = (Student) target;
        String numberPhone = student.getNumberPhone();
        ValidationUtils.rejectIfEmpty(errors, "numberPhone", "numberPhone.empty");
        if (numberPhone.length()>11 || numberPhone.length()<10){
            errors.rejectValue("numberPhone", "numberPhone.length");
        }
        if (!numberPhone.startsWith("0")){
            errors.rejectValue("numberPhone", "numberPhone.startsWith");
        }
        if (!numberPhone.matches("(^$|[0-9]*$)")){
            errors.rejectValue("numberPhone", "numberPhone.matches");
        }
    }
}
