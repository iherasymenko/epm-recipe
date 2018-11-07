module recipe.aggregator {
    requires java.sql;
    requires spring.context;
    requires spring.web;
    requires spring.webmvc;
    requires jetty.server;
    requires jetty.servlet;
    requires recipe.web.ui;
    requires recipe.services.impl;
    requires recipe.persistence.in_memory;
    requires recipe.persistence.in_db;
}