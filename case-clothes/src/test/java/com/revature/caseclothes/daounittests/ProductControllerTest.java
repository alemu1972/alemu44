package com.revature.caseclothes.daounittests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.web.servlet.MockMvc;

import com.revature.caseclothes.controller.ProductController;
import com.revature.caseclothes.dao.ProductsDAO;
import com.revature.caseclothes.service.ProductsService;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ProductsService ps;
	@MockBean
	private ProductsDAO pd;
	@Test
	public void testResponseEntity() {
		
	}
	

}
