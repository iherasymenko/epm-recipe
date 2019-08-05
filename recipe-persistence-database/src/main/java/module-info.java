module recipe.persistence.database {
    requires recipe.domain;
    requires recipe.persistence;
    requires spring.context;
    requires spring.jdbc;
    requires java.sql;
    requires spring.beans;
    requires org.mariadb.jdbc;
    opens com.epm.recipe.persistence.database to spring.core;
    opens com.epm.recipe.persistence.database.config to spring.core;
    exports com.epm.recipe.persistence.database.config;
    exports com.epm.recipe.persistence.database;
}