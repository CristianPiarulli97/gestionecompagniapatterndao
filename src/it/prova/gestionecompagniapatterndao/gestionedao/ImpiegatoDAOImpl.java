package it.prova.gestionecompagniapatterndao.gestionedao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.prova.gestionecompagniapatterndao.dao.AbstractMySQLDAO;
import it.prova.gestionecompagniapatterndao.model.Compagnia;
import it.prova.gestionecompagniapatterndao.model.Impiegato;

public class ImpiegatoDAOImpl extends AbstractMySQLDAO implements ImpiegatoDAO {

	public List<Impiegato> list() throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva");

		ArrayList<Impiegato> result = new ArrayList<Impiegato>();

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("Select * from impiegato")) {

			while (rs.next()) {
				Impiegato impiegatotemp = new Impiegato();
				impiegatotemp.setNome(rs.getString("nome"));
				impiegatotemp.setCognome(rs.getString("Cognome"));
				impiegatotemp.setCodiceFiscale(rs.getString("codicefiscale"));
				impiegatotemp.setDataDiNascita(
						rs.getDate("datadinascita") != null ? rs.getDate("datadinascita").toLocalDate() : null);
				impiegatotemp.setDataAssunzione(
						rs.getDate("datadiassunzione") != null ? rs.getDate("datadiassunzione").toLocalDate() : null);
				impiegatotemp.setId(rs.getLong("id"));
				result.add(impiegatotemp);			}

		}
		return null;

	}

	public Impiegato get(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int update(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List findByExample(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAllByCompagnia(Compagnia compagniaInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int countByDataFondazioneCompagniaGreaterThan(Date dataInput) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List findAllByCompagniaConFatturatoMaggioreDi(int fattura) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAllErroriAssunzione() {
		// TODO Auto-generated method stub
		return null;
	}

}