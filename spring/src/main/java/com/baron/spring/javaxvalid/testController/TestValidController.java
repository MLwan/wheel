package com.baron.spring.javaxvalid.testController;

import com.baron.spring.javaxvalid.dto.ValidationDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
public class TestValidController {

    @GetMapping("/javaxvalid")
//  通过全局异常捕获获取参数校验异常（package com.baron.spring.exception.javaxValidExt;） 20230327beg
//    public String test(@RequestBody @Valid Book book, BindingResult bindingResult){
    public String test(@RequestBody @Valid ValidationDto validationDto){
//        if(bindingResult.hasErrors()){
//            System.out.println("校验失败："+ bindingResult.getFieldError().getDefaultMessage());
//            return bindingResult.getFieldError().getDefaultMessage();
//        }
//  20230327 end

        System.out.println("book_name =="+validationDto.getName());
        System.out.println("book_pageSize =="+validationDto.getPageSize());


        return validationDto.toString();
    }

}
