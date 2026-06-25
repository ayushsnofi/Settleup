package com.ayush.settleUp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.settleUp.dto.AddMemberRequest;
import com.ayush.settleUp.dto.CreateGroupRequest;
import com.ayush.settleUp.dto.GroupResponse;
import com.ayush.settleUp.service.GroupService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {
    
    private final GroupService groupService;


    @PostMapping()
    public ResponseEntity<GroupResponse> createGroup(@RequestBody CreateGroupRequest request,Authentication authentication) {
        

        return ResponseEntity.ok(
            groupService.createGroup(request, authentication.getName())
        );
    }

    @PostMapping("/{id}/members")
    public ResponseEntity<String> addMembers(@PathVariable Long id,@RequestBody AddMemberRequest request, Authentication authentication) {
        
        groupService.addMember(id, request, authentication.getName());

        return ResponseEntity.ok("Member Added");
    }


    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    
    
}
