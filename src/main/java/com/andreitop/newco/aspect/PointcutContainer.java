package com.andreitop.newco.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutContainer {

    @Pointcut("execution( * com.andreitop.newco.repository.*Repo*.find*())")
    public void repositoryFind() {
    }

    @Pointcut("execution( * com.andreitop.newco.repository.*Repo*.save*())")
    public void repositorySave() {
    }

    @Pointcut("within( com.andreitop.newco.service )")
    public void serviceMethod() {
    }

}
