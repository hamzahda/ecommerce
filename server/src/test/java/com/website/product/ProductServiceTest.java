package com.website.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.website.model.Product;
import com.website.repository.ProductRepository;
import com.website.service.ProductServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/* 
Author hamzahda

*/




@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository ProductRepository;

    
    @Test
    public void getProductTest(){
        Product mockProduct = mock(Product.class);
        long id = 1;
        
        when(ProductRepository.findById(id)).thenReturn(Optional.of(mockProduct));
        assertEquals(mockProduct, productService.getProduct(id));  
    }

    @Test
    public void getProductsTest(){
        List<Product> Products = new ArrayList<>();
        Product Product = mock(Product.class);
        
        Products.add(Product);
        when(Product.getId()).thenReturn(new Long(1));
        when(Product.getName()).thenReturn("name");
        when(Product.getStock()).thenReturn(100);
        when(Product.getPrice()).thenReturn(1.00);
        when(ProductRepository.findAll()).thenReturn(Products);
        
        List<Product> retrievedProducts = productService.getAll();
        
		assertEquals(retrievedProducts.size(), Products.size());
        assertEquals(retrievedProducts.get(0), Products.get(0));
    }

    @Test
    public void createProductTest(){
        Product Product = mock(Product.class);

        when(Product.getId()).thenReturn(new Long(1));
        when(Product.getName()).thenReturn("name");
        when(Product.getStock()).thenReturn(100);
        when(Product.getPrice()).thenReturn(1.00);

		when(ProductRepository.save(Product)).thenReturn(Product);
		assertEquals(Product, productService.createProduct(Product));
    }


    @Test
    public void modifProductTest(){
        Product Product = mock(Product.class);
        Product ProductData = mock(Product.class);

        when(Product.getId()).thenReturn(new Long(1));
        when(Product.getName()).thenReturn("name");
        when(Product.getStock()).thenReturn(100);
        when(Product.getPrice()).thenReturn(1.00);     

        when(ProductRepository.save(Product)).thenReturn(Product);
        assertEquals(Product, productService.changeDetails(Product.getId() , ProductData));
    }



    @Test
    public void deleteProductTest(){
        Product Product = mock(Product.class);
        productService.createProduct(Product);
        verify(ProductRepository).save(Product);    
    }

    @Test
	public void checkProduct() {
		Long id = new Long(1);
		when(ProductRepository.existsById(id)).thenReturn(true);
		assertTrue(productService.checkProduct(id));
    }
    
/*   @Test
	public void search() {
        String name = new String("name");
        Product Product = mock(Product.class);
		when(ProductRepository.findByProductname(name)).thenReturn(Product);
		assertTrue(productService.search(name) != null);
    }
*/
}