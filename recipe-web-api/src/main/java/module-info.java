module recipe.web.api {
    requires recipe.services.api;
    requires spring.context;
    requires spring.web;
    requires spring.webmvc;
    opens com.epm.recipe.web_api.config to spring.core;
    opens com.epm.recipe.web_api.controller to spring.web;
    exports com.epm.recipe.web_api.config;
}