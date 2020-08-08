package com.configService.modules.registered.web.baseController;


import com.daxu.common.Bus.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 //
 //                       .::::.
 //                     .::::::::.
 //                    :::::::::::
 //                 ..:::::::::::'
 //              '::::::::::::'
 //                .::::::::::
 //           '::::::::::::::..
 //                ..::::::::::::.
 //              ``::::::::::::::::
 //               ::::``:::::::::'        .:::.
 //              ::::'   ':::::'       .::::::::.
 //            .::::'      ::::     .:::::::'::::.
 //           .:::'       :::::  .:::::::::' ':::::.
 //          .::'        :::::.:::::::::'      ':::::.
 //         .::'         ::::::::::::::'         ``::::.
 //     ...:::           ::::::::::::'              ``::.
 //    ```` ':.          ':::::::::'                  ::::..
 //                       '.:::::'                    ':'````..
 * @program: jhome-root
 * @description: GlobalDataPreprocessingController
 * @author: Daxv
 * @create: 2020-08-08 12:21
 **/

/**
 * 全局处理
 */
@ControllerAdvice
@ResponseBody
public class GlobalDataPreprocessingController {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalDataPreprocessingController.class);
    private static final ResponseJson ERROR;
    static {
        ERROR = new ResponseJson(HttpStatus.INTERNAL_SERVER_ERROR.hashCode()).setMsg("系统出错,请稍候再试");
    }
    /**
     * 描述：默认异常提示
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseJson defaultErrorHandler(Exception exception) {
        LOG.error(exception.getMessage(), exception);
        return ERROR;
    }

    /**
     * 描述：参数不合法默认异常提示
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseJson securityExceptionHandler(Exception exception) {
        return new ResponseJson(HttpStatus.INTERNAL_SERVER_ERROR.hashCode()).setMsg(exception.getMessage());
    }

    /**
     * 描述：表单数据格式不正确异常提示
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseJson illegalParamExceptionHandler(MethodArgumentNotValidException exception) {
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();
        String tips = "参数不合法";
        ResponseJson result = new ResponseJson(HttpStatus.BAD_REQUEST.hashCode());
        if (!errors.isEmpty()) {
            List<String> list = errors.stream()
                    .map(error -> error.getField() + error.getDefaultMessage())
                    .collect(Collectors.toList());
            result.put("details", list);
        }
        result.setMsg(tips);
        return result;
    }

    /**
     * 描述：表单数据缺失异常提示
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseJson servletRequestParameterExceptionHandler(MissingServletRequestParameterException exception) {
        return new ResponseJson(HttpStatus.BAD_REQUEST.hashCode()).setMsg(exception.getMessage());
    }

    /**
     * 描述：请求方法不支持异常提示
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseJson methodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException exception) {
        String supportedMethods = exception.getSupportedHttpMethods().stream()
                .map(method -> method.toString())
                .collect(Collectors.joining("/"));

        String msg = "请求方法不合法,请使用方法" + supportedMethods;
        return new ResponseJson(HttpStatus.METHOD_NOT_ALLOWED.hashCode()).setMsg(msg);
    }

    /**
     * 描述：数据绑定失败异常提示
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseJson validationBindException(BindException exception) {
        String errors = exception.getFieldErrors().stream()
                .map(error -> error.getField() + error.getDefaultMessage())
                .collect(Collectors.joining(","));
        return new ResponseJson(HttpStatus.BAD_REQUEST.hashCode()).setMsg(errors);
    }
}
