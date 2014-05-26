package my.training.ws.service.publisher;

import javax.xml.ws.Endpoint;

import my.training.ws.service.api.ServicePublisher;
import my.training.ws.service.impl.TimeServiceImpl;

public class TimePublisher implements ServicePublisher {
	
	private static String timeServideAddress = "http://127.0.0.1:9876/ts";
	
	public void publishService(){
		Endpoint.publish(timeServideAddress, new TimeServiceImpl());
	}
}
