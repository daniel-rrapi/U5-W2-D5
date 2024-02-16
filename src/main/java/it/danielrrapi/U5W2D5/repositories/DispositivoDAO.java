package it.danielrrapi.U5W2D5.repositories;

import it.danielrrapi.U5W2D5.entities.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivoDAO extends JpaRepository<Dispositivo, Long> {
}
