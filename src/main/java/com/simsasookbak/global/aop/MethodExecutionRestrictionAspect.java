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

    @Pointcut("execution(* com.simsasookbak.reservation.controller.ReservationController.saveReservation(..))")
    private void saveReservation() {}

    @Before("saveReservation()")
    public void beforeSaveReservation() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes())).getRequest();
        String username = request.getUserPrincipal().getName();
        System.out.println(username);

        Long lastInvocationTime = lastInvocationTimes.get(username);
        long currentTime = System.currentTimeMillis();

        if (lastInvocationTime != null && currentTime - lastInvocationTime < 3 * 60 * 1000) {
            throw new MethodInvocationLimitException("같은 사용자가 반복적인 호출을 시도하였습니다");
        }

        lastInvocationTimes.put(username, currentTime);
    }

}
