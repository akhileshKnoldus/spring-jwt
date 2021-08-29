package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StudentRepository;
import com.example.demo.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public Student findById(Long id) {
		Optional<Student> student = studentRepository.findById(id);
		if (student.isPresent())
			return student.get();
		return null;
	}

	@Override
	public void deleteById(Long studnetId) {
		  studentRepository.deleteById(studnetId);
		
	}
}
