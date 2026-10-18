package com.example.studentmanagement.service;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student save(Student student) { return repository.save(student); }

    public boolean delete(int id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    public List<Student> findAll() { return repository.findAll(); }
    public long countStudents() { return repository.count(); }

    @Transactional(readOnly = true)
    public List<Object[]> findNbrStudentByYear() { return repository.findNbrStudentByYear(); }
}
