package com.sihwan.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) //이 어노테이션이 사용될 수 있는 위치 지정. (PARAMETER로 선언된 객체에서만 가능)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
