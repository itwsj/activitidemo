package com.wsj.demo.controller;

import org.activiti.engine.impl.interceptor.AbstractCommandInterceptor;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandConfig;

public class MyCommandInterceptorA extends AbstractCommandInterceptor {

        @Override
        public <T> T execute(CommandConfig config, Command<T> command) {
        System.out.println("拦截器A执行" + command.getClass().getName());
        return this.getNext().execute(config,command);
    }
}
