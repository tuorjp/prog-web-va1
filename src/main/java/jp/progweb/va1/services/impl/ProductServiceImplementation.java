package jp.progweb.va1.services.impl;

import jp.progweb.va1.dtos.ProductCreateDTO;
import jp.progweb.va1.dtos.ProductUpdateDTO;
import jp.progweb.va1.models.Product;
import jp.progweb.va1.repositories.ProductRepository;
import jp.progweb.va1.services.ProductService;
import jp.progweb.va1.services.exceptions.ProductAlreadyExistsException;
import jp.progweb.va1.services.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product create(ProductCreateDTO product) {
        boolean existingProduct = productRepository.existsByName(product.getName());

        if (existingProduct) {
            throw new ProductAlreadyExistsException(product.getName());
        }

        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setCreatedAt(LocalDateTime.now());
        newProduct.setCreatedBy(product.getCreatedBy());
        newProduct.setActive(product.getActive());

        return productRepository.save(newProduct);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException(id);
        }

        return product.get();
    }

    @Override
    public Product update(Long id, ProductUpdateDTO product) {
        Boolean productExists = productExistsById(id);
        if (!productExists) {
            throw new ProductNotFoundException(id);
        }

        Product existingProduct = findById(id);
        existingProduct.setName(product.getName());
        existingProduct.setUpdatedAt(LocalDateTime.now());
        existingProduct.setActive(product.getActive());
        existingProduct.setUpdatedBy(product.getUpdatedBy());

        return productRepository.save(existingProduct);
    }

    @Override
    public void delete(Long id) {
        if(!productExistsById(id)) {
            throw new ProductNotFoundException(id);
        }

        productRepository.deleteById(id);
    }

    Boolean productExistsById(Long id) {
        Product product = findById(id);
        return product != null;
    }
}
