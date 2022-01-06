package com.dmi.api.v1.student;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/student")
public class StudentController {
    private static List<Student> STUDENTS_REPO = Arrays.asList(
            new Student(1, "John"),
            new Student(2, "Ben"),
            new Student(3, "Donald")
    );
    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable Integer studentId){
        return STUDENTS_REPO.stream().filter(student -> student.getStudentId() == studentId).findFirst().orElse(null);
    }
}
