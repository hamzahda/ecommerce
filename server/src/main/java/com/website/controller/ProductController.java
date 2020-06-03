package com.website.controller;

import java.util.List;

import com.website.dto.ProductDTO;
import com.website.model.Product;
import com.website.service.ProductServiceImpl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

/* 
Author hamzahda

*/

@RestController
@RequestMapping("/Products")
public class ProductController {

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  private ProductServiceImpl ProductService;
  private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


  /*
    @return list of all the  Products
  */
  @ApiOperation(value = "${ProductController.getAll}")
  public ResponseEntity<List<Product>> getAll() {
    logger.info("GET: getAll");
    return ResponseEntity.ok(ProductService.getAll());
  }

  
  /*
    @return Product by his id
  */
  @ApiOperation(value = "${ProductController.getProducts(id)}")
  @GetMapping(value = "/{id}")
  public ResponseEntity<Product> getProduct(@PathVariable Long id) {
    logger.info("GET: getProduct");
    if (!ProductService.checkProduct(id)) {
      logger.error("product with id %d not found", id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(ProductService.getProduct(id));
  }

  /*
    @return created Product
  */
  @ApiOperation(value = "${ProductController.postProduct}")
  public ResponseEntity<Product> postProduct(Product product) {
   logger.info("POST: postProduct");
   return ResponseEntity.ok(ProductService.createProduct(product));
}


  /*
    search for a Product
  */

  @ApiOperation(value = "${ProductController.search(name)}")
  @GetMapping(value = "/{Productname}")
  public ProductDTO search(@PathVariable String Productname) {
    logger.info("GET: search");
    return modelMapper.map(ProductService.search(Productname), ProductDTO.class);
  }
  
  
  
  /*
    delete Product by his id
  */
  @ApiOperation(value = "${ProductController.deleteProduct(id)}")
  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<String> deleteProduct(@PathVariable final long id) {
    logger.info("DELETE: deleteProduct");
    if (!ProductService.checkProduct(id)) {
      logger.error("product with id %d not found", id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(String.format("Product with ID : %d is deleted successfuly", id));
  }
  
  /*
    @return the modified Product
  */ 
  @ApiOperation(value = "${ProductController.modifyProduct}")
  @PutMapping("/{id}")
  public ResponseEntity<Product> modifyProduct(@RequestBody final Product ProductData, @PathVariable final Long id) {
    logger.info("put: modifyProduct");
    boolean Product = ProductService.checkProduct(id);
    if (!Product) {
      logger.error("product with id %d not found", id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(ProductService.changeDetails(id, ProductData));
  }


}