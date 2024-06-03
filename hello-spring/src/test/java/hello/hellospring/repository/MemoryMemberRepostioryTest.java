package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepostioryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // test가 끝나고 실행이 된다,,,
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    @DisplayName("테스트1")
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // Optional<Member>에서 get()해야 값을 가져올 수 있다,,,
        Member result = repository.findById(member.getId()).get();
        // Assertions.assertEquals(member, null);
        assertThat(member).isEqualTo(result);
        assertThat(member).isSameAs(result);


    }

    @Test
    @DisplayName("테스트2")
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    @DisplayName("테스트3")
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }


}
