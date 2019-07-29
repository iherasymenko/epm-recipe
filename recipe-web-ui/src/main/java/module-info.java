module recipe.web.ui {
    requires recipe.services.api;
    requires spring.context;
    requires spring.web;
    requires spring.webmvc;
    requires thymeleaf.spring5;
    requires thymeleaf;
    opens com.epm.recipe.web_ui.config to spring.core;
    opens com.epm.recipe.web_ui.controller to spring.web;
    exports com.epm.recipe.web_ui.config;
}