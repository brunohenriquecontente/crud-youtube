package br.com.brunocontente.crud.mapper;

import br.com.brunocontente.crud.dto.ProdutoDTO;
import br.com.brunocontente.crud.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    Produto toEntity(ProdutoDTO produtoDTO);

    ProdutoDTO toDTO(Produto produto);
}
