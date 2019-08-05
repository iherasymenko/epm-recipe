module recipe.persistence.jdbc {
    requires recipe.domain;
    requires recipe.persistence;
    requires java.sql;
    requires org.mariadb.jdbc;
    requires spring.context;
    requires spring.beans;
    requires spring.jdbc;
    opens com.epm.recipe.persistence.jdbc to spring.core;
    opens com.epm.recipe.persistence.jdbc.config to spring.core;
    exports com.epm.recipe.persistence.jdbc.config;
}