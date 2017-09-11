package Services;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/REST/API")
public class RESTAplication extends Application{
	private Set<Object> singletons = new HashSet<Object>();

	public RESTAplication() {
		singletons.add(new PessoaResource());
	}
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
