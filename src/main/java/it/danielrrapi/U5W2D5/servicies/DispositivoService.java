package it.danielrrapi.U5W2D5.servicies;


import it.danielrrapi.U5W2D5.entities.Dispositivo;
import it.danielrrapi.U5W2D5.exceptions.NotFoundException;
import it.danielrrapi.U5W2D5.repositories.DipendenteDAO;
import it.danielrrapi.U5W2D5.repositories.DispositivoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoDAO dispositivoDAO;

    public Page<Dispositivo> getDispositivi(int pageNumber, int size, String orderBy) {
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return dispositivoDAO.findAll(pageable);
    }

//    public Dispositivo save() {
//
//    }

    public Dispositivo findById(long id) {return dispositivoDAO.findById(id).orElseThrow(()-> new NotFoundException(id));}

//    public Dispositivo findByIdAndUpdate(long id, ) {
//        Dispositivo found = this.findById(id);
//
//    }

    public void findByIdAndDelete(long id) {
        Dispositivo found = this.findById(id);
        dispositivoDAO.delete(found);
    }

}
