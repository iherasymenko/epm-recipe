module recipe.aggregator {
    requires java.sql;
    requires spring.context;
    requires spring.web;
    requires spring.webmvc;
    requires spring.core;
    requires org.eclipse.jetty.server;
    requires org.eclipse.jetty.servlet;
    requires recipe.web.ui;
    requires recipe.web.api;
    requires recipe.services.impl;
    requires recipe.persistence.in_memory;
    requires recipe.persistence.idbc;
    requires spring.beans;
    opens com.epm.recipe.aggregator.config to spring.core;
    exports com.epm.recipe.aggregator.config;
}