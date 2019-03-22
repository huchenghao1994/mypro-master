package com.yaspeed.core.exception;

import com.yaspeed.common.ReturnResult;
import com.yaspeed.core.util.RequestUtil;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * 
* @ClassName: ExceptionAdvice
* @Description:统一异常拦截
* @author hch   
* @date Jun 25, 2018 3:35:21 PM   
*
 */
@ControllerAdvice
public class ExceptionAdvice {
    private static Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
    /**
     * 参数验证异常拦截 - Bad Request
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ReturnResult bindException(HttpServletResponse response,BindException  e) {
    	response.setStatus(ReturnResult.PARA_VALID_CODE);
        BindingResult result = e.getBindingResult();
        StringBuffer sb = new StringBuffer();
        for (ObjectError error : result.getAllErrors()) {
            String message = error.getDefaultMessage();
            sb.append(message);
            break;
        }
    	logger.error("参数验证失败>>>>>>:"+sb);
        //2:参数验证失败
    	return ReturnResult.byCodeMsg(ReturnResult.PARA_VALID_CODE, sb.toString());
    }

    /**
     * shiro授权认证 403 Bad Request
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public ReturnResult unauthorizedException(HttpServletRequest request, HttpServletResponse response,UnauthorizedException e) {
        response.setStatus(ReturnResult.FORBIDDEN);
        if (!RequestUtil.isAjaxRequest(request)) {
            try {
                response.sendRedirect("/error");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return ReturnResult.byCodeMsg(ReturnResult.FORBIDDEN,e.getMessage());

    }


    /**
     * 自定义异常 Bad Request
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ReturnResult customException(HttpServletResponse response,CustomException e) {
        response.setStatus(ReturnResult.CUS_EX_CODE);
        return ReturnResult.byCodeMsg(ReturnResult.CUS_EX_CODE,e.getCause().getMessage());

    }

    /**
     *  Bad Request
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ReturnResult exception(HttpServletResponse response,Exception e) {
        response.setStatus(ReturnResult.INTERNAL_SERVER_ERROR);
        e.printStackTrace();
        return ReturnResult.byCodeMsg(ReturnResult.INTERNAL_SERVER_ERROR,e.getMessage());
    }
}