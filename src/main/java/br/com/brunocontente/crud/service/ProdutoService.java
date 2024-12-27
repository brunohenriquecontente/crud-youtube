package br.com.brunocontente.crud.service;

import br.com.brunocontente.crud.dto.ProdutoDTO;
import br.com.brunocontente.crud.entity.Produto;
import br.com.brunocontente.crud.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoDTO salvarProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        BeanUtils.copyProperties(produtoDTO, produto);
        produto = produtoRepository.save(produto);
        return produto.toDTO();
    }

    public void deletarPorId(Integer id) {
        produtoRepository.deleteById(id);
    }

    public ProdutoDTO buscarProdutoPorId(Integer id){
        Produto produto = produtoRepository.findById(id).get();
        return produto.toDTO();
    }

    public ProdutoDTO atualizarProdutoPorId(Integer id, ProdutoDTO produtoDTO){

        Produto produto = produtoRepository.findById(id).get();
        //@TODO Criar Exceção para produto não existente.
        produto.setDescricao(produtoDTO.descricao());
        produto.setNome(produtoDTO.nome());
         produto = produtoRepository.save(produto);
        return produto.toDTO();

    }
}
