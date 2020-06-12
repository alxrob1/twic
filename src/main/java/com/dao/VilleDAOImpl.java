package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import com.config.JDBCConfiguration;
import com.dto.Coordonnee;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {

	public ArrayList<Ville> findAllVilles(String codePostal) {
		ArrayList<Ville> listVille = new ArrayList<Ville>();
		
		Connection con = JDBCConfiguration.getConnection();
		
		ResultSet results = null;
		String requete = "SELECT * FROM ville_france";
		
		if(codePostal!=null) {
			requete += " WHERE Code_postal=" + codePostal;
		}

		try {
		   Statement stmt = con.createStatement();
		   results = stmt.executeQuery(requete);
		   
		   while (results.next()) {
			   	Ville ville = new Ville();
				ville.setCodeCommune(results.getString("Code_commune_INSEE"));
				ville.setNomCommune(results.getString("Nom_commune"));
				ville.setCodePostal(results.getString("Code_postal"));
				ville.setLibelleAcheminement(results.getString("Libelle_acheminement"));
				ville.setLigne(results.getString("Ligne_5"));
				Coordonnee coordonnee = new Coordonnee();
				coordonnee.setLatitude(results.getString("Latitude"));
				coordonnee.setLongitude(results.getString("Longitude"));
				ville.setCoordonnee(coordonnee);
				listVille.add(ville);
		   }

		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return listVille;
	}
	
	public void setVille(Ville ville) {
	
		Connection con = JDBCConfiguration.getConnection();
		
		String requete = "INSERT INTO ville_france (Code_commune_INSEE, Nom_commune, Code_postal, Libelle_acheminement, Ligne_5, Latitude, Longitude) VALUES (";
		requete += "'" + ville.getCodeCommune() + "', '";
		requete += ville.getNomCommune() + "', '";
		requete += ville.getCodePostal() + "', '";
		requete += ville.getLibelleAcheminement() + "', '";
		requete += ville.getLigne() + "', '";
		requete += ville.getCoordonnee().getLatitude() + "', '";
		requete += ville.getCoordonnee().getLongitude() + "')";
				
		try {
		   Statement stmt = con.createStatement();
		   stmt.executeUpdate(requete);

		} catch (SQLException e) {
			System.out.println(e);
		}
				
	}
}
