package com.protaskify.protaskify_api.service;
import com.protaskify.protaskify_api.model.enity.*;
import com.protaskify.protaskify_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SprintService {
    private final SprintRepository sprintRepository;
    private final StudentRepository studentRepository;
    public Sprint findLatestSprintByStudentId(String studentId) {
        Student student = studentRepository.findStudentById(studentId);
        if (student != null) {
            Sprint sprint = sprintRepository.findSprintByStudentId(studentId);
            if (sprint != null && sprint.getClasses().getId().equals(student.getClasses().getId())) {
                return sprint;
            }
        }
        return null;
    }
}