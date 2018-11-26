module recipe.persistence.in_mariadb {
    requires recipe.domain;
    requires recipe.persistence;
    requires spring.context;
    requires spring.jdbc;
    requires spring.beans;
    requires java.sql;
    opens com.epm.recipe.persistence.in_mariadb to spring.core;
    opens com.epm.recipe.persistence.in_mariadb.config to spring.core;
    exports com.epm.recipe.persistence.in_mariadb;
    exports com.epm.recipe.persistence.in_mariadb.config;
}