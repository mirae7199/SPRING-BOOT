package com.example.fristproject.controller;

import com.example.fristproject.dto.MemberForm;
import com.example.fristproject.entity.Member;
import com.example.fristproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Optional;


@Controller
@Slf4j
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/members/search")
    public String search(MemberForm form, Model model){
        log.info(form.toString());

        model.addAttribute("data", form);

        return "members/search/test";
    }

    @GetMapping("/members/new")
    public String NewMemberForm(){

        return "members/member";
    }

    @PostMapping("/members/create")
    public String createMember(MemberForm form) {
        log.info(form.toString());

        Member member = form.toEntity();
        log.info(member.toString());

        Member saved = memberRepository.save(member);
        log.info(saved.toString());

        return "redirect:/members/" + saved.getId();  // 리다이렉트를 작성할 위치
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);

        Member MemberEntity = memberRepository.findById(id).orElse(null);

        model.addAttribute("member", MemberEntity);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model) {
        ArrayList<Member> memberEntityList = memberRepository.findAll();

        model.addAttribute("memberList", memberEntityList);

        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
        public String edit(@PathVariable Long id, Model model) {
        Member memberEntity = memberRepository.findById(id).orElse(null);

        model.addAttribute("member", memberEntity);
        return "members/edit";
        }

    @PostMapping("members/update")
    public String update(MemberForm form, Model modeㅣ) {
        log.info(form.toString());
        // 1.DTO를 엔티티로 변환하기
        Member memberEntity = form.toEntity();
        log.info(memberEntity.toString());
        // 2.엔티티를 DB에 저장하기
        // 2-1. DB에서 기존 데이터 가져오기
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        log.info(target.toString());
        // 2-2. 기존 데이터 값을 갱신하기
        if (target != null){
            memberRepository.save(memberEntity);

        }

        // 3. 수정 결과 페이지로 리다이렉트하기

        return "redirect:/members/" + memberEntity.getId() ;
    }

    @GetMapping("members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        Member target = memberRepository.findById(id).orElse(null);

        if(target != null) {
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다!!!");
        }

        return "redirect:/members";
    }
}
