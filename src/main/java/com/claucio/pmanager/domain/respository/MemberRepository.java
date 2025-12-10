package com.claucio.pmanager.domain.respository;

import com.claucio.pmanager.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {


    Optional<Member> findByIdAndDeleted(String id, Boolean deleted);

    Optional<Member> findByEmailAndDeleted(String email, Boolean deleted);
}
