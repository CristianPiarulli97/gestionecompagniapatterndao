package it.prova.gestionecompagniapatterndao.gestionedao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.prova.gestionecompagniapatterndao.dao.AbstractMySQLDAO;
import it.prova.gestionecompagniapatterndao.model.Compagnia;
import it.prova.gestionecompagniapatterndao.model.Impiegato;

public class ImpiegatoDAOImpl extends AbstractMySQLDAO implements ImpiegatoDAO {
	
	//OK
	public ImpiegatoDAOImpl(Connection connection) {
		super(connection);
	
	}
	//OK
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
	//OK
	public Impiegato get(Long idInput) throws Exception {
		
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Impiegato result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from impiegato where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Impiegato();
					result.setNome(rs.getString("NOME"));
					result.setCognome(rs.getString("COGNOME"));
					result.setCodiceFiscale(rs.getString("codicefiscale"));
					result.setDataDiNascita(rs.getDate("DATECREATED") != null ? rs.getDate("DATECREATED").toLocalDate() : null);
					result.setDataAssunzione(
							rs.getDate("DATECREATED") != null ? rs.getDate("DATECREATED").toLocalDate() : null);
					result.setId(rs.getLong("ID"));
				} else {
					result = null;
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	//OK
	public int update(Impiegato input) throws Exception {
		
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE compagnia SET nome=?, cognome=?, codice fiscale=? ,datadinascita=? , dataassunzione=? , where id=?;")) {
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setString(3, input.getCodiceFiscale());
			ps.setDate(4, java.sql.Date.valueOf(input.getCodiceFiscale()));
			ps.setDate(5, java.sql.Date.valueOf(input.getDataAssunzione()));
			ps.setLong(6, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	//OK
	public int insert(Impiegato input) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
				if (isNotActive())
					throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

				if (input == null)
					throw new Exception("Valore di input non ammesso.");

				int result = 0;
				try (PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO impiegato (nome, cognome, codicefiscale , datadinascita, dataassunzione) VALUES (?, ?, ?, ?, ?);")) {
					ps.setString(1, input.getNome());
					ps.setString(2, input.getCognome());
					ps.setString(3, input.getCodiceFiscale());
					ps.setDate(4, java.sql.Date.valueOf(input.getDataDiNascita()));
					ps.setDate(5, java.sql.Date.valueOf(input.getDataAssunzione()));
					
					result = ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
		return result;
	}
	//OK
	public int delete(Impiegato input) throws Exception {
		
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM impiegato WHERE ID=?")) {
			ps.setLong(1, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
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
