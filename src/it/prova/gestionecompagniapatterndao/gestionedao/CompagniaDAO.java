package it.prova.gestionecompagniapatterndao.gestionedao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import it.prova.gestionecompagniapatterndao.dao.IBaseDAO;
import it.prova.gestionecompagniapatterndao.model.Compagnia;

public interface CompagniaDAO extends IBaseDAO<Compagnia>{
	
	
	public List<Compagnia> findAllByRagioneSocialeContiene(String ragioneSocialeInput) throws Exception;
	public List<Compagnia> findAllByCodFisImpiegatoContiene(String parteCodiceFiscaleInput) throws Exception;
	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(LocalDate dataDaRicercare) throws Exception;
	
	
}
