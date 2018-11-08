module recipe.persistence {
    requires transitive recipe.domain;
    requires java.sql;
    // requires java.sql;
    exports com.epm.recipe.persistence;
}