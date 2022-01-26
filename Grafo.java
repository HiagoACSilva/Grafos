
import java.util.ArrayList;

 
public class Grafo<Type>{
    private ArrayList<Vertice<Type>> vertices;
    private ArrayList<Aresta<Type>> arestas;
    private ArrayList<ArrayList<Aresta<Type>>> Caminhos;
    public Grafo(){
        this.vertices = new ArrayList<Vertice<Type>>();
        this.arestas = new ArrayList<Aresta<Type>>();
        this.Caminhos = new ArrayList<ArrayList<Aresta<Type>>>();
    }
    
    public void AddVertice(Type Data){
        Vertice<Type> NewVertice = new Vertice<Type>(Data);
        this.vertices.add(NewVertice);
    }
    
    public void AddAresta(Double Peso, String DataStart, String DataEnd){
        Vertice<Type> Start = this.getVertice(DataStart);
        Vertice<Type> End = this.getVertice(DataEnd);
        Aresta<Type> aresta = new Aresta<Type>(Peso, Start, End);
        Start.AddArestaOut(aresta);
        Start.AddArestaAll(aresta);
        End.AddArestaIn(aresta);
        End.AddArestaAll(aresta);
        this.arestas.add(aresta);
    }

    public ArrayList<ArrayList<Aresta<Type>>> getCaminhos() {
        return Caminhos;
    }

    public void setCaminhos(ArrayList<ArrayList<Aresta<Type>>> Caminhos) {
        this.Caminhos = Caminhos;
    }

