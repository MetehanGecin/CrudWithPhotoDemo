package com.CrudEx.CrudWPhoto.service;


import com.CrudEx.CrudWPhoto.model.Product;
import com.CrudEx.CrudWPhoto.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProduct(String keyword) {
        if (keyword != null) {
            return productRepository.search(keyword);
        }else

        return (List<Product>) productRepository.findAll();
    }

    public Product saveProduct(Product product){
        return this.productRepository.save(product);
    }

    public Product getProductById(long id){
        Optional<Product> optional = productRepository.findById(id);
        Product product = null;
        if (optional.isPresent()){
            product = optional.get();
        }else {
            throw new RuntimeException(" Employee not found for id : : " + id);
        }
        return product;
    }

    public void deleteProductById(long id){
        this.productRepository.deleteById(id);
    }

    public Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.productRepository.findAll(pageable);
    }
}
