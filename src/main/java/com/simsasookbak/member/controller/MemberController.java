package com.simsasookbak.member.controller;


import com.simsasookbak.member.domain.RegisterDto;
import com.simsasookbak.member.service.MemberService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final JavaMailSender mailSender;

    @ResponseBody
    @PostMapping(value = "/member/register")
    public ResponseEntity<String> regist(@RequestBody RegisterDto member){
        try{
            memberService.register(member.toEntity());
            return ResponseEntity.ok().body(member.toString());
        }catch (IllegalArgumentException | DataIntegrityViolationException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler()
                .logout(request, response, SecurityContextHolder.getContext()
                .getAuthentication());
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/email/check/message")
    public ResponseEntity<String> ckeckEmailAddress(@RequestBody Map<String,String> email) throws MessagingException {

        if(!memberService.isInDb(email.get("email"))) {
            String randomInt = memberService.makeRandomInt();
            MimeMessage message = mailSender.createMimeMessage();
            message.setSubject("[simsasookbak] 본인 이메일을 인증해 주세요"); // 메일 제목
            message.setRecipients(MimeMessage.RecipientType.TO, email.get("email"));
            String body = "";
            body += "<h3>" + "회원가입을 위해 다음 인증 번호를 입력해주세요" + "</h3>";
            body += "<h1>" + randomInt + "</h1>";

            message.setText(body, "UTF-8", "html");
            mailSender.send(message);
            return ResponseEntity.ok(randomInt);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일이 이미 가입되어있습니다.");
        }
    }

    @ResponseBody
    @PostMapping("/member/check/{email}/{password}")
    public ResponseEntity<Boolean> checkIdAndPasswordValidate(@PathVariable String email, @PathVariable String password){
        if(!memberService.isInDb(email) || !memberService.checkLogin(password,email)){
            return ResponseEntity.ok(false);
        }else{
            return ResponseEntity.ok(true);
        }
    }

    /* *
    * 페이지
    *  */
    @GetMapping("/login")
    public String registerPage(){
        return "login";
    }

}
