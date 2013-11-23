package com.blinkstore.controllers.errors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

import org.junit.Test;

import com.blinkstore.controllers.errors.BuyItemErrorResponse;
import com.blinkstore.controllers.errors.BuyItemsErrorHandler;

public class BuyItemsErrorHandlerTest {
	
	private BuyItemsErrorHandler handler = new BuyItemsErrorHandler();

	@Test
	public void shouldReturnBuyItemErrorResponse() {
		BuyItemErrorResponse response = handler.handleBuyItemsValidationError();
		
		assertThat(response, is(notNullValue()));
		assertThat(response.getMessage(), is(notNullValue()));
	}
}
