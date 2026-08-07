package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.config.AppConfig;
import com.example.beans.MyFirstBean;
import com.example.service.IRoutineService;
import com.example.service.IUserService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MyFirstBean myFirstBean = (MyFirstBean) context.getBean("myFirstBean");
        System.out.println(myFirstBean.getMensaje());

        IUserService userService = (IUserService) context.getBean("userService");
        System.out.println("Users: " + userService.findAll());

        IUserService userService2 = (IUserService) context.getBean("userService");
        System.out.println("Are userService and userService2 the same instance? " + (userService == userService2));

        IRoutineService routineService = (IRoutineService) context.getBean("routineService");
        System.out.println("Routines: " + routineService.findAll());

        context.close();
    }
}