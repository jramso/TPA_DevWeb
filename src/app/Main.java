package app;

import java.util.Comparator;
import java.util.List;

import lib.ArvoreAVL;
import lib.ArvoreBinaria;
import lib.Grafo;
import lib.IArvoreBinaria;
import lib.No;
import lib.Vertice;

public class Main {

    public static void main (String args[]){
        /*String nome = "Magaghab";
        Aluno aluno = new Aluno(123, "Jud");
        Aluno aluno2 = new Aluno(2000000005,"Ped");
        Aluno aluno1 = new Aluno(2000000001,"Ped");
        GeradorDeArvores gerador = new GeradorDeArvores();
        IArvoreBinaria<Aluno> arv;*/

        ComparadorAlunoPorMatricula comp = new ComparadorAlunoPorMatricula();

       // ArvoreBinaria <Aluno> arvore = new ArvoreBinaria<Aluno>(comp);
       // No no = arvore.adicionar(aluno);
        //System.out.println(comp.compare(aluno, aluno2));
        /*arv = new ArvoreBinaria(comp);
        gerador.geraArvorePerfeitamenteBalanceada(1,10,arv);
        arv.remover(aluno2);
        
        System.out.println("Árvore Perfeitamente Balanceada Criada");
        System.out.println("Altura: " + arv.altura());

        System.out.println(arv.caminharEmOrdem());
        System.out.println(arv.caminharEmNivel());*/
        // ArvoreAVL <Aluno> arvore = new ArvoreAVL<>(comp);
        // Aluno aluno = new Aluno(123, "Jud");
        // Aluno aluno2 = new Aluno(2000000005,"Ped");
        // Aluno aluno1 = new Aluno(2000000001,"Ped");
       
        Grafo<String> g = new Grafo<>();

        Vertice<String> v1 = g.adicionaVertice("V1");
        Vertice<String> v2 = g.adicionaVertice("V2");
        Vertice<String> v3 = g.adicionaVertice("V3");
        Vertice<String> v4 = g.adicionaVertice("V4");

        // Adicionar arestas
        g.adicionaAresta(v1, v2);
        g.adicionaAresta(v2, v3);
        g.adicionaAresta(v3, v1);

        // Imprimir o grafo
        System.out.println("Grafo:");
        System.out.println(g);

        // Verificar se o grafo tem ciclos
        System.out.println("Tem ciclo: " + g.temCiclo());

        // Realizar ordenação topológica
        List<Vertice<String>> ordemTopologica = g.ordenacaoTopologica();
        System.out.println("Ordenação Topológica:");
        System.out.println(ordemTopologica);

    }
    
}
