module recipe.aggregator {
    requires spring.web;
    requires spring.webmvc;
    requires jetty.server;
    requires jetty.servlet;
    requires recipe.web.ui;
    requires recipe.services.impl;
    requires recipe.persistence.db;
    requires recipe.persistence.in_memory;
    requires javax.servlet.api;
    requires slf4j.api;
}