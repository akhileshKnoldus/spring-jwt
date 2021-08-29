package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AuthRequest;
import com.example.demo.entity.Student;
import com.example.demo.jwtconfig.AuthResponse;
import com.example.demo.jwtconfig.JwtUtil;
import com.example.demo.service.StudentService;
import com.example.demo.service.UserDetailService;

@RestController
//@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private JwtUtil jwtUtilToken;

	@Autowired
	private UserDetailService userDetailService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(
			@RequestBody AuthRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUserName());

		final String jwt = jwtUtilToken.generateToken(userDetails);

		return ResponseEntity.ok(new AuthResponse(jwt));
	}

	@PostMapping("save")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		Student st = studentService.addStudent(student);
		return new ResponseEntity<Student>(st, HttpStatus.CREATED);
	}

	@GetMapping("/all-students")
	public ResponseEntity<List<Student>> getStudents() {
		List<Student> students = studentService.findAll();
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}

	@GetMapping("/{studnetId}")
	public ResponseEntity<Student> getStudentById(@PathVariable("studnetId") Long studnetId) {
		Student student = studentService.findById(studnetId);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{studnetId}")
	public ResponseEntity<Void> deleteByStudentId(@PathVariable("studnetId") Long studnetId) {
		studentService.deleteById(studnetId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	@PutMapping("update")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		Student st = studentService.addStudent(student);
		return new ResponseEntity<Student>(st, HttpStatus.CREATED);
	}
	@PatchMapping("update")
	public ResponseEntity<Student> updateStudentData(@RequestBody Student student) {
		Student st = studentService.addStudent(student);
		return new ResponseEntity<Student>(st, HttpStatus.CREATED);
	}
}
