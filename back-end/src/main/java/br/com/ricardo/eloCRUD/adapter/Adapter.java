package br.com.ricardo.eloCRUD.adapter;

public interface Adapter<Dto, Entity> {

    Dto toEntity(Entity entity);

    Entity toDto(Dto dto);
}
