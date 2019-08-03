module recipe.persistence.db_mysql {
    requires recipe.domain;
    requires recipe.persistence;
    requires spring.context;
    requires spring.beans;
    requires spring.jdbc;
    requires java.sql;
    requires slf4j.api;
    opens com.epm.recipe.persistence.db_mysql to spring.core;
    opens com.epm.recipe.persistence.db_mysql.config to spring.core;
    exports com.epm.recipe.persistence.db_mysql.config;
}