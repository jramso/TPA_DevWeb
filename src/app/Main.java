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
       
        Grafo g = new Grafo();

        Vertice<String> v1 = g.adicionaVertice("V1");
        Vertice<String> v2 = g.adicionaVertice("V2");
        Vertice<String> v3 = g.adicionaVertice("V3");
        Vertice<Integer> v4 = g.adicionaVertice(4);


        g.adicionaAresta(v1, v3);
        g.adicionaAresta(v3, v2);
        g.adicionaAresta(v2, v4);


        List<Vertice> ordemTopologica = g.ordenacaoTopologica();
        System.out.println("Ordem Topológica: " + ordemTopologica);

        System.out.println(g.temCiclo()); // true


    }
    
}