    public ArrayList<Vertice<Type>> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertice<Type>> vertices) {
        this.vertices = vertices;
    }

    public ArrayList<Aresta<Type>> getArestas() {
        return arestas;
    }

    public void setArestas(ArrayList<Aresta<Type>> arestas) {
        this.arestas = arestas;
    }
   
    public Vertice<Type> getVertice(String Data){
        Vertice<Type> vertice = null;
        for(int i=0; i < this.vertices.size(); i++){
            if(this.vertices.get(i).getData().equals(Data)){
                vertice=this.vertices.get(i);
                break;
            }
        }
        return vertice;
    }
    
    public void buscaemlargura(String Data){
        ArrayList<Vertice<Type>> marcados = new ArrayList<Vertice<Type>>();
        ArrayList<Vertice<Type>> fila = new ArrayList<Vertice<Type>>();
        Vertice<Type> atual = getVertice(Data);
        marcados.add(atual);
        System.out.println(atual.getData());
        fila.add(atual);
        while(fila.size() > 0){
            Vertice<Type> visitado = fila.get(0);
            for(int i=0; i < visitado.getArestaOut().size(); i++){
                Vertice<Type> proximo = visitado.getArestaOut().get(i).getEnd();
                if(!marcados.contains(proximo)){
                    marcados.add(proximo);
                    System.out.println(proximo.getData());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
    }
    public boolean Ponte(Aresta<Type> A, Vertice<Type> B, Type Data, ArrayList<Aresta<Type>> Visitados){
        Visitados.add(A);
        boolean True=false;
        for(int i=0; i < B.getArestaAll().size(); i++){
            for(int j=0; j<Visitados.size(); j++){
                True = false;
                if(B.getArestaAll().get(i).equals(Visitados.get(j))){
                    True = true;
                    break;
                }
            }
            if(!True){
                if(!B.getArestaAll().get(i).equals(A)){
                    if(!B.getArestaAll().get(i).getEnd().equals(B)){
                        if(B.getArestaAll().get(i).getEnd().getData().equals(Data)){
                            return true;
                        }
                        if(Ponte(B.getArestaAll().get(i),B.getArestaAll().get(i).getEnd(), Data, Visitados)){
                            return true;
                        }
                    }else{
                        if(B.getArestaAll().get(i).getStart().getData().equals(Data)){
                            return true;
                        }
                        if(Ponte(B.getArestaAll().get(i),B.getArestaAll().get(i).getEnd(), Data, Visitados)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public void Busca(Vertice<Type> A, ArrayList<Aresta<Type>> B, ArrayList<Vertice<Type>> Visitado,
            ArrayList<ArrayList<Aresta<Type>>> Caminhos, Type Data){
        Visitado.add(A);
        boolean Visitou=false;
        for(int i =0; i<A.getArestaAll().size(); i++){
            for(int j=0; j<Visitado.size(); j++){
                if(!A.getArestaAll().get(i).getEnd().equals(A)){
                    if(A.getArestaAll().get(i).getEnd().equals(Visitado.get(j))){
                        Visitou=true;
                    }
                }else if(!A.getArestaAll().get(i).getStart().equals(A)){ 
                    if(A.getArestaAll().get(i).getStart().equals(Visitado.get(j))){
                        Visitou=true;
                    }
                }
            }
            if(!Visitou){
                B.add(A.getArestaAll().get(i));
                if(A.getArestaAll().get(i).getEnd().getData().equals(Data) || A.getArestaAll().get(i).getStart().getData().equals(Data)){
                    Caminhos.add(B);
                    B.remove(B.size()-1);
                }else{
                    if(!A.getArestaAll().get(i).getEnd().equals(A)){
                        Busca(A.getArestaAll().get(i).getEnd(),B,Visitado,Caminhos,Data);
                    }else{
                        Busca(A.getArestaAll().get(i).getStart(),B,Visitado,Caminhos,Data);
                    }   
                }
            }
        }
        if(Visitado.isEmpty()){
            this.setCaminhos(Caminhos);
        }
    }
    public void MenorDistancia(Vertice<Type> Comeco, Type Fim){
        Double Menor=9999.0;
        Double Distancia=0.0;
        ArrayList<Aresta<Type>> Caminho = new ArrayList<Aresta<Type>>();
        ArrayList<Vertice<Type>> Visitados = new ArrayList<Vertice<Type>>();
        ArrayList<ArrayList<Aresta<Type>>> Caminhosa = new ArrayList<ArrayList<Aresta<Type>>>();
        Busca(Comeco,Caminho, Visitados, Caminhosa, Fim);
        for(int i=0; i<this.Caminhos.size(); i++){
            for(int j=0; j<this.Caminhos.get(i).size(); j++){
                Distancia=Distancia+this.Caminhos.get(i).get(j).getPeso();
            }
            if(Distancia <= Menor){
                Menor=Distancia;
            }
        }
        Double D;
        ArrayList<Aresta> Cidades = new ArrayList<Aresta>();
        for(int i=0; i<this.Caminhos.size(); i++){
            D=0.0;
            boolean repetido=false;
            for(int j=0; j<this.Caminhos.get(i).size(); j++){
                if(D+this.Caminhos.get(i).get(j).getPeso()<=Menor){
                    for(int k=0; k<Cidades.size(); k++){
                        if(this.Caminhos.get(i).get(j).equals(Cidades.get(k))){
                            repetido=true;
                        }
                    }
                    if(!repetido){
                        Cidades.add(this.Caminhos.get(i).get(j));
                    }
                    repetido=false;
                }
            }
        }
        
        for(int i=0; i<Cidades.size(); i++){
                System.out.println("Distancia:" + Cidades.get(i).getPeso() 
                        + "\t Vertices: " + Cidades.get(i).getStart().getData() 
                        + " " + Cidades.get(i).getEnd().getData());
        }
    }
    public void GrafoPonte(){
        ArrayList<Aresta<Type>> Arestas = new ArrayList<Aresta<Type>>();
        ArrayList<Aresta<Type>> Visitados = new ArrayList<Aresta<Type>>();
        Vertice<Type> vertice=this.vertices.get(0);
        for(int i=0; i < vertice.getArestaAll().size();i++){
            Visitados.clear();
            if(Ponte(vertice.getArestaAll().get(i),
                    vertice.getArestaAll().get(i).getEnd(),
                    vertice.getArestaAll().get(i).getStart().getData(),Visitados)){
                Arestas.add(vertice.getArestaAll().get(i));
            }
        }
        if(Arestas.size() < 0){
            System.out.println("Quantidade de Arestas: " + Arestas.size());
            System.out.println("Arestas:");
            for(int j=0; j < Arestas.size(); j++){
                System.out.println("Peso:" + Arestas.get(j).getPeso() 
                        + "\t Vertices: " + Arestas.get(j).getStart().getData() 
                        + " " + Arestas.get(j).getEnd().getData());
            }
        }else{
            System.out.println("Conectado por Arestas");
        }
    }
    public void ImprimirGrafo(){
        System.out.println("Quantidade de Vertices: " + this.vertices.size());
        System.out.println("Quantidade de Arestas: " + this.arestas.size());
        System.out.println("Vertices:");
        for(int i=0; i < this.vertices.size(); i++){
            System.out.println(this.vertices.get(i).getData());
        }
        System.out.println("Arestas:");
        for(int j=0; j < this.arestas.size(); j++){
            System.out.println("Peso: " + this.arestas.get(j).getPeso() 
                + "\t Vertices: " + this.arestas.get(j).getStart().getData() + 
                " " + this.arestas.get(j).getEnd().getData());
        }
    }
}
