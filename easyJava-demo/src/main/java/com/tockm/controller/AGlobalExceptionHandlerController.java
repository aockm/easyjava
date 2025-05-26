package com.tockm.controller;

import com.tockm.entity.vo.ResponseVo;
import com.tockm.enums.ResponseCodeEnum;
import com.tockm.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.net.BindException;
import org.springframework.dao.DuplicateKeyException;

@RestControllerAdvice
public class AGlobalExceptionHandlerController extends ABaseController {
    private static final Logger logger = LoggerFactory.getLogger(AGlobalExceptionHandlerController.class);

    @ExceptionHandler(value = Exception.class)
    Object handleException(Exception e, HttpServletRequest request) {
        logger.error("请求错误，请求地址{}，错误信息:",request.getRequestURL(),e);
        ResponseVo ajaxResponseVo = new ResponseVo();

        if (e instanceof NoHandlerFoundException) {
            ajaxResponseVo.setCode(ResponseCodeEnum.CODE_404.getCode());
            ajaxResponseVo.setInfo(ResponseCodeEnum.CODE_404.getMsg());
            ajaxResponseVo.setStatus(STATUS_ERROR);
        }else if (e instanceof BusinessException) {
            BusinessException biz = (BusinessException) e;
            ajaxResponseVo.setCode(biz.getCode());
            ajaxResponseVo.setInfo(biz.getMessage());
            ajaxResponseVo.setStatus(STATUS_ERROR);
        }else if (e instanceof BindException) {
            ajaxResponseVo.setCode(ResponseCodeEnum.CODE_600.getCode());
            ajaxResponseVo.setInfo(ResponseCodeEnum.CODE_600.getMsg());
            ajaxResponseVo.setStatus(STATUS_ERROR);
        }else if (e instanceof DuplicateKeyException) {
            // 主键冲突
            ajaxResponseVo.setCode(ResponseCodeEnum.CODE_601.getCode());
            ajaxResponseVo.setInfo(ResponseCodeEnum.CODE_601.getMsg());
            ajaxResponseVo.setStatus(STATUS_ERROR);
        }else {
            ajaxResponseVo.setCode(ResponseCodeEnum.CODE_500.getCode());
            ajaxResponseVo.setInfo(ResponseCodeEnum.CODE_500.getMsg());
            ajaxResponseVo.setStatus(STATUS_ERROR);
        }
        return ajaxResponseVo;
    }
}
