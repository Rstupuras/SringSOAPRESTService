package com.Repository;


import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


import com.com.example.student.Student;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class StudentRepository {
    private static final Map<Integer, Student> students = new HashMap<>();

    @PostConstruct
    public void initData() {
        Student student1 = new Student();
        student1.setId(1);
        student1.setStudentNumber("267791");
        student1.setPassword("password");
        student1.setFirstName("Gareth");
        student1.setLastName("Bale");
        student1.setEmail("245312@email.com");

        students.put(student1.getId(), student1);

        Student student2 = new Student();
        student2.setId(2);
        student2.setStudentNumber("245312");
        student2.setPassword("password2");
        student2.setFirstName("Wayne");
        student2.setLastName("Rooney");
        student2.setEmail("245312@email.com");

        students.put(student2.getId(), student2);
        Student student3 = new Student();
        student3.setId(3);
        student3.setFirstName("Tim");
        student3.setLastName("Howard");
        student3.setStudentNumber("212154");
        student3.setPassword("password3");
        student3.setEmail("212154@email.com");
        students.put(student3.getId(), student3);
    }

    public Student findStudentByID(int id) {
        Assert.isTrue(id > 0, "The id has to be higher than 0");
        Assert.isTrue(students.get(id) != null, "No student with this id");
        return students.get(id);
    }

    public void updateStudentByID(int id, Student student) {
        Assert.isTrue(id > 0, "The id has to be higher than 0");
        Assert.isTrue(students.get(id) != null, "No student with this id");
        Student studentToUpdate = students.get(id);
        if (student.getFirstName() != null) {
            studentToUpdate.setFirstName(student.getFirstName());
        }
        if (student.getLastName() != null) {
            studentToUpdate.setLastName(student.getLastName());
        }
        if (student.getEmail() != null) {
            studentToUpdate.setEmail(student.getEmail());
        }
        if (student.getStudentNumber() != null) {
            studentToUpdate.setStudentNumber(student.getStudentNumber());
        }
        students.put(studentToUpdate.getId(), studentToUpdate);
    }

    public Student getStudentByStudentNumber(String studentNumber) {


        for (int i = 1; i < students.size(); i++) {
            if (students.get(i).getStudentNumber().equalsIgnoreCase(studentNumber)) {
                return students.get(i);
            }
        }
        return null;

    }

    public void addNewStudent(Student student) {
        students.put(student.getId(), student);
    }

    public void deleteStudentByID(int id) {
        students.remove(id);
    }
}