package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDAO {
	
	public ArrayList<Ville> findAllVilles(String codePostal);
	
	public void setVille(Ville ville);

}
