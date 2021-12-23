package com.revature.caseclothes.daounittests;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
//import javax.websocket.Session;

import org.hibernate.Session;
//import org.apache.catalina.Session;
//import org.h2.engine.Session;
//import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.web.ServerProperties.Reactive.Session;
//import org.springframework.boot.autoconfigure.web.ServerProperties.Reactive.Session;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caseclothes.dto.LoginDTO;
import com.revature.caseclothes.model.User;
import com.revature.caseclothes.model.UserRole;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class AuthenticationControllerTest {
@Autowired
private MockMvc mvc;
@Autowired
private EntityManager em;
//@BeforeAll

@Transactional
@Test
public void testlogin_admin() throws Exception {
	UserRole admin = new UserRole("admin");
	this.em.persist(admin);
	UserRole customer =new UserRole("customer");
	this.em.persist(customer);
	User alemu = new User("alemu","alemu123","Alemu","Robele","rob@gmail.com","678-678-6789","1818 Georgia",admin);
	this.em.persist(alemu);
	this.em.flush();
	ObjectMapper mapper = new ObjectMapper();
	LoginDTO dto = new  LoginDTO("alemu","alemu123");
	String jsonToSend = mapper.writeValueAsString(dto);
	MockHttpServletRequestBuilder builder =  MockMvcRequestBuilders.post("/login").content(jsonToSend)
			.contentType(MediaType.APPLICATION_JSON);
	UserRole expectedUserRole = new UserRole("admin");
	expectedUserRole.setId(1);
	User expectedObject =new User("alemu","alemu123","Alemu","Robele","rob@gmail.com","678-678-6789","1818 Georgia",expectedUserRole);
	expectedObject.setId(1);
	String expectedJson = mapper.writeValueAsString(expectedObject);
	 this.mvc.perform(builder)
	 .andExpect(MockMvcResultMatchers.status().is(200))
	 .andExpect(MockMvcResultMatchers.content().json(expectedJson));
}
}
