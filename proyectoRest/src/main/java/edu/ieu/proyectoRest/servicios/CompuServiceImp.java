package edu.ieu.proyectoRest.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ieu.proyectoRest.entities.Compu;
import edu.ieu.proyectoRest.repositorios.CompuRepository;

@Service
public class CompuServiceImp implements CompuService {

	@Autowired
	private CompuRepository compuDao;
	
	@Override
	public Compu findById(long id) {
		return compuDao.findById((int) id)
				.orElse(null);
	
	}

	@Override
	public Compu findByModelo(String modelo) {
		return compuDao.findByModelo(modelo);
	}

	@Override
	public List<Compu> findAll() {
		List<Compu> lista = new ArrayList<>();
		compuDao.findAll()
			.forEach( lista::add );
		return lista;
	}

	@Override
	public boolean isCompuExist(Compu comp) {
		return compuDao.existsById(comp.getId() );
	}

	@Override
	public void saveCompu(Compu comp) {
		compuDao.save(comp);
		
	}

	@Override
	public void updateCompu(Compu comp) {
		Compu compudb = compuDao.findById(comp.getId() ).orElse(null);
		if(compudb != null) {
			compudb.setModelo( comp.getModelo() );
			compudb.setPrecio( comp.getPrecio() );
			compudb.setMarca( comp.getMarca() );			
			compudb.setRam( comp.getRam() );
			compudb.setProcesador( comp.getProcesador() );
			compudb.setColor( comp.getColor() );
			compudb.setSsd( comp.getSsd() );
			compudb.setHdd( comp.getHdd() );
			
			compuDao.save(compudb);
		}
		
	}

	@Override
	public void deleteCompuById(long id) {
		compuDao.deleteById( (int) id );	
		
	}

}
