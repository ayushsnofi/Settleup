package com.ayush.settleUp.service;

import java.util.List;


import com.ayush.settleUp.dto.AddMemberRequest;
import com.ayush.settleUp.dto.CreateGroupRequest;
import com.ayush.settleUp.dto.GroupResponse;


public interface GroupService {

    GroupResponse createGroup(CreateGroupRequest request,String email);

    void addMember(Long groudId,AddMemberRequest request,String email);

    List<GroupResponse> getMyGroups(String email);
}
