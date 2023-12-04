
package lib;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @author jramso
 */
public class Grafo<G> {

    private List<Vertice<G>> vertices;
    private List<Aresta<G>> arestas;
    private List<Integer> visitados;

    /**
     * Constructor - Crie um grafo com 2 listas uma de vertices outra de arestas
     * @see Vertice
     * @see Aresta
     */
    public Grafo() {
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
        visitados = new ArrayList<>();
    }

    /**
     * Este método adiciona um vértice no Grafo inserindo-o na lista de vertices.
     * @param nome - nome do vertice (ex: V1, V2, A, B, C, "1", "2","3")
     * @return v - retorna o vértice adicionado
     */
    public Vertice<G> adicionaVertice(G valor) {
        Vertice<G> v = new Vertice<>(valor);
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
    public Aresta<G> adicionaAresta(Vertice<G> origem, Vertice<G> destino, int peso) {
        Aresta<G> e = new Aresta<>(origem, destino, peso);
        origem.addAdj(e);
        //destino.addAdj(e);
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
    public Aresta<G> adicionaAresta(Vertice<G> origem, Vertice<G> destino) {
        Aresta<G> e = new Aresta<>(origem, destino);
        origem.addAdj(e);
        //destino.addAdj(e);
        arestas.add(e);
        return e;
    }    

    /**
     * Este método verifica se tem ou não uma aresta entre 2 vértices
     * @param origem - ponto de origem da possivel aresta
     * @param destino - ponto de destino da possivel aresta
     * @return boolean
     */
    public boolean temAresta(Vertice<G> origem, Vertice<G> destino) {
        for (Aresta<G> e : origem.getAdj()) {
            if (e.getDestino() == destino) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se o grafo possui ciclos.
     *
     * @return true se o grafo tiver ciclos, false caso contrário.
     */
    public boolean temCiclo() {
        // Conjunto para rastrear os vértices visitados durante a busca em profundidade.
        Set<Vertice<G>> visitados = new HashSet<>();

        // Conjunto para rastrear os vértices em processo de visita durante a busca em profundidade.
        Set<Vertice<G>> emProcesso = new HashSet<>();

        // Verifica ciclos em cada vértice não visitado.
        for (Vertice<G> vertice : vertices) {
            if (!visitados.contains(vertice)) {
                if (temCicloDFS(vertice, visitados, emProcesso)) {
                    return true; // Se encontrar um ciclo em qualquer ponto, retorna true.
                }
            }
        }

        return false; // Se nenhum ciclo for encontrado.
    }

    /**
     * Método auxiliar para verificar ciclos usando a busca em profundidade (DFS).
     *
     * @param vertice     O vértice atual a ser verificado.
     * @param visitados   Conjunto de vértices visitados.
     * @param emProcesso  Conjunto de vértices em processo de visita.
     * @return true se um ciclo for encontrado, false caso contrário.
     */
    private boolean temCicloDFS(Vertice<G> vertice, Set<Vertice<G>> visitados, Set<Vertice<G>> emProcesso) {
        visitados.add(vertice);
        emProcesso.add(vertice);
        
        // Verifica cada vértice adjacente.
        for (Aresta<G> aresta : vertice.getAdj()) {
            Vertice<G> destino = aresta.getDestino();

            // Se o vértice adjacente já estiver em processo de visita, há um ciclo.
            if (emProcesso.contains(destino)) {
                return true;
            }

            // Se o vértice adjacente não estiver visitado, continua a busca em profundidade.
            if (!visitados.contains(destino)) {
                if (temCicloDFS(destino, visitados, emProcesso)) {
                    return true; // Se um ciclo for encontrado em qualquer ponto, retorna true.
                }
            }
        }

        emProcesso.remove(vertice); // Remove o vértice do conjunto em processo após a visita completa.
        return false;
    }


    public List<Vertice<G>> ordenacaoTopologica() {
        // Conjunto para rastrear os vértices visitados durante a busca em profundidade.
        Set<Vertice<G>> visitados = new HashSet<>();

        // Pilha para armazenar os vértices na ordem topológica.
        Stack<Vertice<G>> pilha = new Stack<>();

        // Realiza a ordenação topológica para cada vértice não visitado.
        for (Vertice<G> vertice : vertices) {
            if (!visitados.contains(vertice)) {
                ordenacaoTopologicaDFS(vertice, visitados, pilha);
            }
        }

        // Converte a pilha em uma lista para obter a ordem topológica.
        List<Vertice<G>> ordemTopologica = new ArrayList<>();
        while (!pilha.isEmpty()) {
            ordemTopologica.add(pilha.pop());
        }

        return ordemTopologica;
    }

    /**
     * Método auxiliar para realizar a ordenação topológica usando a busca em profundidade (DFS).
     *
     * @param vertice   O vértice atual a ser verificado.
     * @param visitados Conjunto de vértices visitados.
     * @param pilha     Pilha para armazenar os vértices na ordem topológica.
     */
    private void ordenacaoTopologicaDFS(Vertice<G> vertice, Set<Vertice<G>> visitados, Stack<Vertice<G>> pilha) {
        visitados.add(vertice);

        // Visita cada vértice adjacente.
        for (Aresta<G> aresta : vertice.getAdj()) {
            Vertice<G> destino = aresta.getDestino();

            // Se o vértice adjacente não estiver visitado, continua a busca em profundidade.
            if (!visitados.contains(destino)) {
                ordenacaoTopologicaDFS(destino, visitados, pilha);
            }
        }

        // Adiciona o vértice à pilha após visitar todos os vértices adjacentes.
        pilha.push(vertice);
    }    


    /**
     * Este metodo retorna o peso de uma aresta entre origem e Destino se existir uma aresta
     * @param origem - ponto de origem da possivel aresta
     * @param destino - ponto de destino da possivel aresta
     * @return peso da aresta
     */
    public int getPeso(Vertice<G> origem, Vertice<G> destino) {
        for (Aresta<G> e : origem.getAdj()) {
            if (e.getDestino() == destino) {
                return e.getPeso();
            }
        }
        return 0;
    }

    public String toString() {
        String r = "";
        for (Vertice<G> u : vertices) {
            r += u.toString() + "\n";
            for (Aresta<G> e : u.getAdj()) {
                r += "  -> " + e.getDestino() + "(" + e.getPeso() + ")\n";
            }
        }
        return r;
    }

}
