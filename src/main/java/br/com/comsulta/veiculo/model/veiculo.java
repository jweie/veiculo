package br.com.comsulta.veiculo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record veiculo(
        @JsonAlias("Valor: ") String valor,
        @JsonAlias("Marca: ") String Marca,
        @JsonAlias("Modelo:") String Modelo,
        @JsonAlias("AnoModelo") Integer Ano,
        @JsonAlias("Combutivel") String Combustivel) {
}
