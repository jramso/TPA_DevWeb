package app;

import java.util.Comparator;

import lib.ArvoreBinaria;
import lib.IArvoreBinaria;

public class Main {

    public static void main (String args[]){
        String nome = "Magaghab";
        Aluno aluno = new Aluno(123, "Jud");
        Aluno aluno2 = new Aluno(123,"Ped");
        GeradorDeArvores gerador = new GeradorDeArvores();
        IArvoreBinaria<Aluno> arv;

        ComparadorAlunoPorMatricula comp = new ComparadorAlunoPorMatricula();

       // ArvoreBinaria <Aluno> arvore = new ArvoreBinaria<Aluno>(comp);
       // No no = arvore.adicionar(aluno);
        //System.out.println(comp.compare(aluno, aluno2));
        arv = new ArvoreBinaria(comp);
        gerador.geraArvorePerfeitamenteBalanceada(1,10,arv);
        System.out.println("Árvore Perfeitamente Balanceada Criada");
        System.out.println("Altura: " + arv.altura());
    }
    
}
