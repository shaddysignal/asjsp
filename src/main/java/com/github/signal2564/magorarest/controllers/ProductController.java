package com.github.signal2564.magorarest.controllers;

import com.github.signal2564.magorarest.models.Product;
import com.github.signal2564.magorarest.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/1/products", produces = APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product get(@PathVariable("id") long id) {
        return productService.get(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody Product product) {
        productService.update(id, product);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        productService.delete(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public void create(@RequestBody Product product) {
        productService.create(product);
    }

    @GetMapping("/")
    public Iterable<Product> list() {
        return productService.list();
    }

}
