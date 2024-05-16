package com.simsasookbak.member.controller;


import com.simsasookbak.external.ai.alan.dto.AlanResponseDto;
import com.simsasookbak.member.domain.RegisterDto;
import com.simsasookbak.member.dto.BusinessDTO;
import com.simsasookbak.member.service.MemberService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final JavaMailSender mailSender;
    private final RestTemplate restTemplate;

    @ResponseBody
    @PostMapping(value = "/member/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto member) {
        try {
            memberService.register(member.toEntity());
            return ResponseEntity.ok().body(member.toString());
        } catch (IllegalArgumentException | DataIntegrityViolationException ex) {
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
    public ResponseEntity<String> checkEmailAddress(@RequestBody Map<String, String> email) throws MessagingException {

        if (!memberService.isInDb(email.get("email"))) {
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
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일이 이미 가입되어있습니다.");
        }
    }

    @ResponseBody
    @PostMapping("/member/check/{email}/{password}")
    public ResponseEntity<Boolean> checkIdAndPasswordValidate(@PathVariable String email,
                                                              @PathVariable String password) {
        if (!memberService.isInDb(email) || !memberService.checkLogin(password, email)) {
            return ResponseEntity.ok(false);
        } else {
            return ResponseEntity.ok(true);
        }
    }

    @ResponseBody
    @PostMapping("/member/check/{business_no}")
    public boolean isValidateBusiness(@PathVariable String business_no) throws ParseException {
        String uri = "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=hDkNGi5NaWhGzert8eJttWXbK8yX0ifM6yMdqv0LVnzFQ/bYgMI/fl1q/7s0as5LkLCQPfkrlNQ/MGA3qIHqvA==";
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

//        String strJson = "{\"b_no\": ["
//                +"\""+business_no+"\"]}";
//        JSONParser jsonParser = new JSONParser();
//        Object obj = jsonParser.parse(strJson);
//        JSONObject parameter = (JSONObject) obj;
////        parameter.put("",);
        JSONObject parameter = new JSONObject();
        List<String> stringList = new ArrayList<>();
        stringList.add(business_no);
        parameter.put("b_no", stringList);

        HttpEntity<String> req = new HttpEntity<>(parameter.toJSONString(), headers);


        ResponseEntity<BusinessDTO> response = restTemplate.postForEntity(uri, req, BusinessDTO.class);
        if (response.getStatusCode().is2xxSuccessful()){
            String responseValue = response.getBody().getData().getFirst().getB_stt_cd();
            log.warn("외부 url요청 후 반환 값 = {}",responseValue);
            log.warn("외부 url요청 후 반환 값 = {}",response.getBody().getData().getFirst());
            log.warn("외부 url요청 후 반환 값 = {}",response.getBody().getData().getFirst().b_no);
            log.warn("외부 url요청 후 반환 값 = {}",response.getBody().getData().getFirst().getB_no());
            log.warn("외부 url요청 후 반환 값 = {}",response.getBody().getData().getFirst().getTax_type());
            return responseValue != null && responseValue.equals("01");
        }
        return false;

    }

    /* *
     * 페이지
     *  */
    @GetMapping("/login")
    public String registerPage() {
        return "login";
    }
}
