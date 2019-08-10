package com.epm.recipe.domain;

import java.util.Objects;

public class Restaurant {
    public final String name;

    public Restaurant(String name) {
        this.name = Objects.requireNonNull(name, "restaurant name");
    }

    /*
      Need for BeanPropertySqlParameterSource
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurant='" + name + '\'' +
                '}';
    }
}
