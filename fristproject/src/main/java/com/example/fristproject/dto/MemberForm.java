package com.example.fristproject.dto;

import com.example.fristproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter

public class MemberForm {
    private Long id;
    private String email;
    private String password;

    // 검색 테스트
    private String search;

    public Member toEntity() {
        return new Member(id, email, password);
    }
}
