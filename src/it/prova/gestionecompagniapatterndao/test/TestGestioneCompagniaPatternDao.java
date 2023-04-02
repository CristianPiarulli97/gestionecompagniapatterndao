package it.prova.gestionecompagniapatterndao.test;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import it.prova.gestionecompagniapatterndao.dao.*;
import it.prova.gestionecompagniapatterndao.connection.MyConnection;
import it.prova.gestionecompagniapatterndao.gestionedao.CompagniaDAO;
import it.prova.gestionecompagniapatterndao.gestionedao.CompagniaDAOImpl;
import it.prova.gestionecompagniapatterndao.gestionedao.ImpiegatoDAO;
import it.prova.gestionecompagniapatterndao.gestionedao.ImpiegatoDAOImpl;
import it.prova.gestionecompagniapatterndao.model.Compagnia;
import it.prova.gestionecompagniapatterndao.model.Impiegato;

public class TestGestioneCompagniaPatternDao {

	public static void main(String[] args) {

		CompagniaDAO compagniaDAOInstance = null;
		ImpiegatoDAO impiegatoDAOInstance = null;

		// ##############################################################################################################
		// Grande novità: la Connection viene allestista dal chiamante!!! Non è più a
		// carico dei singoli metodi DAO!!!
		// ##############################################################################################################

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			// ecco chi 'inietta' la connection: il chiamante
			compagniaDAOInstance = new CompagniaDAOImpl(connection);
			impiegatoDAOInstance = new ImpiegatoDAOImpl(connection);

			// System.out.println(compagniaDAOInstance.list());

			// testInsertCompagnia(compagniaDAOInstance);
			// System.out.println("in tabella sono presenti "+
			// compagniaDAOInstance.list().size()+ " elementi.");

			// testDeleteCompagnia(compagniaDAOInstance);
			// System.out.println("in tabella sono presenti "+
			// compagniaDAOInstance.list().size()+ " elementi.");

			// testInsertImpiegato(impiegatoDAOInstance);
			// System.out.println("in tabella sono presenti "+
			// impiegatoDAOInstance.list().size()+ " elementi.");

			// testFindAllByDataAssunzioneMaggioreDi(compagniaDAOInstance,
			// impiegatoDAOInstance);
			// System.out.println("in tabella sono presenti "+
			// compagniaDAOInstance.list().size()+ " elementi.");
			// -------------

		//	testFindAllByRagioneSocialeContiene(compagniaDAOInstance);
		//	System.out.println("in tabella sono presenti "+ compagniaDAOInstance.list().size()+" elementi.");
			
			testFindAllByCodFisImpiegatoContiene(compagniaDAOInstance, impiegatoDAOInstance);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testDeleteCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {

		System.out.println(".......testDeleteCompagnia inizio.............");
		int quantiElementiInseriti = compagniaDAOInstance.insert(new Compagnia("Ferrari", 90, LocalDate.now()));
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED, user da rimuovere non inserito");

		List<Compagnia> elencoVociPresenti = compagniaDAOInstance.list();
		int numeroElementiPresentiPrimaDellaRimozione = elencoVociPresenti.size();
		if (numeroElementiPresentiPrimaDellaRimozione < 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED, non ci sono voci sul DB");

		Compagnia ultimoDellaLista = elencoVociPresenti.get(numeroElementiPresentiPrimaDellaRimozione - 1);
		compagniaDAOInstance.delete(ultimoDellaLista);

		int numeroElementiPresentiDopoDellaRimozione = compagniaDAOInstance.list().size();
		if (numeroElementiPresentiDopoDellaRimozione != numeroElementiPresentiPrimaDellaRimozione - 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED, la rimozione non è avvenuta");

		System.out.println(".......testDeleteCompagnia fine: PASSED.............");
	}

	private static void testInsertCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".......testInsertCompagnia inizio.............");
		int quantiElementiInseriti = compagniaDAOInstance.insert(new Compagnia("PosteItaliane", 50, LocalDate.now()));
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("testInsertCompagnia : FAILED");

		System.out.println(".......testInsertCompagnia fine: PASSED.............");
	}

	public static void testInsertImpiegato(ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		System.out.println(".......testInsertImpiegato inizio.............");
		int quantiElementiInseriti = impiegatoDAOInstance.insert(
				new Impiegato("Giulia", "Cresca", "GLICRC23H501R", LocalDate.of(2002, 11, 20), LocalDate.now()));
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("TestInsertImpiegato : FAILED");

		System.out.println(".......testInsertImpiegato fine: PASSED.............");
	}

	private static void testUpdateCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".........testUpdateCompagnia inizio...........");
		List<Compagnia> elencoCompagnie = compagniaDAOInstance.list();
		if (elencoCompagnie.size() < 1) {
			throw new RuntimeException("errore: la lista di compagnie è vuota!!");
		}
	}

	private static void testFindAllByDataAssunzioneMaggioreDi(CompagniaDAO compagniaDAOInstance,
			ImpiegatoDAO impiegatoDAOInstance) throws Exception {

		System.out.println(".......testFindAllByDataAssunzioneMaggioreDi inizio......");
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException("testFindAllByDataAssunzioneMaggioreDi : FAILED, non ci sono compagnia sul DB");
		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();
		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException("testFindAllByDataAssunzioneMaggioreDi : FAILED, non ci sono impiegati sul DB");

		LocalDate dataDaRicercare = LocalDate.parse("2022-01-01");
		List<Compagnia> listaCompagniaLikeExample = compagniaDAOInstance
				.findAllByDataAssunzioneMaggioreDi(dataDaRicercare);
		if (listaCompagniaLikeExample.size() < 1) {
			throw new RuntimeException("testFindAllByDataAssunzioneMaggioreDi : FAILED, non ci sono voci sul DB");
		}
		System.out.println(".......testFindAllByDataAssunzioneMaggioreDi fine: PASSED.............");
	}

	private static void testFindAllByRagioneSocialeContiene(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println("......testFindAllByRagioneSocialeContiene inizio......");
		List<Compagnia> compagnieEsistenti = compagniaDAOInstance.list();
		String parteNomeRagioneSociale = "Fe";
		List<Compagnia> result = compagniaDAOInstance.findAllByRagioneSocialeContiene(parteNomeRagioneSociale);
		System.out.println(result);
		System.out.println("......testFindAllByRagioneSocialeContiene fine......");

	}
	
	private static void testFindAllByCodFisImpiegatoContiene (CompagniaDAO compagniaDAOInstance, ImpiegatoDAO impiegatoDAOInstance) throws Exception{
		System.out.println(".......testFindAllByCodFisImpiegatoContiene inizio......");
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException("testFindAllByCodFisImpiegatoContiene : FAILED, non ci sono compagnia sul DB");
		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();
		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException("testFindAllByCodFisImpiegatoContiene : FAILED, non ci sono impiegati sul DB");

		String codFisDaCercare = "33H";
		List<Compagnia> listaCompagniaLikeExample = compagniaDAOInstance.findAllByCodFisImpiegatoContiene(codFisDaCercare);
		if (listaCompagniaLikeExample.size() < 1) {
			throw new RuntimeException("testFindAllByCodFisImpiegatoContiene : FAILED, non ci sono voci sul DB");
		}
		System.out.println("Gli elementi della lista sono: " + listaCompagniaLikeExample.size());
		System.out.println(listaCompagniaLikeExample);
		System.out.println(".......testFindAllByCodFisImpiegatoContiene fine: PASSED.............");
	}

}
