package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.Group;
import com.protaskify.protaskify_api.model.enity.Invitation;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.repository.GroupRepository;
import com.protaskify.protaskify_api.repository.InvitationRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvitationService {
    private final InvitationRepository invitationRepository;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    public void invite(Invitation invitation, Long groupId, String studentId){
        Group group = groupRepository.findById(groupId).orElse(null);
        Student student = studentRepository.findStudentById(studentId);

        invitation.setGroup(group);
        invitation.setStudent(student);
        invitationRepository.save(invitation);
    }

    public List<Invitation> getInvitations (String studentId){
        return invitationRepository.findInvitationsByStudentId(studentId);
    }

    public void deleteInvitation (Long invitationId){
        Invitation invitation = null;
        if (invitationId != null) {
            invitation = invitationRepository.findInvitationById(invitationId);
        }
        invitationRepository.delete(invitation);
    }
}
