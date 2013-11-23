package com.blinkstore.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.blinkstore.dao.IUserShoppingDao;
import com.blinkstore.model.UserShopping;
import com.blinkstore.services.UserShoppingService;


public class UserShoppingServiceTest {


	private UserShoppingService userShoppingService;
	private IUserShoppingDao userShoppingDaoMock;
	
	@Before
	public void before() {
		userShoppingDaoMock = mock(IUserShoppingDao.class);
		userShoppingService = new UserShoppingService(userShoppingDaoMock);
	}
	
	@Test
	public void shouldReturnUserShoppings() {
		when(userShoppingDaoMock.getUserShopping()).thenReturn(createCurrentShoppings());
		List<UserShopping> actualUserShoppings = userShoppingService.getUserShoppings();
		
		List<UserShopping> expectedUserShoppings = createExpectedCurrentShoppings();
		assertThat(actualUserShoppings, is(expectedUserShoppings));
		
	}
	
	private Map<Long, UserShopping> createCurrentShoppings() {
		Map<Long, UserShopping> result = new HashMap<>();
		result.put(1L, new UserShopping("item1", 1L, 1L));
		result.put(2L, new UserShopping("item2", 1L, 2L));
		return result;
	}
	
	private List<UserShopping> createExpectedCurrentShoppings() {
		List<UserShopping> result = new ArrayList<>();
		result.add(new UserShopping("item1", 1L, 1L));
		result.add(new UserShopping("item2", 1L, 2L));
		return result;
	}
}
