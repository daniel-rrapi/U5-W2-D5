package it.danielrrapi.U5W2D5.servicies;

import it.danielrrapi.U5W2D5.entities.Dipendente;
import it.danielrrapi.U5W2D5.exceptions.BadRequestException;
import it.danielrrapi.U5W2D5.payloads.NewDipendenteDTO;
import it.danielrrapi.U5W2D5.repositories.DipendenteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteDAO dipendenteDAO;

    public Page<Dipendente> getDipendenti(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return dipendenteDAO.findAll(pageable);
    }

    public Dipendente save(NewDipendenteDTO payload) {
        dipendenteDAO.findByEmail(payload.email()).ifPresent(dipendente -> {
            throw new BadRequestException("L'email " + payload.email() + " è gia in uso!");
        });
        Dipendente newDipendente = new Dipendente(payload.username(), payload.name(), payload.cognome(), payload.email());
        return dipendenteDAO.save(newDipendente);
    }
}
