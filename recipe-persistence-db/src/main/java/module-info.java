module recipe.persistence.db {
    requires recipe.domain;
    requires recipe.persistence;
    requires spring.context;
    requires spring.jdbc;
    requires spring.beans;
    requires spring.core;
    requires java.sql;
    requires spring.tx;
    requires slf4j.api;
    opens com.epm.recipe.persistence.db to spring.core;
    opens com.epm.recipe.persistence.db.config to spring.core;
    exports com.epm.recipe.persistence.db.config;
    exports com.epm.recipe.persistence.db;
}