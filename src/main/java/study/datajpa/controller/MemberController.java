package study.datajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id){
        Member member = memberRepository.findById(id).get();
        return  member.getUsername();
    }

    @GetMapping("/members2/{id}")
    public String findMember(@PathVariable("id") Member member){
        return  member.getUsername();
    }

    @GetMapping("/members")
    public Page<MemberDto> list(Pageable pageable){
        return memberRepository.findAll(pageable).map(MemberDto::new);
    }

    @GetMapping("/members2")
    public Page<MemberDto> list2(Pageable pageable){
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "username"));
        Page<Member> data = memberRepository.findAll(pageable);
        Page<MemberDto> map = data.map(member -> new MemberDto(member));
        return map;
    }

    @GetMapping("/members3")
    public Page<MemberDto> list3(study.datajpa.pageentity.PageRequest pageable) {
        return  memberRepository.findAll(pageable.of()).map(MemberDto::new);
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 30; i++) {
            memberRepository.save(new Member("user" + i, i));
        }
    }
}