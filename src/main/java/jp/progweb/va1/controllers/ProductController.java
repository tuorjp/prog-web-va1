package jp.progweb.va1.controllers;

import jp.progweb.va1.dtos.ProductCreateDTO;
import jp.progweb.va1.dtos.ProductStatusUpdateDTO;
import jp.progweb.va1.dtos.ProductUpdateDTO;
import jp.progweb.va1.models.Product;
import jp.progweb.va1.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> listAllProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductCreateDTO product) {
        Product createdProduct = productService.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable(value = "id") Long id,
            @RequestBody ProductUpdateDTO productUpdateDTO
    ) {
        Product updatedProduct = productService.update(id, productUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Product> updateProductStatus(
            @PathVariable Long id,
            @RequestBody ProductStatusUpdateDTO statusUpdateDTO
    ) {
        Product updatedProduct = productService.updateStatus(id, statusUpdateDTO.getActive());
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product " + id + " deleted successfully");
    }
}
