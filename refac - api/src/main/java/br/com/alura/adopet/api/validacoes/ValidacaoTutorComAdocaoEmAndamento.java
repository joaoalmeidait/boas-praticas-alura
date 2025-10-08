package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepository;

    public void validar(SolicitacaoAdocaoDTO dto) {
        var tutorComAdocaoEmAndamento = adocaoRepository.existsByTutorIdAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO);

        if (tutorComAdocaoEmAndamento) {
            throw new ValidacaoException("Tutor já possui outra adoção aguardando avaliação!");
        }

    }
}
