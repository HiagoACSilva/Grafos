
import java.util.ArrayList;

public class Vertice<Type> {
    
    private Type Data;
    private ArrayList<Aresta<Type>> ArestaIn;
    private ArrayList<Aresta<Type>> ArestaOut;
    private ArrayList<Aresta<Type>> ArestaAll;

    
    public Vertice(Type Value){
        this.Data = Value;
        this.ArestaIn = new ArrayList<Aresta<Type>>();
        this.ArestaOut = new ArrayList<Aresta<Type>>();
        this.ArestaAll = new ArrayList<Aresta<Type>>();
    }

    public Type getData() {
        return Data;
    }

    public void setData(Type Data) {
        this.Data = Data;
    }
    
    public void AddArestaIn(Aresta<Type> Aresta){
        this.ArestaIn.add(Aresta);
    }
    public void AddArestaOut(Aresta<Type> Aresta){
        this.ArestaOut.add(Aresta);
    }
    public void AddArestaAll(Aresta<Type> Aresta){
        this.ArestaAll.add(Aresta);
    }
    public ArrayList<Aresta<Type>> getArestaIn() {
        return ArestaIn;
    }

    public void setArestaIn(ArrayList<Aresta<Type>> ArestaIn) {
        this.ArestaIn = ArestaIn;
    }

    public ArrayList<Aresta<Type>> getArestaOut() {
        return ArestaOut;
    }

    public void setArestaOut(ArrayList<Aresta<Type>> ArestaOut) {
        this.ArestaOut = ArestaOut;
    }

    public ArrayList<Aresta<Type>> getArestaAll() {
        return ArestaAll;
    }

    public void setArestaAll(ArrayList<Aresta<Type>> ArestaAll) {
        this.ArestaAll = ArestaAll;
    }
    
}
