package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastrarPetDTO;
import br.com.alura.adopet.api.dto.PetDTO;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    PetRepository repository;


    public List<PetDTO> listarPetsDisponiveis() {
        return repository.findAllByAdotadoFalse()
                .stream()
                .map(PetDTO::new)
                .toList();
    }

    public void cadastrarPet(Abrigo abrigo, CadastrarPetDTO dto) {
        repository.save(new Pet(abrigo, dto));
    }
}
