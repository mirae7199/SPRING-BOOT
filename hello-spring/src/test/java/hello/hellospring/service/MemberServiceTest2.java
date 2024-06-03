package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceTest2 {

    public MemberService memberService;
    MemoryMemberRepository memberRepository;
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    @DisplayName("회원가입")
    public void join() {
        Member member1 = new Member();
        member1.setName("Kim Mirae");


        memberService.join(member1);
        assertThat(member1.getName()).isEqualTo("Kim Mirae");

    }

    @Test
    @DisplayName("전체 회원 조회")
    public void findMembers() {
        Member member = new Member();
        member.setName("Kim Mirae");
        memberService.join(member);

        Member member2 = new Member();
        member2.setName("Kim Daho");
        memberService.join(member2);

        List<Member> members = memberService.findMembers();
        assertThat(members.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("회원 조회")
    public void findOne() {
        Member member = new Member();
        member.setName("Kim Mirae");
        memberService.join(member);

        Member member2 = new Member();
        member2.setName("Kim Daho");
        memberService.join(member2);

        Member exM = memberRepository.findById(member.getId()).get();

        assertThat(member).isEqualTo(exM);
    }


}
