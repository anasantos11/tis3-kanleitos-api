package br.com.kanleitos.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class ExameLista {

	@Id
	@SequenceGenerator(name="EXAME_LISTA_ID",  initialValue=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="EXAME_LISTA_ID")
	private long idExameLista;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "registroInternacao",referencedColumnName = "idRegistroInternacao")
	private RegistroInternacao registroInternacao;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="exameLista")
	private List<TipoExame> tiposExames;
	
	public ExameLista() throws JSONException {
		setRegistroInternacao(new RegistroInternacao());
	}
	private static class ExameListaKeys {
		private static final String REGISTRO_INTERNACAO = "registroInternacao";
	}
	
	public ExameLista(JSONObject json) throws JSONException {
		
		if (json.has(ExameListaKeys.REGISTRO_INTERNACAO))
			setRegistroInternacao(json.getJSONObject((ExameListaKeys.REGISTRO_INTERNACAO)));
	}

	public long getIdExameLista() {
		return idExameLista;
	}

	public RegistroInternacao getRegistroInternacao() {
		return registroInternacao;
	}

	public void setRegistroInternacao(RegistroInternacao registroInternacao) {
		this.registroInternacao = registroInternacao;
	}
	
	public void setRegistroInternacao(JSONObject jsonObject) throws JSONException {
		this.registroInternacao = new RegistroInternacao(jsonObject);
	}

	
	
}
