package com.Security;

import com.Repository.StudentRepository;

import com.com.example.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SecurityService implements UserDetailsService {

    private final
    StudentRepository students;

    @Autowired
    public SecurityService(StudentRepository students) {
        this.students = students;
    }

    @Override
    public UserDetails loadUserByUsername(String studentNumber) throws UsernameNotFoundException {

        Student student = students.getStudentByStudentNumber(studentNumber);

        if (student == null){
            throw new UsernameNotFoundException(studentNumber + " was not found");
        }

        return new org.springframework.security.core.userdetails.User(
                student.getStudentNumber(),
                student.getPassword(),
                AuthorityUtils.createAuthorityList(student.getStudentNumber())
        );
    }
}