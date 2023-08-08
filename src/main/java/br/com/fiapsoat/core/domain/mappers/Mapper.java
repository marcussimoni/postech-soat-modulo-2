package br.com.fiapsoat.core.domain.mappers;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public abstract class Mapper<Entity, DTO> {

    protected final ModelMapper mapper;
    private final Class<Entity> entity;
    private final Class<DTO> DTO;

    @SuppressWarnings("unchecked")
    public Mapper(ModelMapper mapper) {
        this.mapper = mapper;
        entity = (Class<Entity>) getType(0);
        DTO = (Class<DTO>) getType(1);
    }

    private Type getType(int index) {
        return ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[index];
    }

    public Entity dtoToEntity(DTO DTO) {
        return mapper.map(DTO, this.entity);
    }

    public DTO entityToDto(Entity entity) {
        return mapper.map(entity, this.DTO);
    }

    public List<Entity> dtoToEntity(List<DTO> dto){
        return dto.stream().map(d -> dtoToEntity(d)).collect(Collectors.toList());
    }

    public List<DTO> entityToDto(List<Entity> entity){
        return entity.stream().map(e -> entityToDto(e)).collect(Collectors.toList());
    }

}
