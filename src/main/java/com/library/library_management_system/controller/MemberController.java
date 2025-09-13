package com.library.library_management_system.controller;

import com.library.library_management_system.dto.MemberRequest;
import com.library.library_management_system.dto.MemberResponse;
import com.library.library_management_system.entity.Member;
import com.library.library_management_system.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        log.info("GET /api/members - Fetching all members");
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long memberId) {
        log.info("GET /api/members/{} - Fetching member by ID", memberId);
        Member member = memberService.getMemberById(memberId);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/{memberId}/details")
    public ResponseEntity<MemberResponse> getMemberDetails(@PathVariable Long memberId) {
        log.info("GET /api/members/{}/details - Fetching member details with books", memberId);
        MemberResponse memberResponse = memberService.getMemberWithBooks(memberId);
        return ResponseEntity.ok(memberResponse);
    }

    @PostMapping
    public ResponseEntity<Member> addMember(@Valid @RequestBody MemberRequest memberRequest) {
        log.info("POST /api/members - Adding new member");
        Member savedMember = memberService.addMember(memberRequest);
        return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<Member> updateMember(@PathVariable Long memberId,
            @Valid @RequestBody MemberRequest memberRequest) {
        log.info("PUT /api/members/{} - Updating member", memberId);
        Member updatedMember = memberService.updateMember(memberId, memberRequest);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        log.info("DELETE /api/members/{} - Deleting member", memberId);
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Member>> searchMembers(@RequestParam String name) {
        log.info("GET /api/members/search - Searching members by name: '{}'", name);
        List<Member> members = memberService.searchMembersByName(name);
        return ResponseEntity.ok(members);
    }
}