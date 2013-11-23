package com.blinkstore.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.blinkstore.controllers.UserShoppingController;
import com.blinkstore.model.UserShopping;
import com.blinkstore.services.UserShoppingService;


public class UserShoppingControllerTest {

	private UserShoppingController userShoppingController;
	private UserShoppingService userShoppingServiceMock;
	
	@Before
	public void before() {
		userShoppingServiceMock = mock(UserShoppingService.class);
		userShoppingController = new UserShoppingController(userShoppingServiceMock);
	}
	
	@Test
	public void shouldReturnUserShoppings() {
		when(userShoppingServiceMock.getUserShoppings()).thenReturn(createUserShoppings());
		
		List<UserShopping> actualUserShopping = userShoppingController.getUserShopping();
		
		assertThat(actualUserShopping, is(createUserShoppings()));
	}
	
	private List<UserShopping> createUserShoppings() {
		List<UserShopping> result = new ArrayList<>();
		result.add(new UserShopping("item1", 1L));
		result.add(new UserShopping("item2", 1L));
		return result;
	}
}
