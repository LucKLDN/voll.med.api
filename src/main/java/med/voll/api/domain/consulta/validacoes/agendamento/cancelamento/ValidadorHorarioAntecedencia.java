package med.voll.api.domain.consulta.validacoes.agendamento.cancelamento;

import lombok.var;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaEntity;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;


    @Override
    public void validar(DadosCancelamentoConsultaDTO dados) {
        ConsultaEntity consulta = repository.getReferenceById(dados.getIdConsulta());
        LocalDateTime agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();
        if(diferencaEmHoras < 24){
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }

}
