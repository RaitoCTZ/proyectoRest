package edu.ieu.proyectoRest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ieu.proyectoRest.entities.Compu;
import edu.ieu.proyectoRest.servicios.CompuService;

@RestController
@RequestMapping("/api/compus")
public class CompuApiRest {
	
	@Autowired
	private CompuService service; 
	
	@GetMapping
	public ResponseEntity<List<Compu>> listAll(){
		List<Compu> listaCompus = service.findAll();
		if(listaCompus.isEmpty()) {
			return new ResponseEntity<List<Compu>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Compu>>(listaCompus, HttpStatus.OK);
	}
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Compu> getCompu(@PathVariable("id") long id) {
        System.out.println("Fetching Computador with id " + id);
        Compu comp = service.findById(id);
        if (comp == null) {
            System.out.println("computador with id " + id + " not found");
            return new ResponseEntity<Compu>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Compu>(comp, HttpStatus.OK);
    }

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createCompu(@RequestBody Compu comp){
		System.out.println("Creating Computador " + comp.getModelo());
		
		if (service.isCompuExist(comp)) {
            System.out.println("computador with name " + comp.getModelo() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
		service.saveCompu(comp);
		 return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<Compu> updateCel(@PathVariable("id") int id,
			@RequestBody Compu compu){
		 System.out.println("Updating Computador " + id);
		 Compu compudb = service.findById(id);
		 if(compudb == null) {
			 System.out.println("Computador with id " + id + " not found");
			 return new ResponseEntity<Compu>(HttpStatus.NOT_FOUND);
		 }
		 compudb.setModelo( compu.getModelo() );
			compudb.setPrecio( compu.getPrecio() );
			compudb.setMarca( compu.getMarca() );			
			compudb.setRam( compu.getRam() );
			compudb.setProcesador( compu.getProcesador() );
			compudb.setColor( compu.getColor() );
			compudb.setSsd( compu.getSsd() );
			compudb.setHdd( compu.getHdd() );
			
			service.updateCompu(compudb);
		 return new ResponseEntity<Compu>(compudb, HttpStatus.OK );
	}
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteCompu(@PathVariable("id") int id){
		System.out.println("Fetching & Deleting compu with id " + id);
		Compu compu = service.findById(id);
		if(compu == null) {
			 System.out.println("Unable to delete. compu with id " + id + " not found");
			 return new ResponseEntity<Void>(HttpStatus.NOT_FOUND); // 404
		}
		service.deleteCompuById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // 204 http
	}
}
