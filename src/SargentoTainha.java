import java.util.*;

public class SargentoTainha implements Runnable {
    private List<List<Integer>> filaOutside = new ArrayList<>();
    private static int tempoDeDescanso = 0;
    private int ocioso = 0;
    public static boolean started = false;


    
    public SargentoTainha( List<List<Integer>> fila, int tempoDeDescanso) {
        
        this.filaOutside = fila;
        this.tempoDeDescanso = tempoDeDescanso;
    }

    @Override
    public void run() {
        try {
            for (List<Integer> oficial : filaOutside) {  
                
                if(oficial.get(0) == 0){
                    Barbearia.adicionarFila(new Cliente(0, 0));
                    ocioso++;
                    if(ocioso == 3){
                      
                        Barbearia.filaFora = false;
                        break;
                        // Aqui o programa deve ser encerrado, pois ao sgt tainha chegou ao limite de 3
                    }
                  
                    Thread.sleep(tempoDeDescanso);
                    continue;
                }
                
                Cliente cliente = new Cliente(oficial.get(0), oficial.get(1));
                Barbearia.adicionarTempoEsperaEntrada(tempoDeDescanso);

                if(Barbearia.obterTamanhoTotalFilas() < 20){
                    ocioso = 0;
                    Barbearia.adicionarFila(cliente);   
                  
                    started = true;

                }else{
                   // Não adicionar clientes
                }
                // cortandoCabelo.release();
                Thread.sleep(tempoDeDescanso);
            }
    
            Barbearia.filaFora = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
    }

    public static synchronized int getTempoDeDescanso(){
        return tempoDeDescanso;
    }

    public static synchronized boolean getStarted(){
        return started;
    }
}
