package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 스프링 컨테이너와 테스트를 함께 실행한다,,,
@SpringBootTest
// 테스트 완료후에 DB에 있던 데이터를 다 지운다,,,
@Transactional
class MemberServiceIntegerationTest {

    // 테스트니까,,
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;



    @Test
    @DisplayName("회원가입")
    void join() {
        //given 이러한 상황이 주어졌을때
        Member member = new Member();
        member.setName("spring");

        //when 이것을 실행 했을때
        Long saveId = memberService.join(member);

        //then 이렇게 실행된다
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo((findMember.getName()));

    }

    @Test
    @DisplayName("중복 회원 예외")
    public void join2() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        // 회원 가입하면 IllegalStateException이 발생해야한다,,,
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

          }

}