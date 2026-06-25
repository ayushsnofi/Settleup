package com.ayush.settleUp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayush.settleUp.entity.Group;
import com.ayush.settleUp.entity.GroupMember;
import com.ayush.settleUp.entity.User;

import java.lang.reflect.Member;
import java.util.List;


@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember,Long>{

    List<GroupMember> findByUser(User user);

    List<GroupMember> findByGroup(Group group);

    boolean existsByGroupAndUser(
        Group group,
        User user
    );

    

}
