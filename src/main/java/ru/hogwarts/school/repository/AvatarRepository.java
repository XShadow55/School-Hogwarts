package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;

import java.util.List;
import java.util.Optional;
@Repository
public interface AvatarRepository extends JpaRepository<Avatar,Long> {
    Optional<Avatar> findByStudentId(Long studentId);
    List<Avatar> findAll();



}
