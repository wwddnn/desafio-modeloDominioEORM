package com.devsuperior.dscommerce.controllers;
import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

//controller implements the resources
//controller that respond on route '/products'
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    //this annotation @Autowired is for injection component
    //dependency
    @Autowired
    private ProductService service;

    //method for return product dto.
    //this '/{id}' is for received several parameter on the route
    //pathvariable received several id on the route
    //findById() is the same method in class ProductService
    //ResponseEntity customize the response 200
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    //pageable to view products by pages
    //findAll() is the same method in class ProductService
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        Page<ProductDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    //insert() method is a post method, and go save on database the body inserted on postman
    //@RequestBody is annotation to insert data on the body of request
    //instantiate object URI, is the good practice. the response come with code 201 and resource link
    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }
}
