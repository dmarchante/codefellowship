package com.dmarchante.codefellowship.codefellowship;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodefellowshipApplicationTests {

	private MockMvc mockCodeFellowshipMvc;
	private MockMvc mockAppUserMvc;

	@Before
	public void setup() {
		this.mockCodeFellowshipMvc = MockMvcBuilders.standaloneSetup(new CodeFellowshipController()).build();
		this.mockAppUserMvc = MockMvcBuilders.standaloneSetup(new AppUserController()).build();
	}

	@Test
	public void testRootPage()
			throws Exception {

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.get("/");

//		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
//				.string("Hello world!");

//		this.mockMvc.perform(builder).andExpect(contentMatcher)
//				.andExpect(MockMvcResultMatchers.status().isOk());

		this.mockCodeFellowshipMvc.perform(builder)
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

//	@Test
//	public void testSignupPage()
//			throws Exception {
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
//				.get("/signup");
//
//		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
//				.string("HELLO");
//
//		this.mockMvc.perform(builder).andExpect(contentMatcher)
//				.andExpect(MockMvcResultMatchers.status().isOk());
//
//		this.mockAppUserMvc.perform(builder)
//				.andExpect(MockMvcResultMatchers.status().isOk());
//	}

//	@Test
//	public void testLoginPage()
//			throws Exception {
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
//				.get("/login");
//
//		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
//				.string("world hello");
//
//		this.mockMvc.perform(builder).andExpect(contentMatcher)
//				.andExpect(MockMvcResultMatchers.status().isOk());
//
//		this.mockAppUserMvc.perform(builder)
//				.andExpect(MockMvcResultMatchers.status().isOk());
//	}

//	@Test
//	public void testSignupPage()
//			throws Exception {
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
//				.get("/signup");
//
//		this.mockAppUserMvc.perform(builder)
//				.andExpect(MockMvcResultMatchers.status().isOk());
//	}

//	@Test
//	public void testLoginPage()
//			throws Exception {
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
//				.get("/login");
//
//		this.mockAppUserMvc.perform(builder)
//				.andExpect(MockMvcResultMatchers.status().isOk());
//	}

}
