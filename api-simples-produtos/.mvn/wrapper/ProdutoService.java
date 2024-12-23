package com.example.apisimplesprodutos.service;

import com.example.apisimplesprodutos.model.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private List<Produto> produtos = new ArrayList<>();
    private Long nextId = 1L;

    public Produto criarProduto(Produto produto) {
        produto.setId(nextId++);
        produtos.add(produto);
        return produto;
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public Optional<Produto> buscarProdutoPorId(Long id) {
        return produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Optional<Produto> atualizarProduto(Long id, Produto produtoAtualizado) {
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            if (produto.getId().equals(id)) {
                produtoAtualizado.setId(id);
                produtos.set(i, produtoAtualizado);
                return Optional.of(produtoAtualizado);
            }
        }
        return Optional.empty();
    }

    public boolean deletarProduto(Long id) {
        return produtos.removeIf(p -> p.getId().equals(id));
    }
}