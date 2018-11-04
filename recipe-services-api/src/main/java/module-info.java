module recipe.services.api {
    requires transitive recipe.domain;
    exports com.epm.recipe.service;
    exports com.epm.recipe.service.dto;
    exports com.epm.recipe.service.exceptions;
}