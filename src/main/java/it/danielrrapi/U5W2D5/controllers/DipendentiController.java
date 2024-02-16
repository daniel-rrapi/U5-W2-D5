package it.danielrrapi.U5W2D5.controllers;

import it.danielrrapi.U5W2D5.entities.Dipendente;
import it.danielrrapi.U5W2D5.exceptions.BadRequestException;
import it.danielrrapi.U5W2D5.payloads.NewDipendenteDTO;
import it.danielrrapi.U5W2D5.servicies.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {
    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping
    public Page<Dipendente> getDipendenti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.dipendenteService.getDipendenti(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Dipendente getDipendenteById(@PathVariable long id) {
        return this.dipendenteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente(@RequestBody @Validated NewDipendenteDTO dipendente, BindingResult valutation) {
        if (valutation.hasErrors()) {
            throw new BadRequestException(valutation.getAllErrors());
        }
        return this.dipendenteService.save(dipendente);
    }

    @PutMapping("/{id}")
    public Dipendente getDipendenteByIdAndUpdate(@PathVariable long id, @RequestBody  Dipendente dipendente, BindingResult valutation) {
        if (valutation.hasErrors()) {
            throw  new BadRequestException(valutation.getAllErrors());
        }
        return this.dipendenteService.findByIdAndUpdate(id, dipendente);
    }

    @DeleteMapping("/{id}")
    public void getDipendenteByIdAndDelete(@PathVariable long id) {
        this.dipendenteService.findByIdAndDelete(id);
    }
    @PostMapping("/{id}/upload")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile image, @PathVariable int id) throws IOException {
        return this.dipendenteService.uploadImage(image, id);
    }


}
