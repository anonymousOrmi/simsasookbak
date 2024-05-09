package com.simsasookbak.member.controller;

import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.domain.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;


@Component
@Aspect
@Slf4j
public class TopbarAOP {
    @Around("execution(* com.simsasookbak.*.*Controller.*(..))")
    public Object TopbarInfo(ProceedingJoinPoint pjp) throws Throwable{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberDto memberDto;
        memberDto = ((Member)authentication.getPrincipal()).toDto();
//        if (authentication != null){
//        }
//        else{
//            topbarDto = new TopbarDto(null,null);
//        }
//
//        Object[] args = pjp.getArgs();
//        for(Object arg: args){
//            if(arg instanceof Model){
//                Model model = (Model) arg;
//                log.error("{}",model);
//                model.addAttribute("TopbarInfo", memberDto);
//            }
//        }




//        return pjp.proceed(new Object[]{topbarDto});
        return memberDto;
    }
}
