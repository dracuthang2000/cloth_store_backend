package com.ptithcm.clothing_store.repository;

import com.ptithcm.clothing_store.model.entity.Staff;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long>, QuerydslPredicateExecutor<Staff> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,value = "staff_graphic")
    Staff findByAccountStaff_Username(String username);
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,value = "staff_graphic")
    List<Staff> findAllByAccountStaff_RoleId(Long id);
}
