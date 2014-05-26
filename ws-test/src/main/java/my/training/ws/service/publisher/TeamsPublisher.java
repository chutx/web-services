package my.training.ws.service.publisher;

import javax.xml.ws.Endpoint;

import my.training.ws.service.api.ServicePublisher;
import my.training.ws.service.impl.Teams;

public class TeamsPublisher implements ServicePublisher {

	private static String url = "http://localhost:8888/teams";
	
	public void publishService( ) {
		int port = 8888;
		System.out.println("Publishing Teams on port " + port);
		Endpoint.publish(url, new Teams());
	}
}
