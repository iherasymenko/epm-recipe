module recipe.persistence.in_db {
    requires recipe.domain;
    requires spring.context;
    requires java.sql;
    opens com.epm.recipe.persistence.in_db to spring.core;
    opens com.epm.recipe.persistence.in_db.config to spring.core;
    exports com.epm.recipe.persistence.in_db.config;
    exports com.epm.recipe.persistence.in_db;
}