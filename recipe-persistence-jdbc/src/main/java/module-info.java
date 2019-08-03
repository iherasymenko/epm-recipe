module recipe.persistence.idbc {
    requires java.sql;
    requires logback.classic;
    requires logback.core;
    requires slf4j.api;
    requires recipe.domain;
    requires recipe.persistence;
    requires spring.context;
    requires org.mariadb.jdbc;
    opens com.epm.recipe.persistence.jdbc to spring.core;
    opens com.epm.recipe.persistence.jdbc.config to spring.core;
    exports com.epm.recipe.persistence.jdbc.config;
}