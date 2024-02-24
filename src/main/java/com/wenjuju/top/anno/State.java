package com.wenjuju.top.anno;

import com.wenjuju.top.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented//元注解
@Constraint(
        validatedBy = {StateValidation.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface State {
    //校验失败后的提示语
   String message()default "state参数只能是已发布或草稿";
   //指定分组
   Class<?>[]groups()default{};
   //负载 获取到state注解的附加信息
   Class<? extends Payload>[]payload()default {};
}
