package com.example.fristproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        // model 객체가 "미래" 값을 "userName"에 연결해 웹 브라우저로 보냄
        model.addAttribute("userName", "미래"); // 변수값을 "userName" 이라는 이름으로 추가
        return "greetings"; // greetings.mustache 파일 변환
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("userName", "김미래");
        return "Bye";
    }

}
