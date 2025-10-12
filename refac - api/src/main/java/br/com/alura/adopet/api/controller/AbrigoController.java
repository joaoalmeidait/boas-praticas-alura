package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AbrigoDTO;
import br.com.alura.adopet.api.dto.CadastrarAbrigoDTO;
import br.com.alura.adopet.api.dto.CadastrarPetDTO;
import br.com.alura.adopet.api.dto.PetDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.service.AbrigoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService abrigoService;

    @GetMapping
    public ResponseEntity<List<AbrigoDTO>> listar() {
        var pets = abrigoService.listarTodos();
        return ResponseEntity.ok(pets);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastrarAbrigoDTO dto) {
        try {
            abrigoService.cadastrarAbrigo(dto);
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Abrigo cadastrado sucesso!");
    }

    @GetMapping("/{id}/pets")
    public ResponseEntity<List<PetDTO>> listarPets(@PathVariable Long id) {
        try {
            List<PetDTO> petsDoAbrigo = abrigoService.listarPetByID(id);
            return ResponseEntity.ok(petsDoAbrigo);
        } catch (ValidacaoException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{nome}/pets")
    public ResponseEntity<List<Pet>> listarPetsByID(@PathVariable String  nome) {
        try {
            var pets = abrigoService.listarPetByNome(nome);
            return ResponseEntity.ok(pets);
        } catch (ValidacaoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(@PathVariable Long id, @RequestBody @Valid CadastrarPetDTO dto) {
        try {
            abrigoService.cadastrarPet(id, dto);
            return ResponseEntity.ok().build();
        } catch (ValidacaoException exception) {
            return ResponseEntity.notFound().build();
        }
    }

}
