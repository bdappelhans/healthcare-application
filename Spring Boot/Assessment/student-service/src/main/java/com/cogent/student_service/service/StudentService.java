package com.cogent.student_service.service;

import com.cogent.student_service.entity.Student;
import com.cogent.student_service.feignclients.BookFeignClient;
import com.cogent.student_service.repository.StudentRepository;
import com.cogent.student_service.request.CreateStudentRequest;
import com.cogent.student_service.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookFeignClient bookFeignClient;

    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

        Student student=new Student();
        student.setFirstName(createStudentRequest.getFirstName());
        student.setLastName(createStudentRequest.getLastName());
        student.setEmail(createStudentRequest.getEmail());

        student.setBookId(createStudentRequest.getBookId());
        student=studentRepository.save(student);

        StudentResponse studentResponse=new StudentResponse(student);
        studentResponse.setBookResponse(bookFeignClient.getById(student.getBookId()));
        return studentResponse;
    }

    public StudentResponse getStudentById(int studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        StudentResponse studentResponse = new StudentResponse(student);
        studentResponse.setBookResponse(bookFeignClient.getById(student.getBookId()));
        return studentResponse;
    }
}
