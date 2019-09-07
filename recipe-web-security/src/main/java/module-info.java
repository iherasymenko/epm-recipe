module recipe.web.security {
    requires recipe.domain;
    requires recipe.services.api;
    requires spring.web;
    requires spring.core;
    requires spring.context;
    requires spring.webmvc;
    requires spring.beans;
    requires java.jwt;
    opens com.epm.recipe.security.config to spring.core;
    opens com.epm.recipe.security to spring.core;
    exports com.epm.recipe.security.config;
    exports com.epm.recipe.security;
}