package app;

import java.util.Comparator;

import lib.ArvoreBinaria;

public class Main {

    public static void main (String args[]){
        String nome = "Magaghab";
        Aluno aluno = new Aluno(123, "Jud");
        Aluno aluno2 = new Aluno(123,"Ped");

        ComparadorAlunoPorMatricula comp = new ComparadorAlunoPorMatricula();

       // ArvoreBinaria <Aluno> arvore = new ArvoreBinaria<Aluno>(comp);
       // No no = arvore.adicionar(aluno);
        System.out.println(comp.compare(aluno, aluno2));

    }
    
}
