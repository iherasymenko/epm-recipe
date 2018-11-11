module recipe.persistence.in_db {
    requires recipe.domain;
    requires recipe.persistence;
    requires recipe.exception;
    requires spring.context;
    requires spring.jdbc;
    requires spring.beans;
    requires java.sql;
    opens com.epm.recipe.persistence.in_db to spring.core;
    opens com.epm.recipe.persistence.in_db.config to spring.core;
    exports com.epm.recipe.persistence.in_db;
    exports com.epm.recipe.persistence.in_db.config;
}