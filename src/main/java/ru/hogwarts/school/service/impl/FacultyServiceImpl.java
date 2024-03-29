package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.*;

@Service
public class FacultyServiceImpl implements FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    private long count = 0;
// Was invoked method for create faculty
    @Override
    public Faculty add(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
            return facultyRepository.save(faculty);
    }
// The method for reading faculties was called
    @Override
    public Faculty read(Long id) {
        logger.info("The method for reading faculties was called");
        return facultyRepository.findById(id).get();
    }
// A method was called to change the faculty
    @Override
    public Faculty set(Faculty faculty) {
        logger.info("A method was called to change the faculty");
        return facultyRepository.save(faculty);
    }
// A method was called to remove a faculty
    @Override
    public void remove(Long id) {
        logger.info("A method was called to remove a faculty");
        facultyRepository.deleteById(id);
    }
    // "A method was called to filter faculties
    public Collection<Faculty> filter(String color) {
        logger.info("A method was called to filter faculties");
        return facultyRepository.findByColor(color);
    }
// A method was called to search for a faculty by name and color, ignoring case
    @Override
    public Collection<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String color, String name) {
        logger.info("A method was called to search for a faculty by name and color, ignoring case");
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(color,name);
    }
// A method was called to get students belonging to this faculty
    @Override
    public List<Student> getStudents(Long id) {
        logger.info("A method was called to get students belonging to this faculty");
        return facultyRepository.findById(id).map(Faculty::getStudents).orElse(null);
    }
// Получение самого длинного названия факультета
    @Override
    public String    lengthFaculty() {
        return facultyRepository.findAll().stream().sorted(Comparator.comparing(Faculty::getName).reversed()).toList().get(0).getName();
    }
}
