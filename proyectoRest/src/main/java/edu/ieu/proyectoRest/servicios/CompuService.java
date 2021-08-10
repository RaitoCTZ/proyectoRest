package edu.ieu.proyectoRest.servicios;

import java.util.List;

import edu.ieu.proyectoRest.entities.Compu;


public interface CompuService {
	Compu findById(long id);
	Compu findByModelo(String modelo);
    List<Compu> findAll(); 
    boolean isCompuExist(Compu comp);
    
    //nuevo 
    void saveCompu(Compu comp);
    
     //avtualiza
    void updateCompu(Compu comp);
    
    //borra
    void deleteCompuById(long id);
}
