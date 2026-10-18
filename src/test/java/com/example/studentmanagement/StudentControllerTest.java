package com.example.studentmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.studentmanagement.controller.StudentController;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
    @Mock StudentService service;
    @InjectMocks StudentController controller;

    @Test void savesStudent() {
        Student student = new Student("LACHGAR", "Mohamed", LocalDate.of(1985, 9, 1));
        student.setId(1);
        when(service.save(any(Student.class))).thenReturn(student);
        assertEquals(HttpStatus.CREATED, controller.save(student).getStatusCode());
        assertEquals("LACHGAR", controller.save(student).getBody().getNom());
    }

    @Test void deletesExistingStudent() {
        when(service.delete(1)).thenReturn(true);
        assertEquals(HttpStatus.NO_CONTENT, controller.delete(1).getStatusCode());
    }

    @Test void reportsMissingStudent() {
        when(service.delete(99)).thenReturn(false);
        assertEquals(HttpStatus.NOT_FOUND, controller.delete(99).getStatusCode());
    }

    @Test void listsStudents() {
        when(service.findAll()).thenReturn(List.of(new Student(), new Student()));
        assertEquals(2, controller.findAll().getBody().size());
    }

    @Test void countsStudents() {
        when(service.countStudents()).thenReturn(10L);
        assertEquals(10L, controller.countStudent().getBody());
    }

    @Test void countsByYear() {
        when(service.findNbrStudentByYear()).thenReturn(List.<Object[]>of(new Object[]{1985, 2L}));
        assertEquals(1985, controller.findByYear().getBody().get(0)[0]);
    }
}
