package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.CadastrarTutorDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

public class TutorService {

    @Autowired
    TutorRepository repository;

    public void cadastrarTutor(CadastrarTutorDTO dto) {

        var tutorExists = repository.existsByTelefoneOrEmail(dto.telefone(), dto.email());

        if (tutorExists) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor");
        }

        repository.save(new Tutor(dto));
    }

    public void atualizar(@Valid AtualizacaoTutorDto dto) {

        var tutor = repository.findById(dto.id())
                .orElseThrow(() -> new ValidacaoException("Nào foi encontrado tutor com o id informado"));

        tutor.atualizarDados(dto);
    }
}
