package com.example.fristproject.dto;

import com.example.fristproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    private String title; // 제목을 받을 필드
    private String content; // 내용을 받을 필드



    // DTO인 form 객체를 엔티티 객체로 변환해주는 역할이다.
    public Article toEntity() {
        return new Article(id, title, content);
    }
}
