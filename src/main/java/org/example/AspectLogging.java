package org.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/*
    Класс логирования с помощью аспекта
    * Этот класс логирует метод GetGoods класса ProgramManager
    * Содержит методы логирования logAroundGetGoods и logExceptionInGetGoods
    * для логирования исключений
 */
@Aspect
@Component
public class AspectLogging {

    private static final Logger logger = LoggerFactory.getLogger(AspectLogging.class);

    @Around("execution(* org.example.ProgramManager.getGoods(..))")
    public Object logAroundGetGoods(ProceedingJoinPoint joinPoint) throws Throwable {

        /*
         * Логгер, который использует аспект @Around для мониторинга метода getGoods.
         * Логирует имя метода, параметры, возвращаемое значение и необработанные исключения.
         */

        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        // Логгируем информацию перед выполнением метода
        System.out.println("Method started: " + methodName);
        System.out.println("Arguments: " + Arrays.toString(methodArgs));

        logger.info("Method started: {}", methodName);
        logger.info("Arguments: {}", Arrays.toString(methodArgs));

        Object result = null;
        try {

            result = joinPoint.proceed();
            // Логируем возвращаемое значение после успешного выполнения метода
            System.out.println("Method successfully completed: " + methodName);
            System.out.println("Returned: " + result);

            logger.info("Method successfully completed: {}", methodName);
            logger.info("Returned: {}", result);

        } catch (Throwable throwable) {
            // Логируем исключение
            System.out.println("Exception in method: " + methodName);
            logger.error("Exception in method: {}", methodName, throwable);
            throw throwable;
        }
        return result;
    }

    /*
     * Логирование необработанных исключений, которые возникают в методе getGoods.
     * Метод использует аннотацию @AfterThrowing для перехвата исключений и их логирования.
     */

    @AfterThrowing(pointcut = "execution(* org.example.ProgramManager.getGoods(..))", throwing = "ex")
    public void logExceptionInGetGoods(JoinPoint joinPoint, Throwable ex) {
        System.out.println("Exception thrown in method: " + joinPoint.getSignature().getName());
        System.out.println("Exception: " + ex.getMessage());

        logger.error("Exception thrown in method: {}", joinPoint.getSignature().getName());
        logger.error("Exception: {}", ex.getMessage(), ex);
    }
}
