package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDTO;
import br.com.alura.adopet.api.dto.CadastrarAbrigoDTO;
import br.com.alura.adopet.api.dto.CadastrarPetDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoService {

    @Autowired
    AbrigoRepository repository;

    @Autowired
    PetService petService;

    public List<AbrigoDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(AbrigoDTO::new)
                .toList();
    }

    public void cadastrarAbrigo(CadastrarAbrigoDTO dto) {
        boolean jaCadastrado = repository.existsByNomeOrEmailOrTelefone(dto.nome(), dto.email(), dto.telefone());

        if (jaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        }

        var abrigo = new Abrigo(dto.nome(), dto.telefone(), dto.email());
        repository.save(abrigo);
    }

    public List<Pet> listarPetByID(Long id) {
        var abrigo = buscarAbrigoPorId(id);
        return abrigo.getPets() != null ? abrigo.getPets() : List.of();
    }

    public List<Pet> listarPetByNome(String nome) {
        var abrigo = repository.findByNome(nome)
                .orElseThrow(() -> new ValidacaoException("Abrigo não encontrado."));

        return abrigo.getPets() != null ? abrigo.getPets() : List.of();
    }

    public void cadastrarPet(Long id, CadastrarPetDTO dto) {

        var abrigo = buscarAbrigoPorId(id);

        petService.cadastrarPet(abrigo, dto);
    }

    private Abrigo buscarAbrigoPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Abrigo não encontrado."));
    }
}

