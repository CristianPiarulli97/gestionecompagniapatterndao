package it.prova.gestionecompagniapatterndao.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Compagnia {
	
	private Long id;
	private String ragionesociale;
	private double fatturatoAnnuo;
	private Date dataFondazione;
	private List<Impiegato> impiegati = new ArrayList<>();
	
	public Compagnia() {
		
	}
	
	public Compagnia(Long id, String ragionesociale, double fatturatoAnnuo, Date dataFondazione) {
		super();
		this.id = id;
		this.ragionesociale = ragionesociale;
		this.fatturatoAnnuo = fatturatoAnnuo;
		this.dataFondazione = dataFondazione;
	}

	public Compagnia(Long id, String ragionesociale, double fatturatoAnnuo, Date dataFondazione,
			List<Impiegato> impiegati) {
		super();
		this.id = id;
		this.ragionesociale = ragionesociale;
		this.fatturatoAnnuo = fatturatoAnnuo;
		this.dataFondazione = dataFondazione;
		this.impiegati = impiegati;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRagionesociale() {
		return ragionesociale;
	}
	public void setRagionesociale(String ragionesociale) {
		this.ragionesociale = ragionesociale;
	}
	public double getFatturatoAnnuo() {
		return fatturatoAnnuo;
	}
	public void setFatturatoAnnuo(double fatturatoAnnuo) {
		this.fatturatoAnnuo = fatturatoAnnuo;
	}
	public Date getDataFondazione() {
		return dataFondazione;
	}
	public void setDataFondazione(Date dataFondazione) {
		this.dataFondazione = dataFondazione;
	}
	public List<Impiegato> getImpiegati() {
		return impiegati;
	}
	public void setImpiegati(List<Impiegato> impiegati) {
		this.impiegati = impiegati;
	}

	@Override
	public String toString() {
		return "Compagnia [id=" + id + ", ragionesociale=" + ragionesociale + ", fatturatoAnnuo=" + fatturatoAnnuo
				+ ", dataFondazione=" + dataFondazione + ", impiegati=" + impiegati + "]";
	}
	
	
	
	
}
