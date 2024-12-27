package br.com.brunocontente.crud.controller;

import br.com.brunocontente.crud.dto.ProdutoDTO;
import br.com.brunocontente.crud.entity.Produto;
import br.com.brunocontente.crud.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ProdutoDTO salvarProduto(@RequestBody ProdutoDTO produtoDTO){
        return produtoService.salvarProduto(produtoDTO);
    }

    @GetMapping(value = "{id}")
    public ProdutoDTO buscarProdutoPorId(@PathVariable Integer id){
        return produtoService.buscarProdutoPorId(id);
    }

    @DeleteMapping(value = "{id}")
    public void deletarProdutoPorId(@PathVariable Integer id){
        produtoService.deletarPorId(id);
    }

    @PutMapping(value = "{id}")
    public ProdutoDTO atualizarProduto(@RequestBody ProdutoDTO produtoDTO, @PathVariable Integer id){
        return produtoService.atualizarProdutoPorId(id, produtoDTO);
    }

}
