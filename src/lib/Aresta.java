package lib;

public class Aresta<G> {

    private Vertice<G> origem;
    private Vertice<G> destino;
    private int peso;

    public Aresta(Vertice<G> origem, Vertice<G> destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public Aresta(Vertice<G> origem, Vertice<G> destino) {
        this.origem = origem;
        this.destino = destino;
        this.peso = 1;
    }

    public Vertice<G> getOrigem() {
        return origem;
    }

    public void setOrigem(Vertice<G> origem) {
        this.origem = origem;
    }

    public Vertice<G> getDestino() {
        return destino;
    }

    public void setDestino(Vertice<G> destino) {
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return origem.getNome() + " -> " + destino.getNome() + "(" + getPeso() + ")";
    }
}

