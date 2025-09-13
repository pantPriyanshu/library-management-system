package com.library.library_management_system.repository;

import com.library.library_management_system.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m FROM Member m WHERE m.name ILIKE %:name%")
    List<Member> findByNameContainingIgnoreCase(String name);
}