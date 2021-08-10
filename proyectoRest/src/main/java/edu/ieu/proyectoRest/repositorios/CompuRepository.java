package edu.ieu.proyectoRest.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.ieu.proyectoRest.entities.Compu;


public interface CompuRepository extends CrudRepository<Compu, Integer> {
	
	@Query("SELECT computadoras FROM Compu computadoras WHERE computadoras.modelo = ?1  ")
	public  Compu findByModelo(String modelo);
}
