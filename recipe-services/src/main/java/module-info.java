module recipe.services.impl {
    requires recipe.services.api;
    requires spring.context;
    requires java.sql;
    requires recipe.persistence.in_db;
    opens com.epm.recipe.service.impl to spring.core;
    opens com.epm.recipe.service.impl.config to spring.core;
    exports com.epm.recipe.service.impl.config;
}