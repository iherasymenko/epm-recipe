package com.epm.recipe.persistence.in_memory;

public enum DatabaseQuery {

    GET_ALL_RECIPES("select * from recipes"),
    GET_BY_ID_RECIPE("select * from recipes where id = ?"),
    CREATE_RECIPE("insert into recipes (title) values (?)"),
    DELETE_RECIPE("delete from recipes where id = ?"),
    UPDATE_RECIPE("update recipes set title = ? where id = ?");

    private String query;

    DatabaseQuery(String query) {
        this.query = query;
    }

    public String query() {
        return query;
    }
}
