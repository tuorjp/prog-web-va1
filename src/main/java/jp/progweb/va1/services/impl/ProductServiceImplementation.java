package jp.progweb.va1.services.impl;

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

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        Boolean productExists = productExists(product.getId());
        if (productExists) {
            throw new ProductAlreadyExistsException(product.getId());
        }

        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product update(Long id, ProductUpdateDTO product) {
        Boolean productExists = productExists(id);
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
        if(!productExists(id)) {
            throw new ProductNotFoundException(id);
        }

        productRepository.deleteById(id);
    }

    Boolean productExists(Long id) {
        Product product = findById(id);
        return product != null;
    }
}
