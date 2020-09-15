package com.starnet.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//拦截Controller类
@ControllerAdvice
public class ControllerExceptionHandler {

    //日志记录异常
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @ExceptionHandler(Exception.class)//标识该方法可以做异常处理
    public ModelAndView exceptionHander(HttpServletRequest request, Exception e) throws Exception {

        //日志里的信息
        logger.error("Requst URL : {}，Exception : {}", request.getRequestURL(),e);

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception", e);
        mv.setViewName("error/error");//errror目录下的error页面
        return mv;
    }
}
