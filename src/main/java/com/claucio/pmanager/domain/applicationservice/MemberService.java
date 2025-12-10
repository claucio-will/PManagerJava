package com.claucio.pmanager.domain.applicationservice;

import com.claucio.pmanager.domain.entity.Member;
import com.claucio.pmanager.domain.exception.DuplicateMemberException;
import com.claucio.pmanager.domain.exception.MemberNotFoundException;
import com.claucio.pmanager.domain.respository.MemberRepository;
import com.claucio.pmanager.infrastructure.DTO.SaveMemberDataDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member createMember(SaveMemberDataDTO saveMemberData) {
        if (existsMemberWithEmail(saveMemberData.getEmail(), null)) {
            throw new DuplicateMemberException(saveMemberData.getEmail());
        }
        Member member = Member.builder()
                .name(saveMemberData.getName())
                .email(saveMemberData.getEmail())
                .secret(UUID.randomUUID().toString())
                .deleted(false)
                .build();
        memberRepository.save(member);
        return member;
    }

    public Member loadMemberById(String memberId) {
        return memberRepository
                .findByIdAndDeleted(memberId, false)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }

    @Transactional
    public void deleteMember(String memberId) {
        Member member = loadMemberById(memberId);
        member.setDeleted(true);
    }


    @Transactional
    public Member updateMember(String memberId, SaveMemberDataDTO saveMemberDataDTO) {
        if (existsMemberWithEmail(saveMemberDataDTO.getEmail(), memberId)) {
            throw new DuplicateMemberException(saveMemberDataDTO.getEmail());
        }

        Member member = loadMemberById(memberId);
        member.setName(saveMemberDataDTO.getName());
        member.setEmail(saveMemberDataDTO.getEmail());
        return member;


    }

    private boolean existsMemberWithEmail(String email, String idToExclude) {
        return memberRepository
                .findByEmailAndDeleted(email, false)
                .filter(m -> !Objects.equals(m.getId(), idToExclude))
                .isPresent();
    }

}
