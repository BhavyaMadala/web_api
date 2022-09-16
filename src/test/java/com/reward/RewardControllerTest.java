package com.reward;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reward.dto.UserRewards;
import com.reward.service.RewardServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RewardControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Mock
	private RewardServiceImpl pointsServices;

	@Mock
	private ObjectMapper objectMapper;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}



	@Test 
	public void test_getPointsByCustomerId_success() throws Exception {
		

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
				.get("/rewardsGenerated/customers/1")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status =mvcResult.getResponse().getStatus();
		assertEquals(200, status); 		

	}
	@Test 
	public void test_getPointsByCustomerId() throws Exception {
		UserRewards	points=new UserRewards (4l,123,0,0,0); 


		Mockito.when(pointsServices.getPoints(5l)).thenReturn(points);


		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
				.get("/rewardsGenerated/customers/10")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		String status =mvcResult.getResponse().getContentAsString();
		assertEquals("Invalid Customer Id", status); 		

	}

}
