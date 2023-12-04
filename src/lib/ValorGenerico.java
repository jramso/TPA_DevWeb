package lib;

public class ValorGenerico<G> {
    private G valor;

    public ValorGenerico(G valor) {
        this.valor = valor;
    }

    public G getValor() {
        return valor;
    }

    public void setValor(G valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}
