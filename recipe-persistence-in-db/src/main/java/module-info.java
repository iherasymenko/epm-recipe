module recipe.persistence.in_db {
    requires recipe.domain;
    requires recipe.persistence;
    requires spring.context;
    requires spring.jdbc;
    requires h2;
    requires java.sql;
    requires spring.beans;
    requires spring.core;
    opens com.epm.recipe.persistence.in_db to spring.core;
    opens com.epm.recipe.persistence.in_db.config to spring.core;
    exports com.epm.recipe.persistence.in_db.config;
}