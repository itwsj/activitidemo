package com.wsj.demo.controller;

import org.activiti.engine.impl.interceptor.AbstractCommandInterceptor;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandConfig;

public class MyCommandInterceptorC extends AbstractCommandInterceptor {

    @Override
        public <T> T execute(CommandConfig config, Command<T> command) {
            System.out.println("拦截器执行command.getClass().getName()" + command.getClass().getName());
            System.out.println("拦截器执行command.getClass().toString()" + command.getClass().toString());
            System.out.println("拦截器执行command.getClass().getComponentType()" + command.getClass().getComponentType());
            System.out.println("拦截器执行config.toString()" + config.toString());
            System.out.println("拦截器执行config.getTransactionPropagation().name()" + config.getTransactionPropagation().name());
            System.out.println("拦截器执行config.getTransactionPropagation().toString()" + config.getTransactionPropagation().toString());
            System.out.println("拦截器执行config.getTransactionPropagation().getDeclaringClass().getName()" + config.getTransactionPropagation().getDeclaringClass().getName());
            System.out.println("拦截器执行config.transactionRequired().toString()" + config.transactionRequired().toString());
            return this.getNext().execute(config,command);
        }
}
