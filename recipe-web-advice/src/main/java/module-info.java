module recipe.web.advice{
    requires spring.core;
    requires spring.context;
    requires recipe.domain;
    requires spring.webmvc;
    requires spring.web;
    opens com.epm.recipe.advice.config to spring.core;
    opens com.epm.recipe.advice to spring.core;
    exports com.epm.recipe.advice;
    exports com.epm.recipe.advice.config;
}