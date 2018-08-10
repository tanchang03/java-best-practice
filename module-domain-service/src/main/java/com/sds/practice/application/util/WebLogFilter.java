package com.sds.practice.application.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.practice.application.util
 * @Description : 统一controller日志输出
 * @Author : tanchang
 * @Create Date : 2018年08月10日 下午2:36
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@WebFilter(filterName = "weblog",urlPatterns = {"/api/*","/open/*"})
public class WebLogFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(WebLogFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        ResponseWrapper wrapper = new ResponseWrapper((HttpServletResponse) response);
        HttpServletRequest req = (HttpServletRequest) request;
        WebUtils.logRequestParams(req,logger);

        chain.doFilter(request, wrapper);
        long endTime = System.currentTimeMillis();
//
        String result = wrapper.getResponseData(response.getCharacterEncoding());
        response.getOutputStream().write(result.getBytes());
        if(response.getContentType().matches("^[application/json|text].*")){
            logger.debug("响应类型："+response.getContentType());
            logger.debug("请求返回："+result);
        }
        logger.debug("请求耗时[{}ms]=>{}",endTime - startTime,((HttpServletRequest) request).getRequestURI());
    }

    @Override
    public void destroy() {

    }
}
