module recipe.persistence.db {

    requires recipe.domain;
    requires recipe.persistence;
    requires spring.context;
    requires java.sql;

    opens com.epm.recipe.persistence.db.connection to spring.core;
    opens com.epm.recipe.persistence.db.dao to spring.core;
    opens com.epm.recipe.persistence.db.config to spring.core;

    exports com.epm.recipe.persistence.db.config;
}