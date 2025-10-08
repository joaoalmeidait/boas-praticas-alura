package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoTutorComLimiteDeAdocoes implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public void validar(SolicitacaoAdocaoDTO dto) {
        var tutuor = tutorRepository.getReferenceById(dto.idTutor());

        long adocoesAprovadas = adocaoRepository.countByTutorStatus(tutuor, StatusAdocao.APROVADO);

        if (adocoesAprovadas >= 5) {
            throw new ValidacaoException("Tutor chegou ao limite máximo de 5 adoções!");
        }
    }
}
