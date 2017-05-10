package com.hao.onlineExam.logging;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	@Pointcut("execution(public * com.hao.onlineExam.service.imp.UserServiceImp.createUser(..))")
    public void myMethod(){};
    
    /*@Before("execution(public void com.hao.onlineExam.dao.impl.UserDAOImpl.save(com.hao.onlineExam.model.User))")*/
    @Before("myMethod()")
    public void before() {
        System.out.println("method staet");
    } 
    @After("myMethod()")
    public void after() {
        System.out.println("method after");
    } 
    
//    @AfterReturning("execution(public * com.hao.onlineExam.dao.imp.UserHibernateDAOImp.createUser(..))")
    @AfterReturning(value = "myMethod()",returning = "returnValue")
    public void AfterReturning() {
        System.out.println("method AfterReturning");
    } 
    
//    @AfterThrowing("execution(public * com.hao.onlineExam.dao.imp.UserHibernateDAOImp.createUser(..))")
    @AfterThrowing(throwing = "ex",value = "myMethod()")
    public void AfterThrowing() {
        System.out.println("method AfterThrowing");
    } 
}
