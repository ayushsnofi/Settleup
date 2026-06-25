package com.ayush.settleUp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Repository;

import com.ayush.settleUp.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long>  {

}
