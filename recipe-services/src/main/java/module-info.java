module recipe.services.impl {
    requires recipe.services.api;
    requires recipe.persistence;
    requires spring.context;
    requires java.jwt;
    requires spring.beans;
    opens com.epm.recipe.service.impl to spring.core;
    opens com.epm.recipe.service.impl.config to spring.core;
    exports com.epm.recipe.service.impl.config;
}