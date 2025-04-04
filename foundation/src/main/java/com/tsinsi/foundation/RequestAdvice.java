package com.tsinsi.foundation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Component
@Aspect
@Order(1)
@Slf4j
public class RequestAdvice {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restController() {
    }

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
    }

    @Pointcut("publicMethod() && restController()")
    public void publicMethodInsideController() {
    }

    @Around("publicMethodInsideController()")
    public Object aroundMethodAdvice(final ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        try {
            log.info("Executing use case: {}#{} with parameters: {}", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
            stopWatch.start();
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            log.info("Finished executing use case {}#{} in {}ms", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(), stopWatch.getTotalTimeMillis());
        }
    }
}
