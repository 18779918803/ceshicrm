package com.mage.crm.base.exceptions;

import com.alibaba.fastjson.JSON;
import com.mage.crm.base.CrmConstant;
import com.mage.crm.model.MessageModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;


@Component
public class GlobalException  implements HandlerExceptionResolver{
    /**
     *视图异常，return ModelAndView
     * json异常有ResponseBody return null; MessageModel httpServletResponse写出去
     *
     * 1 是否是未登录异常
     * 2 json异常
     * 3 视图异常
     * @param httpServletRequest
     * @param httpServletResponse
     * @param handler
     * @param e
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) {
        ModelAndView modelAndView = createDefaultModelAndView(httpServletRequest);
        ParamsException pe;
        if(handler instanceof HandlerMethod){
            //1，未登录异常
            if(e instanceof ParamsException){
                pe = (ParamsException) e;
                if(pe.getCode()==CrmConstant.NO_LOGIN_CODE){
                    modelAndView.addObject("code",pe.getCode());
                    modelAndView.addObject("msg",pe.getMsg());
                    return modelAndView;
                }
            }
            //2，json异常
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
            if(null!=responseBody){
                MessageModel messageModel = new MessageModel();
                messageModel.setCode(CrmConstant.OPS_FAILED_CODE);
                messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
                if(e instanceof ParamsException){
                    pe=(ParamsException)e;
                    messageModel.setCode(pe.getCode());
                    messageModel.setMsg(pe.getMsg());
                }
                httpServletResponse.setContentType("application/json;charset=utf-8");
                httpServletResponse.setCharacterEncoding("utf-8");
                PrintWriter writer = null;
                try {
                    writer = httpServletResponse.getWriter();
                }catch (IOException ioe){
                    ioe.printStackTrace();
                }finally {
                    if(writer!= null){
                        writer.write(JSON.toJSONString(messageModel));
                        writer.flush();
                        writer.close();
                    }
                }
                return null;
            }else{
                //3，视图异常
                if(e instanceof ParamsException){
                    pe = (ParamsException)e;
                    modelAndView.addObject("code",pe.getCode());
                    modelAndView.addObject("msg",pe.getMsg());
                    return modelAndView;
                }else {
                    return modelAndView;
                    }
                }
             }
            return null;
        }

    private static ModelAndView createDefaultModelAndView(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("code", CrmConstant.OPS_FAILED_CODE);
        modelAndView.addObject("msg",CrmConstant.OPS_FAILED_MSG);
        modelAndView.addObject("ctx",request.getContextPath());
        modelAndView.addObject("uri",request.getRequestURI());
        return modelAndView;
    }
}
