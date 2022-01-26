//AUTORES: MYLADY DOS SANTOS AMARAL, HIAGO ANTONIO COSTA SILVA
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    
    public static void main(String[] args) throws IOException{
        //SCANNER
        Scanner Scan = new Scanner (System.in);
        
        //VARIAVEL PARA SELECIONAR O GRAFO EM QUESTAO
        //int escolha;
        Grafo<String> grafo=new Grafo<String>();
        //LOOPING DO PROGRAMA
        grafo.AddVertice("1");
        grafo.AddVertice("2");
        grafo.AddVertice("3");
        //grafo.AddVertice("4");
        //grafo.AddVertice("5");
        //grafo.AddVertice("6");
        
        grafo.AddAresta(1.0, "1", "2");
        grafo.AddAresta(1.0, "2", "3");
        boolean bool=true;
        
        
        //SELECIONANDO O GRAFO A SER TRABALHADO
        //escolha = Scan.nextInt();
            
        //LOOPING
        while(bool==true){
            int choise;
            System.out.print("\n1 - Imprimir Grafo\n2 - Ponte Grafo\n3 - Cidades proximas ao local\n");
            
            //ESCOLHENDO O QUE FAZER
            choise = Scan.nextInt();
                
            if(choise == 1){
                grafo.ImprimirGrafo();
                
            }else if(choise == 2){
                grafo.GrafoPonte();
            }else if(choise == 3){
                System.out.println("Qual o Local de partida:");
                String comeco, fim;
                comeco=Scan.next();
                System.out.println("Qual o fim:");
                fim=Scan.next();
                grafo.MenorDistancia(grafo.getVertice(comeco), fim);
            }else{
                bool = false;
            }
        }  
    }
}
