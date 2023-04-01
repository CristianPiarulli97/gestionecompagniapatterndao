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

public class CompagniaDAOImpl extends AbstractMySQLDAO implements CompagniaDAO {

	// OK
	public CompagniaDAOImpl(Connection connection) {
		super(connection);
	}

	// OK
	public List list() throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("Select * from compagnia")) {

			while (rs.next()) {
				Compagnia compagniaItem = new Compagnia();
				compagniaItem.setRagionesociale(rs.getString("ragionesociale"));
				compagniaItem.setFatturatoAnnuo(rs.getDouble("fatturatoannuo"));
				compagniaItem.setDataFondazione(
						rs.getDate("datafondazione") != null ? rs.getDate("datafondazione").toLocalDate() : null);
				compagniaItem.setId(rs.getLong("id"));
				result.add(compagniaItem);
			}
		}
		return result;
	}

	// OK
	public Compagnia get(Long idInput) throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Compagnia result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from compagnia where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Compagnia();
					result.setRagionesociale(rs.getString("ragionesociale"));
					result.setFatturatoAnnuo(rs.getDouble("fatturatoannuo"));
					result.setDataFondazione(
							rs.getDate("datafondazione") != null ? rs.getDate("datafondazione").toLocalDate() : null);

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

	// OK
	public int update(Compagnia input) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE compagnia SET ragionesociale=?, fatturatoannuo=?, datafondazione=? where id=?;")) {
			ps.setString(1, input.getRagionesociale());
			ps.setDouble(2, input.getFatturatoAnnuo());

			ps.setDate(3, java.sql.Date.valueOf(input.getDataFondazione()));
			ps.setLong(4, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	// OK
	public int insert(Compagnia input) throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO impiegato (ragionesociale, fatturatoannuo, datafondazione) VALUES (?, ?, ?);")) {
			ps.setString(1, input.getRagionesociale());
			ps.setDouble(2, input.getFatturatoAnnuo());
			ps.setDate(3, java.sql.Date.valueOf(input.getDataFondazione()));

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	// OK
	public int delete(Compagnia input) throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM compagnia WHERE ID=?")) {
			ps.setLong(1, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	public List findByExample(Compagnia input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAllByDataAssunzioneMaggioreDi(Date dataInput) throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();

		try (Statement ps = connection.createStatement();
				ResultSet rs = ps.executeQuery(
						"select * from compagnia c inner join impiegato i on c.id=i.id_compagnia where i.dataassunzione> ? ; ")) {

			while (rs.next()) {
				Compagnia compagniaItem = new Compagnia();
				compagniaItem.setRagionesociale(rs.getString("ragionesociale"));
				compagniaItem.setFatturatoAnnuo(rs.getDouble("fatturatoannuo"));
				compagniaItem.setDataFondazione(
						rs.getDate("datafondazione") != null ? rs.getDate("datafondazione").toLocalDate() : null);
				compagniaItem.setId(rs.getLong("id"));
				result.add(compagniaItem);
			}
		}
		return result;
	}

	public List findAllByRagioneSocialeContiene(String ragioneSocialeInput) throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();

		try (Statement ps = connection.createStatement();
				ResultSet rs = ps.executeQuery(
						"select * from compagnia c inner join impiegato i on c.id=i.id_compagnia where c.ragionesociale like ? ; ")) {
//			ps.setString(1, "%"+ ragioneSocialeInput + "%");
			while (rs.next()) {
				Compagnia compagniaItem = new Compagnia();
				compagniaItem.setRagionesociale(rs.getString("ragionesociale"));
				compagniaItem.setFatturatoAnnuo(rs.getDouble("fatturatoannuo"));
				compagniaItem.setDataFondazione(
						rs.getDate("datafondazione") != null ? rs.getDate("datafondazione").toLocalDate() : null);
				compagniaItem.setId(rs.getLong("id"));
				result.add(compagniaItem);
			}
		}
		return result;

	}

	public List findAllByCodFisImpiegatoContiene(String parteCodiceFiscaleInput) throws Exception {
		
		if (isNotActive())
			throw new Exception("Connessione non attiva");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();

		try (Statement ps = connection.createStatement();
				ResultSet rs = ps.executeQuery(
						"select * from compagnia c inner join impiegato i on c.id=i.id_compagnia where i.codicefiscale like ? ; ")) {
//			ps.setString(1, "%"+ parteCodiceFiscaleInput + "%");
			while (rs.next()) {
				Compagnia compagniaItem = new Compagnia();
				compagniaItem.setRagionesociale(rs.getString("ragionesociale"));
				compagniaItem.setFatturatoAnnuo(rs.getDouble("fatturatoannuo"));
				compagniaItem.setDataFondazione(
						rs.getDate("datafondazione") != null ? rs.getDate("datafondazione").toLocalDate() : null);
				compagniaItem.setId(rs.getLong("id"));
				result.add(compagniaItem);
			}
		}
		return result;
	
	}

}
