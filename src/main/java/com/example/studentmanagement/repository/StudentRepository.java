package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("select year(s.dateNaissance), count(s) from Student s group by year(s.dateNaissance) order by year(s.dateNaissance)")
    List<Object[]> findNbrStudentByYear();
}
