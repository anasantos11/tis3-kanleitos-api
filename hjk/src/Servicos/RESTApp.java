package Servicos;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/API")
public class RESTApp extends Application{
	private Set<Object> singletons = new HashSet<Object>();

	public RESTApp() {
		singletons.add(new PacienteResource());
	}
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}

