package my.training.ws.service.impl;

import java.util.Date;

import javax.jws.WebService;

import my.training.ws.service.api.TimeService;

@WebService(endpointInterface="my.training.ws.service.api.TimeService")
public class TimeServiceImpl implements TimeService {

	@Override
	public String getTimeAsString() {
		String result = new Date().toString();
		System.out.println("Creating date: " + result);
		
		return result;
	}

	@Override
	public Long getTimeAsElapsed() {
		return new Date().getTime();
	}

}
