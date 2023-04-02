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
				Impiegato impiegatoTemp = new Impiegato();

				impiegatoTemp.setNome(rs.getString("nome"));
				impiegatoTemp.setCognome(rs.getString("Cognome"));
				impiegatoTemp.setCodiceFiscale(rs.getString("codicefiscale"));
				impiegatoTemp.setDataNascita(
						rs.getDate("datanascita") != null ? rs.getDate("datanascita").toLocalDate() : null);
				impiegatoTemp.setDataAssunzione(
						rs.getDate("dataassunzione") != null ? rs.getDate("dataassunzione").toLocalDate() : null);
				impiegatoTemp.setId(rs.getLong("id"));
				result.add(impiegatoTemp);

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
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
					result.setDataNascita(rs.getDate("datanascita") != null ? rs.getDate("datanascita").toLocalDate() : null);
					result.setDataAssunzione(
							rs.getDate("dataassunzione") != null ? rs.getDate("dataassunzione").toLocalDate() : null);
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
				"UPDATE compagnia SET nome=?, cognome=?, codice fiscale=? ,datanascita=? , dataassunzione=? , where id=?;")) {
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setString(3, input.getCodiceFiscale());
			ps.setDate(4, java.sql.Date.valueOf(input.getDataNascita()));
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
						"INSERT INTO impiegato (nome, cognome, codicefiscale , datanascita, dataassunzione) VALUES (?, ?, ?, ?, ?);")) {
					ps.setString(1, input.getNome());
					ps.setString(2, input.getCognome());
					ps.setString(3, input.getCodiceFiscale());
					ps.setDate(4, java.sql.Date.valueOf(input.getDataNascita()));
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
		return null;
		
/*		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		
		List<Impiegato> result = new ArrayList<Impiegato>();

		if (compagniaInput == null)
			throw new RuntimeException("Impossibile caricare Negozio: id mancante!");

		Impiegato temp = null;
		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("select d from impiegato i inner join compagnia c on c.id=i.id_compagnia where id_compagnia=?; ")) {

			ps.setLong(1, compagniaInput.getId());
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					temp = new Impiegato();
					temp.setId(rs.getLong("id"));
					temp.setNome(rs.getString("nome"));
					temp.setCognome(rs.getString("cognome"));
					temp.getCodiceFiscale(rs.getString("codicefiscale"));
					temp.get

					result.add(temp);

				}

				negozioInput.setArticoli(result);

			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
*/
	}


	public int countByDataFondazioneCompagniaGreaterThan(Date dataInput) throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (dataInput == null || dataInput==null)
			throw new Exception("Valore di input non ammesso.");

		int contatore = 0;

		try (PreparedStatement ps = connection.prepareStatement("select count(datafondazione) from compagnia where datafondazione > ? ;")) {
			// quando si fa il setDate serve un tipo java.sql.Date
			ps.setDate(1, dataInput);
			
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					Compagnia compagniaTemp = new Compagnia();
					compagniaTemp.setRagionesociale(rs.getString("ragionesociale"));
					compagniaTemp.setFatturatoAnnuo(rs.getDouble("fatturatoannuo"));
					compagniaTemp.setDataFondazione(
							rs.getDate("datafondazione") != null ? rs.getDate("datafondazione").toLocalDate() : null);
					compagniaTemp.setId(rs.getLong("ID"));
					contatore++;
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return contatore;
	}

	public List findAllByCompagniaConFatturatoMaggioreDi(int fatturatoInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAllErroriAssunzione() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
