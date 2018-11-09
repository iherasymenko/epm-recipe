module recipe.persistence.db.main {
    requires java.sql;
    requires recipe.domain;
    requires recipe.persistence;
    requires spring.context;
    requires spring.jdbc;
    requires spring.beans;
    requires org.mariadb.jdbc;
    requires spring.core;

    opens com.epm.recipe.persistence.db to spring.core;
    opens com.epm.recipe.persistence.db.config to spring.core;

    exports com.epm.recipe.persistence.db;
    exports com.epm.recipe.persistence.db.config;
}