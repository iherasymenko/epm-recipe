module recipe.aggregator {
    requires java.sql;
    requires spring.beans;
    requires spring.core;
    requires spring.context;
    requires spring.web;
    requires spring.webmvc;
    requires org.eclipse.jetty.server;
    requires org.eclipse.jetty.servlet;
    requires recipe.web.ui;
    requires recipe.web.api;
    requires recipe.services.impl;
    requires recipe.persistence.in_memory;
    requires recipe.persistence.jdbc;
    requires recipe.web.advice;
    requires recipe.web.security;
    opens com.epm.recipe.aggregator.config to
            spring.core,
            spring.beans,
            spring.context;
}