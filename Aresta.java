
public class Aresta<Type> {
    private Double Peso;
    private Vertice<Type> Start;
    private Vertice<Type> End;

    public Double getPeso() {
        return Peso;
    }

    public Aresta(Double Peso, Vertice<Type> Start, Vertice<Type> End) {
        this.Peso = Peso;
        this.Start = Start;
        this.End = End;
    }
    
    public void setPeso(Double Peso) {
        this.Peso = Peso;
    }

    public Vertice<Type> getStart() {
        return Start;
    }

    public void setStart(Vertice<Type> Start) {
        this.Start = Start;
    }

    public Vertice<Type> getEnd() {
        return End;
    }

    public void setEnd(Vertice<Type> End) {
        this.End = End;
    }
    
    
}
