package my.training.ws.service.api;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style=Style.RPC)
public interface TimeService {

	@WebMethod
	public String getTimeAsString();
	
	@WebMethod
	public Long getTimeAsElapsed();
}
