package com.baron.spring.javaxvalid.annotation;


import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义xss校验注解 实现自定义校验
 *
 * @author tpic
 */
public class XssValidator implements ConstraintValidator<Xss, String>
{
    private static final String HTML_PATTERN = "<(\\S*?)[^>]*>.*?|<.*? />";
    private static final String HTML_PATTERN2 = "!";

    /**
     * 自定义包含 ! 和不为空 校验
    */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext)
    {
        if (StringUtils.isBlank(value))
        {
            return false;
        }
        return !containsHtml(value);
    }

    public static boolean containsHtml(String value)
    {
//        Pattern pattern = Pattern.compile(HTML_PATTERN);
//        Matcher matcher = pattern.matcher(value);
//        return matcher.matches();
        Pattern pattern = Pattern.compile(HTML_PATTERN2);
        boolean matcher = pattern.matcher(value).find();
        return matcher;
    }
}
