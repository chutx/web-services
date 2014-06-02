package my.training.ws.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import my.training.ws.rest.domain.Customer;

import org.junit.Test;

public class CustomerClient {

	private final String URL = "http://localhost:8080/rest-test/services/customer";
	
	@Test
	public void createFromJaxb(){
		Customer customerObj = createDefaultCustomer("Bill", "Burke");
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL+"/jaxb");
		Builder builder = target.request();
		Response response = builder.post(Entity.xml(customerObj));
		if (response.getStatus() != 201)
			throw new RuntimeException("Failed to create");
		String location = response.getLocation().toString();
		System.out.println("Location: " + location);
		response.close();
	}
	
	@Test
	public void createCustomer() {
		Client client = ClientBuilder.newClient();
		try {
			System.out.println("*** Create a new Customer ***");
			Customer customerObj = createDefaultCustomer("Bill", "Burke");
			String xml = createXmlCustomerAsString(customerObj);
			
			Response response = client.target(URL).request().post(Entity.xml(xml));
			if (response.getStatus() != 201)
				throw new RuntimeException("Failed to create");
			String location = response.getLocation().toString();
			System.out.println("Location: " + location);
			response.close();
			
			System.out.println("*** GET Created Customer **");
			String customer = client.target(location).request().get(String.class);
			System.out.println(customer);
			
			
			String updateCustomer = "<customer>"
					+ "<first-name>William</first-name>"
					+ "<last-name>Burke</last-name>"
					+ "<street>256 Clarendon Street</street>"
					+ "<city>Boston</city>" + "<state>MA</state>"
					+ "<zip>02115</zip>" + "<country>USA</country>"
					+ "</customer>";
			response = client.target(location).request()
					.put(Entity.xml(updateCustomer));
			if (response.getStatus() != 204)
				throw new RuntimeException("Failed to update");
			response.close();
			System.out.println("**** After Update ***");
			customer = client.target(location).request().get(String.class);
			System.out.println(customer);
		} catch(Exception ex){
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}finally {
			client.close();
		}

	}
	
	private String createXmlCustomerAsString(Customer customer){
		String xml = "<customer>" + "<first-name>" + customer.getFirstName() + "</first-name>"
				+ "<last-name>" + customer.getLastName() + "</last-name>"
				+ "<street>" + customer.getStreet() + "</street>"
				+ "<city>" + customer.getCity() + "</city>" + "<state>" + customer.getState() + "</state>"
				+ "<zip>" + customer.getZip() + "</zip>" + "<country>" + customer.getCountry() + "</country>"
				+ "</customer>";
		return xml;
	}
	
	private Customer createDefaultCustomer(String firstName, String lastName){
		Customer c = new Customer();
		
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setStreet("256 Clarendon Street");
		c.setCity("Boston");
		c.setState("MA");
		c.setZip("02115");
		c.setCountry("USA");
		
		return c;
	}
}
