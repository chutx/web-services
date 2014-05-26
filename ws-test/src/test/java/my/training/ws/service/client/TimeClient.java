package my.training.ws.service.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import my.training.ws.service.api.TimeService;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class TimeClient {

	@Test
	public void testTimeWs() throws MalformedURLException{
		URL url = new URL("http://127.0.0.1:9876/ts?wsdl");
		
		QName qName = new QName("http://impl.service.ws.training.my/", "TimeServiceImplService");
	
		Service service = Service.create(url, qName);
		
		TimeService ts = service.getPort(TimeService.class);
		
		Assert.assertThat(ts.getTimeAsString(), CoreMatchers.not(""));
	}
}
