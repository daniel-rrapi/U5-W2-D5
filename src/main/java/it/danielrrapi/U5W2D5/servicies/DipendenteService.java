package it.danielrrapi.U5W2D5.servicies;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.danielrrapi.U5W2D5.entities.Dipendente;
import it.danielrrapi.U5W2D5.exceptions.BadRequestException;
import it.danielrrapi.U5W2D5.exceptions.NotFoundException;
import it.danielrrapi.U5W2D5.payloads.NewDipendenteDTO;
import it.danielrrapi.U5W2D5.repositories.DipendenteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteDAO dipendenteDAO;
    @Autowired
    private Cloudinary cloudinaryUploader;

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

    public Dipendente findById(long id) { return dipendenteDAO.findById(id).orElseThrow(() -> new NotFoundException(id)); }

    public Dipendente findByIdAndUpdate(long id, Dipendente dipendente) {
        Dipendente found = this.findById(id);
        return dipendenteDAO.save(found);
    }

    public void findByIdAndDelete(long id) {
        Dipendente found = this.findById(id);
        dipendenteDAO.delete(found);
    }

    public String uploadImage(MultipartFile image, int id) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        Dipendente dipendente = this.findById(id);
        dipendente.setProfilePicUrl(url);
        this.findByIdAndUpdate(id, dipendente);
        return url;
    }
}
