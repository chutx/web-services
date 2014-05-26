package my.training.ws.service;

import my.training.ws.service.api.TimeService;
import my.training.ws.service.impl.TimeServiceImpl;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TimeServerTest {

	private TimeService timeServer;
	
	@Before
	public void setup(){
		timeServer = new TimeServiceImpl();
	}
	
	@Test
	public void getTimeAsString(){
		String result = timeServer.getTimeAsString();
		
		Assert.assertThat(result, CoreMatchers.is(String.class));
	}
}
