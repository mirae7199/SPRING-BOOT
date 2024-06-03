package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id 세팅
        store.put(member.getId(), member); // store map에 저장이 된다,,
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // ofNullable을 감싸면 NPE 방지
    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream()
                .filter(member -> member.getName().equals(name))
               // filter에서 찾으면 반환 findAny
                .findAny();

    }

    @Override
    public List<Member> findAll() {
        // ArrayList<Member> members = new ArrayList<>(store.values());
        // new ArrayList<>([Member값])
        // store.values()는 Member값이 모두 반환된다
        return new ArrayList<>(store.values());

    }

    public void clearStore() {
        store.clear();
    }
}
