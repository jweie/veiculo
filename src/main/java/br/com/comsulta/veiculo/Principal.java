package br.com.comsulta.veiculo;

import br.com.comsulta.veiculo.model.veiculo;
import br.com.comsulta.veiculo.service.ConverteDados;
import br.com.comsulta.veiculo.service.DadosVeiculo;
import br.com.comsulta.veiculo.service.ListaVeiculo.ListaVeiculo;

import javax.xml.transform.Source;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private ConsumoApi consumo = new ConsumoApi();

    private Scanner leitura = new Scanner(System.in);

    private ConverteDados convensor = new ConverteDados();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";


    public void exibirMenu() {
       var menu = """
               ***OPÇÕES***
               Carro
               Moto
               Caminhão
               
               Digite uma das opções para consulta:
               
               """;
        System.out.println(menu);
        var opcao = leitura.nextLine();
        String endereco;

        if(opcao.toLowerCase().contains("carr")){
          endereco = URL_BASE + "carros/marcas";
        } else if(opcao.toLowerCase().contains("mot")){
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }
        var json = consumo.obterDados(endereco);
        System.out.println(json);

        var marcas = convensor.obterLista(json, DadosVeiculo.class);
        marcas.stream()
                .sorted(Comparator.comparing(DadosVeiculo::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta: ");
        var codigo = leitura.nextLine();

        endereco = endereco + "/" + codigo + "/modelos";
        json = consumo.obterDados(endereco);
        var modeloLista = convensor.obterDados(json, ListaVeiculo.class);

        System.out.println("\nModelos dessa marca:");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(DadosVeiculo::codigo))
                .forEach(System.out::println);

        System.out.println("Digite a pesquisa de carro: ");
        var buscarCarro = leitura.nextLine();

        List<DadosVeiculo> modelosFiltro = modeloLista.modelos().stream()
                .filter(c -> c.nome().toLowerCase().contains(buscarCarro.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados: " + modelosFiltro);
        modelosFiltro.forEach(System.out::println);

        System.out.println("Informe o código do carro para avaliação : ");
        var codigoCarro = leitura.nextLine();

        endereco = endereco + "/" + codigo + "/anos";
        json = consumo.obterDados(endereco);

        List<DadosVeiculo> anos = convensor.obterLista(json, DadosVeiculo.class);

        List<veiculo> veiculos = new ArrayList<>();
        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            json = consumo.obterDados(enderecoAnos);
            veiculo veiculo = convensor.obterDados(json, veiculo.class);
            veiculos.add(veiculo);

            System.out.println("\nTodos os veículos filtrados com avaliações por anos: ");
            veiculos.forEach(System.out::println);
        }

    }
}