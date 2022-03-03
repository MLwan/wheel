package com.baron.spring.exception.testcontroller;

import com.baron.spring.exception.JudgeEmptyUtil;
import com.baron.spring.exception.MailCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/mail")
public class ExtTestController {
    @RequestMapping("/ext")
    public void testExt(){
        //ExceptionCast.throwParamNullExt(MailCode.MAIL_ID_NOTEXISTS);

        String c = null;
        /*Assert.notNull(c);*/

        //JudgeEmptyUtil.judgeObjectEmpty(new Object(), MailCode.MAIL_ID_NOTEXISTS);

        //JudgeEmptyUtil.judgeStringEmpty(c,MailCode.MAIL_ID_NOTEXISTS);

        int a = 6/0;
    }
}
