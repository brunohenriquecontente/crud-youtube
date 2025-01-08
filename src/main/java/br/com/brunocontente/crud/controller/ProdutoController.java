package br.com.brunocontente.crud.controller;

import br.com.brunocontente.crud.dto.ProdutoDTO;
import br.com.brunocontente.crud.exception.ProdutoNotFoundException;
import br.com.brunocontente.crud.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.annotations.ParameterObject;
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
    @Operation(
            summary = "Salvar um produto",
            description = "Salva um produto no banco de dados",
            tags = {"produto"}
    )
    public ProdutoDTO salvarProduto(@RequestBody ProdutoDTO produtoDTO){
        return produtoService.salvarProduto(produtoDTO);
    }

    @GetMapping(value = "{id}")
    @Operation(
            summary = "Obter um produto por ID",
            description = "Obtém um produto por id",
            tags = {"produto"}
    )
    public ProdutoDTO buscarProdutoPorId(@PathVariable Integer id) throws ProdutoNotFoundException {
        return produtoService.buscarProdutoPorId(id);
    }

    @DeleteMapping(value = "{id}")
    @Operation(
            summary = "Deletar um produto por ID",
            description = "Deleta um produto por id",
            tags = {"produto"}
    )
    public void deletarProdutoPorId(@PathVariable Integer id){
        produtoService.deletarPorId(id);
    }

    @PutMapping(value = "{id}")
    @Operation(
            summary = "Atualizar um produto por ID",
            description = "Atualiza um produto por id",
            tags = {"produto"}
    )
    public ProdutoDTO atualizarProduto(@RequestBody ProdutoDTO produtoDTO, @PathVariable Integer id) throws ProdutoNotFoundException {
        return produtoService.atualizarProdutoPorId(id, produtoDTO);
    }

    @GetMapping
    @Operation(
            summary = "Listar produtos por filtro",
            description = "Lista um produto com base nos filtros fornecidos e retorna uma paginação",
            tags = {"produto"},
            parameters = {
                    @Parameter(name = "page", description = "Número da página", required = false, example = "0"),
                    @Parameter(name = "size", description = "Tamanho da página", required = false, example = "10"),
                    @Parameter(name = "sort", description = "Ordena por ASC ou DESC", required = false, example = "ASC"),
                    @Parameter(name = "orderBy", description = "Campo para ordenenar", required = false, example = "id"),
            }
    )
    public Page<ProdutoDTO> buscarTodosProdutos(
            @ParameterObject ProdutoDTO produtoDTO,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(value = "orderBy", required = false,  defaultValue = "id") String orderBy
            ){
        return produtoService.buscarTodosProdutos(produtoDTO, page, size, sort, orderBy);
    }

}
