package com.epm.recipe.persistence.in_db.converter;

import java.util.List;

public interface Converter <Obj, Dto> {
    Dto asDto(Obj obj);
    Obj asObj(Dto dto);
    List<Obj> asObj(List<Dto> dtoList);
}
