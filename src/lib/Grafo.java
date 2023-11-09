
package lib;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jramso
 */
public class Grafo {

    private List<Vertice> vertices;
    private List<Aresta> arestas;

    public Grafo() {
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
    }

    public Vertice adicionaVertice(String nome) {
        Vertice v = new Vertice(nome);
        vertices.add(v);
        return v;
    }

    
    public Aresta adicionaAresta(Vertice origem, Vertice destino, int peso) {
        Aresta e = new Aresta(origem, destino, peso);
        origem.addAdj(e);
        destino.addAdj(e);
        arestas.add(e);
        return e;
    }
    
    public Aresta adicionaAresta(Vertice origem, Vertice destino) {
        Aresta e = new Aresta(origem, destino);
        origem.addAdj(e);
        destino.addAdj(e);
        arestas.add(e);
        return e;
    }    

    public boolean temAresta(Vertice origem, Vertice destino) {
        for (Aresta e : origem.getAdj()) {
            if (e.getDestino() == destino) {
                return true;
            }
        }
        return false;
    }

    public int getPeso(Vertice origem, Vertice destino) {
        for (Aresta e : origem.getAdj()) {
            if (e.getDestino() == destino) {
                return e.getPeso();
            }
        }
        return -1;
    }

    public String toString() {
        String r = "";
        for (Vertice u : vertices) {
            r += u.toString() + "\n";
            for (Aresta e : u.getAdj()) {
                r += "  -> " + e.getDestino() + "(" + e.getPeso() + ")\n";
            }
        }
        return r;
    }

}
