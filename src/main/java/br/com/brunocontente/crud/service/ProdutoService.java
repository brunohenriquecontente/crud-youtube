package br.com.brunocontente.crud.service;

import br.com.brunocontente.crud.dto.ProdutoDTO;
import br.com.brunocontente.crud.entity.Produto;
import br.com.brunocontente.crud.mapper.ProdutoMapper;
import br.com.brunocontente.crud.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoDTO salvarProduto(ProdutoDTO produtoDTO) {
        Produto produto = ProdutoMapper.INSTANCE.toEntity(produtoDTO);
        produto = produtoRepository.save(produto);
        return ProdutoMapper.INSTANCE.toDTO(produto);
    }

    public void deletarPorId(Integer id) {
        produtoRepository.deleteById(id);
    }

    public ProdutoDTO buscarProdutoPorId(Integer id){
        Produto produto = produtoRepository.findById(id).get();
        return ProdutoMapper.INSTANCE.toDTO(produto);
    }

    public ProdutoDTO atualizarProdutoPorId(Integer id, ProdutoDTO produtoDTO){

        Produto produto = produtoRepository.findById(id).get();
        //@TODO Criar Exceção para produto não existente.
        produto.setDescricao(produtoDTO.descricao());
        produto.setNome(produtoDTO.nome());
         produto = produtoRepository.save(produto);
        return ProdutoMapper.INSTANCE.toDTO(produto);

    }

    public Page<ProdutoDTO> buscarTodosProdutos(ProdutoDTO produtoDTO, Integer page, Integer size, String sort, String orderBy){
        Sort.Direction direction = "desc".equalsIgnoreCase(sort) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, orderBy));
        Produto produto = new Produto();

        BeanUtils.copyProperties(produtoDTO, produto);

        ExampleMatcher matcher = ExampleMatcher.matching()
                         .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Produto> produtoExample = Example.of(produto, matcher);
        Page<Produto> produtos = produtoRepository.findAll(produtoExample, pageable);

        return produtos.map(ProdutoMapper.INSTANCE::toDTO);

    }
}
