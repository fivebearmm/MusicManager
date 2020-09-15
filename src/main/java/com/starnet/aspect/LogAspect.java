package com.starnet.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect //切面操作
@Component //开启组件扫描
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());//输出日志记录

    @Pointcut("execution(* com.starnet.web.*.*(..))")//让log（）成为切面，规定拦截的类
    public void log() {}


    @Before("log()") //Controller类请求之前拦截。执行下面的方法
    public void doBefore(JoinPoint joinPoint) { //切面对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();//获取Controller中方法请求httpRequest
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();//获取
        Object[] args = joinPoint.getArgs();//获取请求参数
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request : {}", requestLog);
    }

    @After("log()")  //Controller类的方法请求之后拦截。执行下面的方法
    public void doAfter() {
        logger.info("--------doAfter--------");
    }

    @AfterReturning(returning = "result",pointcut = "log()") //Controller类的方法请求拦截，执行下面的方法，返回Controller类的方法的返回内容
    public void doAfterRuturn(Object result) {
        logger.info("Result : {}", result);
    }

    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}
