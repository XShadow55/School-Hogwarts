package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }
    @PostMapping
    public Student add(@RequestBody Student student) {
        return service.add(student);
    }
    @GetMapping("{id}")
    public ResponseEntity<Student> read(@PathVariable Long id) {
        Student student = service.read(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);

    }
    @PutMapping
    public ResponseEntity<Student>  set(@RequestBody Student student) {
        Student foundStudent = service.set(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }
    @DeleteMapping("{id}")
    public  ResponseEntity<Object> remove(@PathVariable Long  id) {
        service.remove(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<Collection<Student>> filterStudents(@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(service.filter(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
}
