package jp.progweb.va1.services;

import jp.progweb.va1.dtos.ProductCreateDTO;
import jp.progweb.va1.dtos.ProductUpdateDTO;
import jp.progweb.va1.models.Product;

import java.util.List;

public interface ProductService {
    Product create(ProductCreateDTO product);
    List<Product> findAll();
    Product findById(Long id);
    Product update(Long id, ProductUpdateDTO product);
    void delete(Long id);
}
