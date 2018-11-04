module recipe.persistence.in_db {
    requires recipe.domain;
    requires recipe.persistence;
    requires spring.context;
    requires persistence.api;
    opens com.epm.recipe.persistence.in_db to spring.core;
    opens com.epm.recipe.persistence.in_db.config to spring.core;
    exports com.epm.recipe.persistence.in_db.config;
}