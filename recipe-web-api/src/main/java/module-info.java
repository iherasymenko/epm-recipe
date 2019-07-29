open module recipe.web.api {
    requires recipe.services.api;
    requires spring.context;
    requires spring.web;
    requires spring.webmvc;
    exports com.epm.recipe.web_api.config;
}