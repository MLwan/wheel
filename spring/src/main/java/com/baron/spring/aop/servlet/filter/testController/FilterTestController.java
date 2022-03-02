package com.baron.spring.aop.servlet.filter.testController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: listener\filter测试入口
 * @Author: zhanwang
 * @Date: 2021/1/18
*/

@Slf4j
@RequestMapping("/servlet")
@RestController
public class FilterTestController {

    @RequestMapping("/test")
    public String test(HttpServletRequest req, HttpServletRequest resp, HttpSession ses){

        System.out.println("执行test方法");
        /*
            获取 HttpServletRequest form-data形式的全部参数
         */
        Map res = new HashMap();
        Enumeration temp = req.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = req.getParameter(en);
                System.out.println("key的值："+ en + "value的值：" + value);
                res.put(en, value);
                //如果字段的值为空，判断若值为空，则删除这个字段>
                if (null == res.get(en) || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        /*
            获取 HttpServletRequest json形式的全部参数
         */
        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                sb.append(line);
        } catch (Exception e) { /*report an error*/ }
        //将空格和换行符替换掉避免使用反序列化工具解析对象时失败
        String jsonString = sb.toString().replaceAll("\\s","").replaceAll("\n","");
        //下面就可以使用如GSON或FastJson之类的工具解析成自己的对象数据并做后续的业务逻辑处理了

        System.out.println(jsonString);
//        PrintWriter out = resp.getWriter();
//        out.write("success");
//        out.close();




        //操作Attribute
        req.setAttribute("reqAttribute","one");
        req.setAttribute("reqAttribute","two");
        req.removeAttribute("reqAttribute");

        //操作session
        ses.setAttribute("seqAttribute","three");
        ses.getAttribute("seqAttribute");
        ses.invalidate();

        //
        return "ok";
    }

    @RequestMapping("/test2")
    public String test2(Object o){
        System.out.println(o.toString());
        return "ok2";
    }
}
