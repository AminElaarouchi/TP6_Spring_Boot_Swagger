package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) { this.service = service; }

    @Operation(summary = "Créer ou mettre à jour un étudiant")
    @PostMapping("/save")
    public ResponseEntity<Student> save(@Valid @RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(student));
    }

    @Operation(summary = "Supprimer un étudiant par identifiant")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Lister tous les étudiants")
    @GetMapping("/all")
    public ResponseEntity<List<Student>> findAll() { return ResponseEntity.ok(service.findAll()); }

    @Operation(summary = "Compter les étudiants")
    @GetMapping("/count")
    public ResponseEntity<Long> countStudent() { return ResponseEntity.ok(service.countStudents()); }

    @Operation(summary = "Compter les étudiants par année de naissance")
    @GetMapping("/byYear")
    public ResponseEntity<List<Object[]>> findByYear() { return ResponseEntity.ok(service.findNbrStudentByYear()); }
}
