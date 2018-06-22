package bank.backend;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Aspect
public class LoggerAspect {

    private List<String> methods = new ArrayList<>();

    public void clear() {
        methods.clear();
    }
//minden advice
    @Around("execution(* bank.backend.BankService.*(..))")//ez a pointcut
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("log: " + joinPoint.getSignature().getName());
        methods.add(joinPoint.getSignature().getName());

        return joinPoint.proceed();
    }
    //advice és pointcut egyben az aspect
}
