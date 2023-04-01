package it.prova.gestionecompagniapatterndao.gestionedao;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import it.prova.gestionecompagniapatterndao.dao.AbstractMySQLDAO;
import it.prova.gestionecompagniapatterndao.model.Compagnia;

public class CompagniaDAOImpl extends AbstractMySQLDAO implements CompagniaDAO {

	public CompagniaDAOImpl(Connection connection) {
		super(connection);
	}

	public List list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Compagnia get(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int update(Compagnia input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Compagnia input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(Compagnia input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List findByExample(Compagnia input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAllByDataAssunzioneMaggioreDi(Date dataInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAllByRagioneSocialeContiene(String ragioneSocialeInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAllByCodFisImpiegatoContiene(String parteCodiceFiscaleInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
