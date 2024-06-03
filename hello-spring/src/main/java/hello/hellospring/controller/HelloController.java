package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello") // http://localhost:8080/hello 여기서 /hello 이걸로 메서드가 실행 됨
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // hello.html을 찾아서 실행시켜라,,
        // resources:templates/ +{hello} + .html

        /*

         * hello 라는 url을 호출하면 model에다가 "data"라는 이름으로, "spring"값을 넣는다.

         * -- Model은 spring framework ui를 위한 interface다--> 구현제 ModelMap(data만 저장)

         *            , ModelAndView(viewPage까지 저장) 가 있다.

         * -- 잘보면 return 이 string이다.

         *    이말은 viewResolver가 return string값의 view페이지를  로딩한다. (hello.html)

         */


    }

    @GetMapping("hello-mvc") // http://localhost:8080/hello-mvc?name=spring!
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name); // kye : value
        return "hello-template";
        // GetMapping은 @ReQuestParam을 받아 처리한다. url 다음에 ?이름=값 /hello-mvc?name=spring!




    }

    // @ResponseBody 는 body에 내가 넘기는 값을 바로 출력해주는 것이다.

    // viewResolver가 하지 않고 mesageConverter가 작업을 해주게 된다.


    @GetMapping("hello-string")
    @ResponseBody // http 바디부에 직접 데이터를 넣어주겠다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello spring" 데이터 값 고대로 보내줌 @ResponseBody
    }

    @GetMapping("hello-api")
    @ResponseBody // 리턴 값이 객체일때 json 방식으로 http 응답에 반환함,,,
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);

        return hello; // 객체가 옴,,,

    }
    static class Hello {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
