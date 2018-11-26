open module recipe.web.ui {
    requires recipe.services.api;
    requires spring.context;
    requires spring.web;
    requires spring.webmvc;
    requires spring.security.config;
    requires spring.security.web;
    requires spring.security.core;
    requires javax.servlet.api;
    requires spring.beans;
    requires thymeleaf.spring5;
    requires thymeleaf;
    requires thymeleaf.extras.springsecurity5;
    requires spring.core;
    exports com.epm.recipe.web_ui.config;
}