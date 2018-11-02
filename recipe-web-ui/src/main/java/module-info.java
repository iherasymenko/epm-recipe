open module recipe.web.ui {
    requires recipe.services.api;
    requires spring.context;
    exports com.epm.recipe.web_ui;
    exports com.epm.recipe.web_ui.config;
}