package com.foryou.validation;

import com.foryou.constraint.MyConstraint;
import com.foryou.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidation implements ConstraintValidator<MyConstraint,Object> {
    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("校验逻辑初始化...");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String s = helloService.sayHello();
        System.out.println(s);
        System.out.println("校验逻辑...");
        return false;
    }
}
