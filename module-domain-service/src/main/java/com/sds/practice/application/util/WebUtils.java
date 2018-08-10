package com.sds.practice.application.util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * @author tantyou
 */
@SuppressWarnings("unchecked")
public class WebUtils {
	public static final Logger logger = LoggerFactory.getLogger(WebUtils.class);
	private WebUtils() {
	}
	
	/**
	 *  向客户端输出json字符串
	 * @param response
	 * @param json
	 */
	public static void outputJson(HttpServletRequest request, HttpServletResponse response, String json){
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		try {
			logger.debug("请求响应 ["+getRemoteAddress(request)+"]["+request.getSession().getId()+"]:"+json);
			response.getWriter().print(json);
		} catch (IOException e) {
			logger.error("IO异常",e);
		}
	}
	/**
	 *  向客户端输出json字符串
	 * @param response
	 * @param json
	 */
	@Deprecated
	public static void outputJson(HttpServletResponse response, String json){
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		try {
			logger.debug("response output :"+json);
			response.getWriter().print(json);
		} catch (IOException e) {
			logger.error("IO异常",e);
		}
	}

	/**
	 * 取得request参数值,根据是否需要转码配置进行相应的转码工作
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getRequestParam(HttpServletRequest request, String name){
		return getRequestParam(request, name, null);
	}

	/**
	 * 取得request参数值,根据是否需要转码配置进行相应的转码工作
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getRequestParam(HttpServletRequest request, String name, String defaultValue){
		String method = request.getMethod().trim().toLowerCase();
		String ret = request.getParameter(name);
		if(StringUtils.isNotEmpty(ret)){
			if(method.equals("get")){
				try {
					if (ret != null && !ret.equals("")) {
						byte[] byteStr = ret.getBytes("ISO-8859-1");
						ret = new String(byteStr,"utf-8");
					}
				} catch (Exception e) {
				}
			}
		}else{
			ret = defaultValue;
		}
		return ret;
	}

	/**
	 * 得到request的queryString
	 * @param request
	 * @return
	 */
	public static String getQueryString(HttpServletRequest request){
		String ret = request.getQueryString();
		if(StringUtils.isNotEmpty(ret)){
			String encode = "utf-8";

				String charset = "utf-8";
				try {
					if (ret != null && !ret.equals("")) {
						byte[] byteStr = ret.getBytes("ISO-8859-1");
						ret = new String(byteStr,charset);
					}
				} catch (Exception e) {
				}
				


		}
		return ret;
	}

	/**
	 * 输出请求参数方法
	 * @param request
	 */
	public static void logRequestParams(HttpServletRequest request) throws IOException {
		logRequestParams(request,logger);
	}
	/**
	 * 输出请求参数方法
	 * @param request
	 */
	public static void logRequestParams(HttpServletRequest request,Logger logger) throws IOException {
		//过滤不想看到的日志
		String url = request.getRequestURI();
		String excludes[] = "".split(",");
		for (int i = 0; i < excludes.length; i++) {
			if(StringUtils.isNotEmpty(excludes[i]) && url.indexOf(excludes[i])>=0){
				return;
			}
		}
		
		logger.info("Http请求 ["+getRemoteAddress(request)+"]["+request.getSession().getId()+"]"+request.getRequestURL()+" ");
		if(logger.isDebugEnabled()){
			logger.debug("ContentType：{}",request.getContentType());
			logger.debug("Method:{}",request.getMethod());
			logger.debug("Encoding:{}",request.getCharacterEncoding());
			logger.debug("Headers:");
			Enumeration headerNames = request.getHeaderNames();
			while(headerNames.hasMoreElements()){
				String name = (String) headerNames.nextElement();
				String value = request.getHeader(name);
				logger.debug("   {}:{}",name,value);
			}
		}
		logger.info("Params：");
		Enumeration<String> params = request.getParameterNames();
		while(params.hasMoreElements()){
			String paramName = params.nextElement();
			String value = request.getParameter(paramName);
			logger.info(paramName+":"+value);
		}
	}

	public static String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

	/**
	 * 获取调用者ip地址
	 * @param request
	 * @return
	 */
	public static String getRemoteAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-real-ip");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
				{
					ip = request.getRemoteAddr();
				}
			}
		}
		return ip;
	}
	
	/**
	 * 获取mac地址
	 * @param ip
	 * @return
	 */
	 public String getMACAddress(String ip) {
	        String str = "";
	        String macAddress = "";
	        try {
	            Process p = Runtime.getRuntime().exec("nbtstat -a " + ip);
	            InputStreamReader ir = new InputStreamReader(p.getInputStream());
	            LineNumberReader input = new LineNumberReader(ir);
	            for (int i = 1; i < 100; i++) {
	                str = input.readLine();
	                if (str != null) {
	                    //if (str.indexOf("MAC Address") > 1) {
	                    if (str.indexOf("MAC") > 1) {
	                        macAddress = str.substring(
	                                str.indexOf("=") + 2, str.length());
	                        break;
	                    }
	                }
	            }
	        } catch (IOException e) {
				logger.error("IO异常",e);
	        }
	        return macAddress;
	    }

	/**
	 * 根据cookie名字获取cookie value
	 * @param name
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String name){
		Cookie[] cookies = request.getCookies();
		String value = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(name)){
					value = cookie.getValue();
					break;
				}
			}
		}
		return value;
	}
	
	


	/**
	 * 得到cookie对象
	 * 主要用于删除cookie使用
	 * 需要重新设置对应的 path和 domain才能正确删除对应cookie
	 * @param string
	 */
	public static Cookie getCookieObj(HttpServletRequest request, String string) {
        Cookie[] allCookies = request.getCookies();
        int i = 0;
        if(allCookies != null){
            for(i = 0;i < allCookies.length;i++){
                Cookie temp = allCookies[i];
                if(temp.getName().equals(string)){
                	return temp;
                }
            }
        }
        return null;
	}

	/**
	 * 获取请求参数作为map返回
	 * @param request
	 * @return
     */
	public static Map<String,Object> getRequestParams(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		Enumeration<String> params = request.getParameterNames();
		while(params.hasMoreElements()){
			String paramName = params.nextElement();
			String value = request.getParameter(paramName);
			result.put(paramName,value);
		}

		return result;
	}

	/**
	 * 将map参数转换为queryString
	 * @param params
	 * @return
     */
	public static String paramsMapToQueryString(Map<String, Object> params) {
		StringBuffer sb = new StringBuffer();
		for (String key:params.keySet() ) {
			sb.append(key + "=" + params.get(key) + "&");
		}
		if(sb.length() > 0){
			sb.subSequence(0,sb.length()-1);
		}
		return sb.toString();
	}
}
