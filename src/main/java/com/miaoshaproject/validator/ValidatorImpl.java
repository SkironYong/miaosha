package com.miaoshaproject.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


@Component
public class ValidatorImpl implements InitializingBean {
    private Validator validator;

    //实现校验方法并返回校验结果
    public ValidationResult validate(Object bean) {
        final ValidationResult result = new ValidationResult();
        //校验bean
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);
        if(constraintViolationSet.size()>0) {
            //有错误
            result.setHasErrors(true);
            //java8新特性 Lambda表达式，遍历set里的错误字段信息，找出字段名和错误信息并返回为ValidationResult的形式
            constraintViolationSet.forEach(constraintViolation->{
                String errMsg = constraintViolation.getMessage();
                String propertyName = constraintViolation.getPropertyPath().toString();
                result.getErrMsgMap().put(propertyName,errMsg);
            });
        }
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //将hibernate validator通过工厂的初始化方式使其实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
