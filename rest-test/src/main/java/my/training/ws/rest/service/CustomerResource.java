package my.training.ws.rest.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import my.training.ws.rest.domain.Customer;
import my.training.ws.rest.util.CustomerUtil;

@Path(value = "/customer")
public class CustomerResource {

	private Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();
	private AtomicInteger idCounter = new AtomicInteger();

	@POST
	@Consumes(value = { "application/xml", "application/json" })
	public Response createCustomer(InputStream is) {
		System.out.println("Creating customer ...");
		Customer customer = CustomerUtil.readCustomer(is);
		customer.setId(idCounter.incrementAndGet());
		customerDB.put(customer.getId(), customer);
		System.out.println("Created customer " + customer.getId());
		return Response.created(URI.create("/customer/" + customer.getId()))
				.build();
	}
	
	@POST
	@Consumes(value = {"application/xml"})
	@Path(value="/jaxb")
	public Response createCustomerFromJaxb(Customer customer){
		customer.setId(idCounter.incrementAndGet());
		customerDB.put(customer.getId(), customer);
		System.out.println("Created customer " + customer.getId());
		return Response.created(URI.create("/customer/" + customer.getId()))
				.build();
	}

	@GET
	@Path(value = "{id}")
	@Produces(value = { "application/xml", "application/json" })
	public StreamingOutput getCustomer(@PathParam(value = "id") int id) {
		final Customer customer = customerDB.get(id);
		if (customer == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return new StreamingOutput() {
			public void write(OutputStream outputStream) throws IOException,
					WebApplicationException {
				CustomerUtil.outputCustomer(outputStream, customer);
			}
		};
	}

	@PUT
	@Path(value = "{id}")
	@Produces(value = { "application/xml", "application/json" })
	public void updateCustomer(@PathParam("id") int id, InputStream is) {
		Customer update = CustomerUtil.readCustomer(is);
		Customer current = customerDB.get(id);
		if (current == null)
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		current.setFirstName(update.getFirstName());
		current.setLastName(update.getLastName());
		current.setStreet(update.getStreet());
		current.setState(update.getState());
		current.setZip(update.getZip());
        current.setCountry(update.getCountry());

	}

}
