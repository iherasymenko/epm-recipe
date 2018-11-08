package com.epm.recipe.aggregator;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.db.config.RecipeDaoImplConfiguration;
import com.epm.recipe.service.RecipeService;
import com.epm.recipe.service.impl.config.ServicesConfiguration;
import com.epm.recipe.web_ui.config.WebUiConfiguration;
import com.epm.recipe.web_ui.controller.RestRecipeController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(classes = {RecipeDaoImplConfiguration.class, ServicesConfiguration.class,
        WebUiConfiguration.class})
class RestRecipeControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Mock
    private RecipeService recipeService;
    @InjectMocks
    private RestRecipeController recipeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void shouldGetRecipeById() {
        //Given
        Recipe recipe = new Recipe("Test recipe", 1L);

        //When
        Mockito.when(recipeService.getRecipeById(anyLong())).thenReturn(java.util.Optional.of(recipe));
        ResponseEntity<Recipe> responseRecipe = recipeController.recipe(1L);

        //Then
        Assertions.assertNotNull(recipe);
        Assertions.assertEquals("Test recipe", responseRecipe.getBody().getTitle());
        Assertions.assertEquals(HttpStatus.OK, responseRecipe.getStatusCode());

        Mockito.verify(recipeService, times(1)).getRecipeById(1L);
        Mockito.verifyNoMoreInteractions(recipeService);
    }

    @Test
    void shouldGetAllRecipes() {
        //Given
        List<Recipe> recipes = Arrays.asList(
                new Recipe("Test recipe 1", 1L),
                new Recipe("Test recipe 2", 2L));

        //When
        Mockito.when(recipeService.findAllRecipes()).thenReturn(recipes);
        ResponseEntity<List<Recipe>> responseRecipes = recipeController.recipes();

        //Then
        Assertions.assertNotNull(recipes);
        Assertions.assertEquals(responseRecipes.getBody().size(), 2);
        Assertions.assertEquals(HttpStatus.OK, responseRecipes.getStatusCode());

        Mockito.verify(recipeService, times(1)).findAllRecipes();
        Mockito.verifyNoMoreInteractions(recipeService);
    }

    @Test
    void shouldCheckResponseRecipeOfTheDay() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/recipes/recipe_of_the_day"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Hashbrowns")))
                .andReturn();

        MockHttpServletResponse mockResponse = result.getResponse();
        org.assertj.core.api.Assertions.assertThat(mockResponse.getContentType())
                .isEqualTo("application/json;charset=UTF-8");
    }

    @Test
    void shouldBeOkStatus() throws Exception {
        mockMvc.perform(get("/api/recipes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Hashbrowns")));
    }

    @Test
    void shouldBeNotFoundStatus() throws Exception {
        mockMvc.perform(get("/api/recipes/12345678"))
                .andExpect(status().isNotFound());
    }
}
