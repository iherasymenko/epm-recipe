module recipe.services.api {
    requires transitive recipe.domain;
    requires java.sql;
    exports com.epm.recipe.service;
}