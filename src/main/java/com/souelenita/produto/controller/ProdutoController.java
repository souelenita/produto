package com.souelenita.produto.controller;

import com.souelenita.produto.model.Produto;
import com.souelenita.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    ProdutoController(ProdutoRepository produtoRepository){
        this.repository = produtoRepository;
    }

    @GetMapping
    public List findAll(){
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto create(@RequestBody Produto produto){
        return repository.save(produto);
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity update(@PathVariable long id,
                                 @RequestBody Produto produto){
        return repository.findById(id)
                .map(record -> {

                    record.setNome(produto.getNome());;
                    record.setPreco(produto.getPreco());
                    record.setQuantidade(produto.getQuantidade());
                    Produto updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id){
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
