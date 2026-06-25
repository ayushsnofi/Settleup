package com.ayush.settleUp.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayush.settleUp.dto.AddMemberRequest;
import com.ayush.settleUp.dto.CreateGroupRequest;
import com.ayush.settleUp.dto.GroupResponse;
import com.ayush.settleUp.entity.Group;
import com.ayush.settleUp.entity.GroupMember;
import com.ayush.settleUp.entity.User;
import com.ayush.settleUp.repository.GroupMemberRepository;
import com.ayush.settleUp.repository.GroupRepository;
import com.ayush.settleUp.repository.UserRepository;
import com.ayush.settleUp.service.GroupService;
// replaced wrong Service import with Spring's Service

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    
    
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;

@Override
@Transactional
public GroupResponse createGroup(
        CreateGroupRequest request,
        String email
) {

    User user =
            userRepository.findByEmail(email)
                    .orElseThrow();

    Group group =
            Group.builder()
                    .name(request.getName())
                    .createdBy(user)
                    .createdAt(LocalDateTime.now())
                    .build();

    groupRepository.save(group);

    GroupMember member =
            GroupMember.builder()
                    .group(group)
                    .user(user)
                    .joinedAt(LocalDateTime.now())
                    .build();

    groupMemberRepository.save(member);

    return GroupResponse.builder()
            .id(group.getId())
            .name(group.getName())
            .createdBy(user.getEmail())
            .build();
    }


@Override
@Transactional
public void addMember(Long groupId, AddMemberRequest request,String requesterEmail){
        Group group = groupRepository.findById(groupId).orElseThrow();

        User user=userRepository.findByEmail(request.getEmail())
                                .orElseThrow();

        if(groupMemberRepository.existsByGroupAndUser(group,user)){
                throw new RuntimeException("User Already Exists");
        }
}

@Override
public List<GroupResponse> getMyGroups(String email){
        User user=userRepository.findByEmail(email).orElseThrow();

        List<GroupMember> memberships=groupMemberRepository.findByUser(user);

        return memberships.stream()
                        .map(m-> GroupResponse.builder()
                                        .id(m.getGroup().getId())
                                        .name(m.getGroup().getName())
                                        .createdBy(m.getGroup().getCreatedBy().getEmail())
                                        .build()
                ).toList();
}


}
