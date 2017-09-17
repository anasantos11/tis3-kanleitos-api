package Services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Model.Pessoa;
import Model.Pessoas;

@Path("/pessoas")
public class PessoaResource {
	
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Pessoas getAllPessoas() {
		Pessoas p = new Pessoas(this.getListaPessoas());
		return p;
	}
	
	public List<Pessoa> getListaPessoas(){
		List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
		Pessoa p1 = new Pessoa("Luiz Henrique", "Silva Jesus", "130.489.936-57", 19);
		Pessoa p2 = new Pessoa("Ana Paula", "Dos Santos", "130.489.936-57", 99);
		Pessoa p3 = new Pessoa("Bruna Santos", "Silva", "130.489.936-57", 99);
		Pessoa p4 = new Pessoa("Wellington", "ALves polt", "130.489.936-57", 99);
		
		listaPessoas.add(p1);
		listaPessoas.add(p2);
		listaPessoas.add(p3);
		listaPessoas.add(p4);
		
		return listaPessoas;
	}
	
//	@POST
//	@Path("/creaVte")
//	@Consumes(MediaType.APPLICATION_JSON + "charset=UFT-8")
//	public ResponseBuilder criarPessoa(Pessoa p) {
//		return null;
//	}
}
