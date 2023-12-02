
package lib;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jramso
 */
public class Grafo {

    private List<Vertice> vertices;
    private List<Aresta> arestas;
    private List<Boolean> visitados;

    /**
     * Constructor - Crie um grafo com 2 listas uma de vertices outra de arestas
     * @see Vertice
     * @see Aresta
     */
    public Grafo() {
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
        visitados = new ArrayList();
    }

    /**
     * Este método adiciona um vértice no Grafo inserindo-o na lista de vertices.
     * @param nome - nome do vertice (ex: V1, V2, A, B, C, "1", "2","3")
     * @return v - retorna o vértice adicionado
     */
    public Vertice adicionaVertice(String nome) {
        Vertice v = new Vertice(nome);
        vertices.add(v);
        return v;
    }

    /**
     * SOBRECARGA : :
     * Este método adiciona uma aresta no Grafo com peso.
     * @param origem - ponto de inicio da aresta
     * @param destino - ponto de destino da aresta
     * @param peso - custo da origem até o destino
     * @return e - retorna uma aresta
     */
    public Aresta adicionaAresta(Vertice origem, Vertice destino, int peso) {
        Aresta e = new Aresta(origem, destino, peso);
        origem.addAdj(e);
        destino.addAdj(e);
        arestas.add(e);
        return e;
    }
    /**
     * SOBRECARGA : :
     * Este método adiciona uma aresta no Grafo sem peso.
     * @param origem - ponto de inicio da aresta
     * @param destino - ponto de destino da aresta
     * @return e - retorna uma aresta
     */
    public Aresta adicionaAresta(Vertice origem, Vertice destino) {
        Aresta e = new Aresta(origem, destino);
        origem.addAdj(e);
        destino.addAdj(e);
        arestas.add(e);
        return e;
    }    

    /**
     * Este método verifica se tem ou não uma aresta entre 2 vértices
     * @param origem - ponto de origem da possivel aresta
     * @param destino - ponto de destino da possivel aresta
     * @return boolean
     */
    public boolean temAresta(Vertice origem, Vertice destino) {
        for (Aresta e : origem.getAdj()) {
            if (e.getDestino() == destino) {
                return true;
            }
        }
        return false;
    }

    public boolean temCiclo(){

        for (int i = 0; i < vertices.size(); i++) {
            visitados.add(false);
        }

        // Chama o método recursivo para visitar a raiz
        buscaEmProfundidadeRecursivo(vertices.get(0));

        // Verifica se algum vértice foi visitado mais de uma vez
        for (int i = 0; i < visitados.size(); i++) {
            if (visitados.get(i)) {
                return true;
            }
        }

        return false;
    }

    private void buscaEmProfundidadeRecursivo(Vertice v) {

        // Marca o vértice como visitado
        visitados.set(vertices.indexOf(v), true);

        // Visita todos os vizinhos do vértice
        for (Aresta e : v.getAdj()) {
            if (!visitados.get(vertices.indexOf(e.getDestino()))) {
                buscaEmProfundidadeRecursivo(e.getDestino());
            }
        }
    }

    /**
     * Este metodo retorna o peso de uma aresta entre origem e Destino se existir uma aresta
     * @param origem - ponto de origem da possivel aresta
     * @param destino - ponto de destino da possivel aresta
     * @return peso da aresta
     */
    public int getPeso(Vertice origem, Vertice destino) {
        for (Aresta e : origem.getAdj()) {
            if (e.getDestino() == destino) {
                return e.getPeso();
            }
        }
        return 0;
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
