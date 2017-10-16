package br.com.kanleitos.models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class PedidoInternacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPedidoInternacao;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idPaciente", nullable = false)
	private Paciente paciente;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idAla", nullable = false)
	private Ala ala;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idDiagnostico")
	private Diagnostico diagnostico;

	@Column(name = "medicoResponsavel")
	private String medicoResponsavel;

	@Column(name = "residenteResponsavel")
	private String residenteResponsavel;

	@Column(name = "dataAdmissao", nullable = false)
	private String dataAdmissao;

	@Column(name = "AIH", nullable = false)
	private String AIH;

	@Column(name = "dataPedido", nullable = false)
	private String dataPedido;

	@Column(name = "statusPedido", nullable = false)
	private String statusPedido;

	public PedidoInternacao() throws JSONException {
		setAIH(null);
		setAla(new Ala());
		setDataAdmissao(null);
		setDiagnostico(new Diagnostico());
		setMedicoResponsavel(null);
		setPaciente(new Paciente());
		setResidenteResponsavel(null);
		setDataPedido(LocalDate.now().toString());
		setStatusPedido("Pendente");

	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void setAla(Ala ala) {
		this.ala = ala;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}
	private static class PedidoInternacaoKeys {
		private static final String PACIENTE = "paciente";
		private static final String ALA = "ala";
		private static final String DIAGNOSTICO = "diagnostico";
		private static final String MEDICO_RESPONSAVEL = "medicoResponsavel";
		private static final String RESIDENTE_RESPONSAVEL = "residenteResponsavel";
		private static final String DATA_ADMISSAO = "dataAdmissao";
		private static final String AIH = "AIH";
		private static final String DATA_PEDIDO = "dataPedido";
		private static final String STATUS_PEDIDO = "status";

	}

	public PedidoInternacao(JSONObject json) throws JSONException {

		if (json.has(PedidoInternacaoKeys.PACIENTE))
			setPaciente(json.getJSONObject(PedidoInternacaoKeys.PACIENTE));

		if (json.has(PedidoInternacaoKeys.ALA))
			setAla(json.getJSONObject(PedidoInternacaoKeys.ALA));

		if (json.has(PedidoInternacaoKeys.DIAGNOSTICO))
			setDiagnostico(json.getJSONObject(PedidoInternacaoKeys.DIAGNOSTICO));

		if (json.has(PedidoInternacaoKeys.PACIENTE))
			setPaciente(json.getJSONObject(PedidoInternacaoKeys.PACIENTE));

		if (json.has(PedidoInternacaoKeys.MEDICO_RESPONSAVEL))
			setMedicoResponsavel(json.getString(PedidoInternacaoKeys.MEDICO_RESPONSAVEL));

		if (json.has(PedidoInternacaoKeys.RESIDENTE_RESPONSAVEL))
			setResidenteResponsavel(json.getString(PedidoInternacaoKeys.RESIDENTE_RESPONSAVEL));

		if (json.has(PedidoInternacaoKeys.DATA_ADMISSAO))
			setDataAdmissao(json.getString(PedidoInternacaoKeys.DATA_ADMISSAO));

		if (json.has(PedidoInternacaoKeys.AIH))
			setAIH(json.getString(PedidoInternacaoKeys.AIH));

		if (json.has(PedidoInternacaoKeys.DATA_PEDIDO))
			setDataPedido(json.getString(PedidoInternacaoKeys.DATA_PEDIDO));

		if (json.has(PedidoInternacaoKeys.STATUS_PEDIDO))
			setStatusPedido(json.getString(PedidoInternacaoKeys.STATUS_PEDIDO));

	}

	public String getMedicoResponsavel() {
		return medicoResponsavel;
	}

	public void setMedicoResponsavel(String medicoResponsavel) {
		this.medicoResponsavel = medicoResponsavel;
	}

	public String getResidenteResponsavel() {
		return residenteResponsavel;
	}

	public void setResidenteResponsavel(String residenteResponsavel) {
		this.residenteResponsavel = residenteResponsavel;
	}

	public String getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(String dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public String getAIH() {
		return AIH;
	}

	public void setAIH(String aIH) {
		AIH = aIH;
	}

	public int getIdPedidoInternacao() {
		return idPedidoInternacao;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(JSONObject jsonObject) throws JSONException {
		this.paciente = new Paciente(jsonObject);
	}

	public Ala getAla() {
		return ala;
	}

	public void setAla(JSONObject jsonObject) throws JSONException {
		this.ala = new Ala(jsonObject);
	}

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(JSONObject jsonObject) throws JSONException {
		this.diagnostico = new Diagnostico(jsonObject);
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}

}
