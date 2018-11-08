package com.epm.recipe.aggregator;

import com.epm.recipe.persistence.db.config.RecipeDaoImplConfiguration;
import com.epm.recipe.service.impl.config.ServicesConfiguration;
import com.epm.recipe.web_ui.config.WebUiConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collection;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringJUnitWebConfig(classes = {RecipeDaoImplConfiguration.class, ServicesConfiguration.class,
        WebUiConfiguration.class})
class RecipeControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void shouldCheckResponseRecipeOfTheDay() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/recipes_ui/recipe_of_the_day"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe_of_the_day"))
                .andReturn();

        MockHttpServletResponse mockResponse = result.getResponse();
        Collection<String> responseHeaders = mockResponse.getHeaderNames();

        Assertions.assertNotNull(responseHeaders);
        Assertions.assertEquals(2, responseHeaders.size());
    }

    @Test
    void shouldGetWelcomePage() throws Exception {
        mockMvc.perform(get("/api"))
                .andExpect(status().isOk())
                .andExpect(view().name("api"));
    }
}
