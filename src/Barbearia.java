import java.util.concurrent.*;

public class Barbearia {
    public static boolean filaFora = true;

    static ConcurrentLinkedQueue<Cliente> filaOficiais = new ConcurrentLinkedQueue<>();
    static ConcurrentLinkedQueue<Cliente> filaSargentos = new ConcurrentLinkedQueue<>();
    static ConcurrentLinkedQueue<Cliente> filaCabos = new ConcurrentLinkedQueue<>();

    private static int totalAtendimentosCabos = 0;
    private static int totalAtendimentosOficiais = 0;
    private static int totalAtendimentosSargentos = 0;
    private static int totalAtendimentosGeral = 0;
    private static int totalAtendimentosPausa = 0;
    
    private static int tempoEsperaEntrada = 0;
    private static int tempoEsperaSaida = 0;
    
    public static synchronized int obterTempoEsperaEntrada() {
        return tempoEsperaEntrada;
    }

    public static synchronized void adicionarTempoEsperaEntrada(int tempoEsperaEntrada) {
    	Barbearia.tempoEsperaEntrada += tempoEsperaEntrada;
    }

    public static synchronized int obterTempoEsperaSaida() {
        return tempoEsperaSaida;
    }

    public static synchronized void adicionarTempoEsperaSaida(int tempoEsperaSaida) {
    	Barbearia.tempoEsperaSaida += tempoEsperaSaida;
    }

    public static void exibirFila() {
        System.out.println("Fila Oficiais: " + filaOficiais);
        System.out.println("Fila Sargentos: " + filaSargentos);
        System.out.println("Fila Cabos: " + filaCabos);
    }

    public static synchronized int obterTamanhoTotalFilas() {
        return filaOficiais.size() + filaSargentos.size() + filaCabos.size();
    }

    public static synchronized int obterTamanhoFilaOficiais() {
        return filaOficiais.size();
    }

    public static synchronized int obterTamanhoFilaSargentos() {
        return filaSargentos.size();
    }

    public static synchronized int obterTamanhoFilaCabos() {
        return filaCabos.size();
    }

    public static void adicionarFila(Cliente cliente) {
        totalAtendimentosGeral++;
        switch (cliente.getCategoria()) {
            case 0: // Pausa
                totalAtendimentosPausa++;
                break;
            case 1: // Oficiais
                if (obterTamanhoTotalFilas() < 20) {
                    totalAtendimentosOficiais++;
                    filaOficiais.offer(cliente);
                }
                break;
            case 2: // Sargentos
                if (obterTamanhoTotalFilas() < 20) {
                    totalAtendimentosSargentos++;
                    filaSargentos.offer(cliente);
                }
                break;
            case 3: // Cabos
                if (obterTamanhoTotalFilas() < 20) {
                    totalAtendimentosCabos++;
                    filaCabos.offer(cliente);
                }
                break;
        }
    }

    public static synchronized int obterTotalAtendimentosGeral() {
        return totalAtendimentosGeral;
    }

    public static synchronized int obterTotalAtendimentosOficiais() {
        return totalAtendimentosOficiais;
    }

    public static synchronized int obterTotalAtendimentosSargentos() {
        return totalAtendimentosSargentos;
    }

    public static synchronized int obterTotalAtendimentosCabos() {
        return totalAtendimentosCabos;
    }

    public static synchronized int obterTotalAtendimentosPausa() {
        return totalAtendimentosPausa;
    }

    public static synchronized void removerFila(int categoria) {
        switch (categoria) {
            case 1:
                filaOficiais.poll();
                break;
            case 2:
                filaSargentos.poll();
                break;
            case 3:
                filaCabos.poll();
                break;
        }
    }

    public static synchronized Cliente pegarCliente() {
        if (!filaOficiais.isEmpty())
            return filaOficiais.peek();
        if (!filaSargentos.isEmpty())
            return filaSargentos.peek();
        if (!filaCabos.isEmpty())
            return filaCabos.peek();
        return null;
    }

    public static synchronized boolean isFilaVazia() {
        return filaOficiais.isEmpty() && filaSargentos.isEmpty() && filaCabos.isEmpty();
    }
}
