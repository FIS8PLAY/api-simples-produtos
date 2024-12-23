package com.example.apisimplesprodutos.controller;

import com.example.apisimplesprodutos.model.Produto;
import com.example.apisimplesprodutos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.criarProduto(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        return produtoService.buscarProdutoPorId(id)
                .map(produto -> new ResponseEntity<>(produto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        return produtoService.atualizarProduto(id, produto)
                .map(produtoAtualizado -> new ResponseEntity<>(produtoAtualizado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        if (produtoService.deletarProduto(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}