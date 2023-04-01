package it.prova.gestionecompagniapatterndao.test;

import java.sql.Connection;

import it.prova.gestionecompagniapatterndao.dao.*;
import it.prova.gestionecompagniapatterndao.connection.MyConnection;
import it.prova.gestionecompagniapatterndao.gestionedao.CompagniaDAO;
import it.prova.gestionecompagniapatterndao.gestionedao.CompagniaDAOImpl;
import it.prova.gestionecompagniapatterndao.gestionedao.ImpiegatoDAO;

public class TestGestioneCompagniaPatternDao {

	public static void main(String[] args) {

		CompagniaDAO compagniaDAOInstance = null;
		
		
		// ##############################################################################################################
		// Grande novità: la Connection viene allestista dal chiamante!!! Non è più a
		// carico dei singoli metodi DAO!!!
		// ##############################################################################################################

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			// ecco chi 'inietta' la connection: il chiamante
			compagniaDAOInstance = new CompagniaDAOImpl(connection);
			

		//	System.out.println(compagniaDAOInstance.list());
					
			// -------------

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
		public static void TestGet(CompagniaDAO compagnia) {
			
		}
}
