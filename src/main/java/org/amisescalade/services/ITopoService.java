package org.amisescalade.services;

import java.util.List;

import org.amisescalade.entity.Topo;

public interface ITopoService {
	
	/**
	 * method to register a topo
	 * 
	 * @param topo
	 * @return topo Object save
	 * @throws Exception
	 */
	Topo register(Topo topo) throws Exception;
	
	/**
	 * method to modify a topo
	 * 
	 * @param topo
	 * @return topo Object modify
	 * @throws Exception
	 */
	Topo edit(Topo topo) throws Exception;
	
	/**
	 * method to display one topo by his id
	 * 
	 * @param topo
	 * @return topo Object to display
	 * @throws Exception
	 */
	Topo displayOne(Long id) throws Exception;
	
	/**
	 * method to display all topos
	 * 
	 * @return the topo list 
	 */
	List<Topo> displayAll();
	
	
	/**
	 * method to display one topo by his title
	 * 
	 * @param topo
	 * @return the topo list with his title to display
	 * @throws Exception
	 */
	List<Topo> displayByTitle(String title) throws Exception;
	
	
	
	

}
