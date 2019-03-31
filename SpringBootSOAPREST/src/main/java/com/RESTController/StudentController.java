package com.RESTController;

import com.Repository.StudentRepository;

import com.com.example.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studentsREST")
public class StudentController {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getStudentByID(@PathVariable("id") int id) {
        return studentRepository.findStudentByID(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStudentByID(@PathVariable("id") int id, @RequestBody Student student) {
        studentRepository.updateStudentByID(id,student);
    }
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewStudent(@RequestBody Student student) {
        studentRepository.addNewStudent(student);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable("id") int id)
    {
        studentRepository.deleteStudentByID(id);
    }
}
