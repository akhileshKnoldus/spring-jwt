package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Student;

public interface StudentService {

	public Student addStudent(Student student);

	public List<Student> findAll();

	public Student findById(Long studnetId);

	public void deleteById(Long studnetId);

}
