module recipe.persistence.jdbc {
    requires recipe.domain;
    requires recipe.persistence;
    requires spring.context;
    requires spring.jdbc;
    requires com.zaxxer.hikari;
    requires java.sql;
    requires org.mariadb.jdbc;
    opens com.epm.recipe.persistence.jdbc to spring.core;
    opens com.epm.recipe.persistence.jdbc.config to spring.core;
    exports com.epm.recipe.persistence.jdbc.config;
}