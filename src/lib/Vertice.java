package lib;

import java.util.ArrayList;
import java.util.List;

public class Vertice <G> {

    private G nome;
    private List<Aresta<G>> adj;

    public Vertice(G nome) {
        this.nome = nome;
        adj = new ArrayList<>();
    }

    public G getNome() {
        return nome;
    }

    public void setNome(G nome) {
        this.nome = nome;
    }

    public List<Aresta<G>> getAdj() {
        return adj;
    }

    public void addAdj(Aresta<G> aresta) {
        adj.add(aresta);
    }

    @Override
    public String toString() {
        return String.valueOf(nome);
    }
}