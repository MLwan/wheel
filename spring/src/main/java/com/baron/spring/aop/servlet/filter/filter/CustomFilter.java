package com.baron.spring.aop.servlet.filter.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.BufferedReader;
import java.io.IOException;


//注册器名称为customFilter,拦截的url为所有
//@WebFilter(filterName="customFilter",urlPatterns={"/*"})  //使用注册方式二
@Slf4j
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter 初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("customFilter 请求处理之前----doFilter方法之前过滤请求");
        //对request、response进行一些预处理
        // 比如设置请求编码
        // request.setCharacterEncoding("UTF-8");
        // response.setCharacterEncoding("UTF-8");

        /*
            获取 HttpServletRequest json形式的全部参数
         */
        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                sb.append(line);
        } catch (Exception e) { /*report an error*/ }
        //将空格和换行符替换掉避免使用反序列化工具解析对象时失败
        String jsonString = sb.toString().replaceAll("\\s","").replaceAll("\n","");
        //下面就可以使用如GSON或FastJson之类的工具解析成自己的对象数据并做后续的业务逻辑处理了

        System.out.println("ServletRequest转json输出"+jsonString);
//        PrintWriter out = response.getWriter();
//        out.write("success");
//        out.close();

        //链路 直接传给下一个过滤器
        chain.doFilter(request, response);

        log.info("customFilter 请求处理之后----doFilter方法之后处理响应");
    }

    @Override
    public void destroy() {
        log.info("filter 销毁");
    }
}

