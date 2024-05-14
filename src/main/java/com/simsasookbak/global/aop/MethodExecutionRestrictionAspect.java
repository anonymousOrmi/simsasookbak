package com.simsasookbak.global.aop;

import com.simsasookbak.global.exception.MethodInvocationLimitException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class MethodExecutionRestrictionAspect {

    private final Map<String, Long> lastInvocationTimes = new HashMap<>();

    @Pointcut("@annotation(com.simsasookbak.global.aop.MethodInvocationLimit)")
    private void methodInvocationLimit() {}

//    @Before("methodInvocationLimit()")
//    public void beforeMethodInvocationLimit() {
//        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(
//                RequestContextHolder.getRequestAttributes())).getRequest();
//        String username = request.getUserPrincipal().getName();
//
//        Long lastInvocationTime = lastInvocationTimes.get(username);
//        long currentTime = System.currentTimeMillis();
//
//        if (lastInvocationTime != null && currentTime - lastInvocationTime < 60 * 1000) {
//            throw new MethodInvocationLimitException("같은 사용자가 반복적인 호출을 시도하였습니다");
//        }
//
//        // 메서드를 호출한 시간을 기록
//        lastInvocationTimes.put(username, currentTime);
//    }
}
