package aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

    //setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    //setup pointcut declarations for dao
    @Pointcut("execution(* dao.*.*(..))")
    private void forDaoPackage() {
    }

    ;

    //setup pointcut declarations for entity
    @Pointcut("execution(* entity.*.*(..))")
    private void forEntityPackage() {
    }

    ;

    //setup pointcut declarations for service
    @Pointcut("execution(* service.*.*(..))")
    private void forServicePackage() {
    }

    ;

    //setup pointcut declarations for view
    @Pointcut("execution(* view.*.*(..))")
    private void forViewPackage() {
    }

    ;

    //setup pointcut declarations for view
    @Pointcut("forDaoPackage() || forEntityPackage() || forServicePackage() || forViewPackage()")
    private void forAppFlow() {
    }

    ;

    //add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {

        //display method name
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("===>> in @Before: calling method: " + theMethod);

        //display method arguments
        Object[] args = theJoinPoint.getArgs();

        for (Object argument : args) {
            myLogger.info("===>> argument: " + argument);
        }
    }

    //add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult"
    )
    public void afterReturning(JoinPoint theJoinPoint, Object theResult){
        //display method name
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("===>> in @AfterReturning: from method: " + theMethod);

        //display data returned
        myLogger.info("===>> result: " + theResult);
    }
}
