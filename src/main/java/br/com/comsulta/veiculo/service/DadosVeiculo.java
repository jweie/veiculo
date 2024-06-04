package br.com.comsulta.veiculo.service;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



public record DadosVeiculo(String codigo,String nome) {


}
