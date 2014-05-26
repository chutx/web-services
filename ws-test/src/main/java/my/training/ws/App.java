package my.training.ws;

import java.util.ArrayList;
import java.util.List;

import my.training.ws.service.api.ServicePublisher;
import my.training.ws.service.publisher.TeamsPublisher;
import my.training.ws.service.publisher.TimePublisher;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	List<ServicePublisher> publishers = new ArrayList<>();
    	publishers.add(new TimePublisher());
    	publishers.add(new TeamsPublisher());
        
    	for(ServicePublisher publisher : publishers){
    		publisher.publishService();
    	}
    }
    
}
