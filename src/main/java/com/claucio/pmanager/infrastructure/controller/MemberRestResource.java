package com.claucio.pmanager.infrastructure.controller;

import com.claucio.pmanager.domain.applicationservice.MemberService;
import com.claucio.pmanager.domain.applicationservice.ProjectService;
import com.claucio.pmanager.domain.entity.Member;
import com.claucio.pmanager.domain.entity.Project;
import com.claucio.pmanager.infrastructure.DTO.MemberDTO;
import com.claucio.pmanager.infrastructure.DTO.ProjectDTO;
import com.claucio.pmanager.infrastructure.DTO.SaveMemberDataDTO;
import com.claucio.pmanager.infrastructure.DTO.SaveProjectDataDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.claucio.pmanager.infrastructure.controller.RestConstants.PATH_MEMBERS;
import static com.claucio.pmanager.infrastructure.controller.RestConstants.PATH_PROJECTS;


@RestController
@RequestMapping(PATH_MEMBERS)
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class MemberRestResource {


    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@RequestBody @Validated SaveMemberDataDTO saveMemberDataDTO) {
        Member member = memberService.createMember(saveMemberDataDTO);
        return ResponseEntity.
                created(URI.create(PATH_MEMBERS + member.getId()))
                .body(MemberDTO.create(member));

    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> loadMember(@PathVariable("id") String memberId) {
        Member member = memberService.loadMemberById(memberId);
        return ResponseEntity.ok(MemberDTO.create(member));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") String memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> UpdateMember(
            @PathVariable("id") String memberId,
            @Valid @RequestBody SaveMemberDataDTO saveMemberData) {
        Member member = memberService.updateMember(memberId, saveMemberData);
        return ResponseEntity.ok(MemberDTO.create(member));
    }

}
