open module recipe.web.ui {
    requires recipe.services.api;
    requires recipe.exception;
    requires spring.context;
    requires spring.web;
    requires spring.webmvc;
    requires thymeleaf.spring5;
    requires thymeleaf;
    exports com.epm.recipe.web_ui.config;
}