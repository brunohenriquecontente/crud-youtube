package br.com.brunocontente.crud.controller;

import br.com.brunocontente.crud.dto.ProdutoDTO;
import br.com.brunocontente.crud.exception.ProdutoNotFoundException;
import br.com.brunocontente.crud.service.ProdutoService;
import org.springframework.data.domain.Page;
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
    public ProdutoDTO buscarProdutoPorId(@PathVariable Integer id) throws ProdutoNotFoundException {
        return produtoService.buscarProdutoPorId(id);
    }

    @DeleteMapping(value = "{id}")
    public void deletarProdutoPorId(@PathVariable Integer id){
        produtoService.deletarPorId(id);
    }

    @PutMapping(value = "{id}")
    public ProdutoDTO atualizarProduto(@RequestBody ProdutoDTO produtoDTO, @PathVariable Integer id) throws ProdutoNotFoundException {
        return produtoService.atualizarProdutoPorId(id, produtoDTO);
    }

    @GetMapping
    public Page<ProdutoDTO> buscarTodosProdutos(
            ProdutoDTO produtoDTO,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(value = "orderBy", required = false,  defaultValue = "id") String orderBy
            ){
        return produtoService.buscarTodosProdutos(produtoDTO, page, size, sort, orderBy);
    }

}
