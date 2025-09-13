package com.library.library_management_system.service;

import com.library.library_management_system.dto.MemberRequest;
import com.library.library_management_system.dto.MemberResponse;
import com.library.library_management_system.entity.Member;
import com.library.library_management_system.exception.ResourceNotFoundException;
import com.library.library_management_system.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

	private final MemberRepository memberRepository;

	public List<Member> getAllMembers() {
		log.info("Fetching all members");
		return memberRepository.findAll();
	}

	public Member getMemberById(Long memberId) {
		log.info("Fetching member with ID: {}", memberId);
		return memberRepository.findById(memberId)
				.orElseThrow(() -> new ResourceNotFoundException("Member", "ID", memberId));
	}

	public Member addMember(MemberRequest memberRequest) {
		log.info("Adding new member: {}", memberRequest.getName());
		Member member = Member.builder()
				.name(memberRequest.getName())
				.build();
		return memberRepository.save(member);
	}

	public Member updateMember(Long memberId, MemberRequest memberRequest) {
		log.info("Updating member with ID: {}", memberId);
		Member existingMember = getMemberById(memberId);
		existingMember.setName(memberRequest.getName());
		return memberRepository.save(existingMember);
	}

	public void deleteMember(Long memberId) {
		log.info("Deleting member with ID: {}", memberId);
		Member member = getMemberById(memberId);
		memberRepository.delete(member);
	}

	public List<Member> searchMembersByName(String name) {
		log.info("Searching members by name: {}", name);
		return memberRepository.findByNameContainingIgnoreCase(name);
	}

	public MemberResponse getMemberWithBooks(Long memberId) {
		Member member = getMemberById(memberId);
		return MemberResponse.builder()
				.memberId(member.getMemberId())
				.name(member.getName())
				.borrowedBooksCount(member.getBorrowedBooksCount())
				.borrowedBookTitles(member.getBorrowedBooks().stream()
						.map(book -> book.getTitle())
						.collect(Collectors.toList()))
				.build();
	}
}