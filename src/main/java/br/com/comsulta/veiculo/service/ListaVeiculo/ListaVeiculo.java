package br.com.comsulta.veiculo.service.ListaVeiculo;

import br.com.comsulta.veiculo.service.DadosVeiculo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record ListaVeiculo (List<DadosVeiculo> modelos){

}
