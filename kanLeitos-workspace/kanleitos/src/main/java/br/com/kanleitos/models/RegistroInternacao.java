package br.com.kanleitos.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class RegistroInternacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRegistroInternacao;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idPedidoInternacao", nullable = false)
	private PedidoInternacao pedidoInternacao;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "registroInternacao")
	private List<ExameLista> exames;
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "registroInternacao")
	private List<TipoPendencia> pendencias;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idEnfermaria", nullable = false)
	private Enfermaria enfermaria;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idLeito", nullable = false)
	private Leito leito;

	@Column(name = "dataInternacao", nullable = false)
	private String dataInternacao;

	@Column(name = "previsaoAlta", nullable = false)
	private String previsaoAlta;

	@Column(name = "dataAlta", nullable = false)
	private String dataAlta;

	@Column(name = "diasInternacao", nullable = false)
	private int diasInternacao;

	@Column(name = "classificacao", nullable = false)
	private String classificacao;

	public RegistroInternacao() throws JSONException {
		setPedidoInternacao(new PedidoInternacao());
		setEnfermaria(new Enfermaria());
		setLeito(new Leito());
		setDataInternacao(null);
		setPrevisaoAlta(null);
		setDataAlta(null);
		setDiasInternacao(-1);
		setClassificacao(null);
	}

	private static class RegistroInternacaoKeys {
		private static final String PEDIDO_INTERNACAO = "pedidoInternacao";
		private static final String TIPO_PENDENCIA = "tipoPendencia";
		private static final String ENFERMARIA = "enfermaria";
		private static final String LEITO = "leito";
		private static final String DATA_INTERNACAO = "dataInternacao";
		private static final String PREVISAO_ALTA = "previsaoAlta";
		private static final String DATA_ALTA = "dataAlta";
		private static final String DIAS_INTERNACAO = "diasInternacao";
		private static final String CLASSIFICACAO = "classificacao";
	}

	public RegistroInternacao(JSONObject json) throws JSONException {

		if (json.has(RegistroInternacaoKeys.PEDIDO_INTERNACAO))
			setPedidoInternacao(json.getJSONObject(RegistroInternacaoKeys.PEDIDO_INTERNACAO));

		if (json.has(RegistroInternacaoKeys.TIPO_PENDENCIA))
			setTipoPendencia(json.getJSONObject(RegistroInternacaoKeys.TIPO_PENDENCIA));

		if (json.has(RegistroInternacaoKeys.ENFERMARIA))
			setEnfermaria(json.getJSONObject(RegistroInternacaoKeys.ENFERMARIA));

		if (json.has(RegistroInternacaoKeys.LEITO))
			setLeito(json.getJSONObject(RegistroInternacaoKeys.LEITO));

		if (json.has(RegistroInternacaoKeys.DATA_INTERNACAO))
			setDataInternacao(json.getString(RegistroInternacaoKeys.DATA_INTERNACAO));

		if (json.has(RegistroInternacaoKeys.PREVISAO_ALTA))
			setPrevisaoAlta(json.getString(RegistroInternacaoKeys.PREVISAO_ALTA));

		if (json.has(RegistroInternacaoKeys.DATA_ALTA))
			setDataAlta(json.getString(RegistroInternacaoKeys.DATA_ALTA));

		if (json.has(RegistroInternacaoKeys.DIAS_INTERNACAO))
			setDiasInternacao(json.getInt(RegistroInternacaoKeys.DIAS_INTERNACAO));

		if (json.has(RegistroInternacaoKeys.CLASSIFICACAO))
			setClassificacao(json.getString(RegistroInternacaoKeys.CLASSIFICACAO));

	}

	public PedidoInternacao getPedidoInternacao() {
		return pedidoInternacao;
	}

	public void setPedidoInternacao(PedidoInternacao pedidoInternacao) {
		this.pedidoInternacao = pedidoInternacao;
	}

	public void setPedidoInternacao(JSONObject jsonObject) throws JSONException {
		this.pedidoInternacao = new PedidoInternacao(jsonObject);
	}

	public List<TipoPendencia> getTipoPendencia() {
		return pendencias;
	}

	public void setTipoPendencia(TipoPendencia pendencia) {
		pendencias.add(pendencia);
	}

	public void setTipoPendencia(JSONObject jsonObject) throws JSONException {
		pendencias.add(new TipoPendencia(jsonObject));
	}

	public Enfermaria getEnfermaria() {
		return enfermaria;
	}

	public void setEnfermaria(Enfermaria enfermaria) {
		this.enfermaria = enfermaria;
	}

	public void setEnfermaria(JSONObject jsonObject) throws JSONException {
		this.enfermaria = new Enfermaria(jsonObject);
	}

	public Leito getLeito() {
		return leito;
	}

	public void setLeito(Leito leito) {
		this.leito = leito;
	}

	public void setLeito(JSONObject jsonObject) throws JSONException {
		this.leito = new Leito(jsonObject);
	}

	public String getDataInternacao() {
		return dataInternacao;
	}

	public void setDataInternacao(String dataInternacao) {
		this.dataInternacao = dataInternacao;
	}

	public String getPrevisaoAlta() {
		return previsaoAlta;
	}

	public void setPrevisaoAlta(String previsaoAlta) {
		this.previsaoAlta = previsaoAlta;
	}

	public String getDataAlta() {
		return dataAlta;
	}

	public void setDataAlta(String dataAlta) {
		this.dataAlta = dataAlta;
	}

	public int getDiasInternacao() {
		return diasInternacao;
	}

	public void setDiasInternacao(int diasInternacao) {
		this.diasInternacao = diasInternacao;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public int getIdRegistroInternacao() {
		return idRegistroInternacao;
	}

}
