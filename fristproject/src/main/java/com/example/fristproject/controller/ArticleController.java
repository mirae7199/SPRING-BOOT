package com.example.fristproject.controller;

import com.example.fristproject.dto.ArticleForm;
import com.example.fristproject.entity.Article;
import com.example.fristproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {

        return "/articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        //System.out.println(form.toString());
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        //System.out.println(article.toString());
        log.info(article.toString());
        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId(); // 리다이렉트를 작성할 위치
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) { // 매개변수로 id 받아 오기
        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null); // 해당 id값이 없으면 null을 반환하라는 뜻
        // 2. 모델에 데이터 등록하기
        // model.addAttribute(String name, Object value) (형식)
        model.addAttribute("article", articleEntity);
        // 3. 뷰 페이지 반환하기
        return "articles/show";

    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 1단계 데이터 모두 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        // 2단계 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);

        model.addAttribute("article", articleEntity);
        // 뷰 페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){ // 매개변수로 DTO 받아 오기
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        // 2. 엔티티를 DB에 저장하기
        // 2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-2. 기존 데이터 값을 갱신하기
        if(target != null){
            articleRepository.save(articleEntity); // 엔티티를 DB에 저장(갱신)
        }else
            articleRepository.save(articleEntity);

        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delect")
    public String delect(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다!");

        // 1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 2. 대상 엔티티 삭제하기
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다!");
        }
        // 3. 결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }




}
