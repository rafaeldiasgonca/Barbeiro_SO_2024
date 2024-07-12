public class TenenteEscovinha implements Runnable {
    private final int tempoDeEspera = 3;
    private int totalVerificacoes = 0;

    private int tamanhoMedioDasFilas = 0;

    private int porcentagemMediaOcupacaoCadeirasTotal = 0;
    private int porcentagemMediaOcupacaoCadeirasOficiais = 0;
    private int porcentagemMediaOcupacaoCadeirasSargentos = 0;
    private int porcentagemMediaOcupacaoCadeirasCabos = 0;

    @Override
    public void run() {
        while (true) {
            if (SargentoTainha.getStarted() == true) {
                try {
                   
                    // A cada 3 segundos o Tente escovinha verifica a fila
                    this.tamanhoMedioDasFilas += Barbearia.obterTamanhoTotalFilas();

                    this.totalVerificacoes++;

                    this.porcentagemMediaOcupacaoCadeirasTotal += (Barbearia.obterTamanhoTotalFilas() * 100) / 20;
                    this.porcentagemMediaOcupacaoCadeirasOficiais += (Barbearia.obterTamanhoFilaOficiais() * 100) / 20;
                    
                   
                    this.porcentagemMediaOcupacaoCadeirasSargentos += (Barbearia.obterTamanhoFilaSargentos() * 100) / 20;
                    this.porcentagemMediaOcupacaoCadeirasCabos += (Barbearia.obterTamanhoFilaCabos() * 100) / 20;

                    Thread.sleep(tempoDeEspera);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (RecrutaZero.getCorteFinalizados()) {
                       // Acabaram os clientes
                        break;
                    }
                }
            }
        }

        System.out.println(this.imprimirRelatorio());
    }

    public String imprimirRelatorio() {
        String linhaDivisoria = "------------------------------------------------------------------------------------------------------------------------\n";

        String ocupacaoCadeiras = "Estado de ocupação da(s) cadeira(s) (% por categoria e livre)\n"
            + "Oficiais: "
            + (double) this.porcentagemMediaOcupacaoCadeirasOficiais / (double) this.totalVerificacoes
            + "% "
            + "\nSargentos: "
            + (double) this.porcentagemMediaOcupacaoCadeirasSargentos / (double) this.totalVerificacoes
            + "% "
            + "\nCabos: "
            + (double) this.porcentagemMediaOcupacaoCadeirasCabos / (double) this.totalVerificacoes
            + "% "
            + "\nTotal: "
            + (double) this.porcentagemMediaOcupacaoCadeirasTotal / (double) this.totalVerificacoes
            + "% "
            + "\n";
        
        String comprimentoMedioDasFilas = "Comprimento médio das filas: "
            + (double) this.tamanhoMedioDasFilas / (double) this.totalVerificacoes
            + "\n";
        
        String tempoMedioDeAtendimentoPorCategoria = "Tempo médio de atendimento por categoria\n"
            + "Oficiais: "
            + (double) RecrutaZero.getTempoMedioDeCorteOficiais() / (double) Barbearia.obterTotalAtendimentosOficiais()
            + "\nSargentos: "
            + (double) RecrutaZero.getTempoMedioDeCorteSargentos() / (double) Barbearia.obterTotalAtendimentosSargentos()
            + "\nCabos: "
            + (double) RecrutaZero.getTempoMedioDeCorteCabos() / (double) Barbearia.obterTotalAtendimentosCabos()
            + "\nTotal: "
            + (double) RecrutaZero.getTempoMedioDeCorteTotal() / (double) Barbearia.obterTotalAtendimentosGeral()
            + "\n";
        
        String tempoMedioDeEsperaPorCategoria = "Tempo médio de espera por categoria\n"
            + "Oficiais: "
            + (double) RecrutaZero.getTempoMedioDeEsperaOficial() / (double) Barbearia.obterTotalAtendimentosOficiais()
            + "\nSargentos: "
            + (double) RecrutaZero.getTempoMedioDeEsperaSargento() / (double) Barbearia.obterTotalAtendimentosSargentos()
            + "\nCabos: "
            + (double) RecrutaZero.getTempoMedioDeEsperaCabo() / (double) Barbearia.obterTotalAtendimentosCabos()
            + "\nTotal: "
            + (double) RecrutaZero.getTempoMedioDeEsperaTotal() / (double) Barbearia.obterTotalAtendimentosGeral()
            + "\n";
        
        String numeroDeAtendimentosPorCategoria = "Número de atendimentos por categoria\n"
            + "Oficiais: "
            + RecrutaZero.getQuantidadeDeAtendimentosOficiais()
            + "\nSargentos: "
            + RecrutaZero.getQuantidadeDeAtendimentosSargentos()
            + "\nCabos: "
            + RecrutaZero.getQuantidadeDeAtendimentosCabos()
            + "\nTotal: "
            + RecrutaZero.getQuantidadeDeAtendimentosTotal()
            + "\n";
        
        String numeroTotalDeClientesPorCategoria = "Número total de clientes por categoria (oficiais, sargentos, cabos e pausa)\n"
            + "Oficiais: "
            + Barbearia.obterTotalAtendimentosOficiais()
            + "\nSargentos: "
            + Barbearia.obterTotalAtendimentosSargentos()
            + "\nCabos: "
            + Barbearia.obterTotalAtendimentosCabos()
            + "\nPausa: "
            + Barbearia.obterTotalAtendimentosPausa()
            + "\n";

        return linhaDivisoria + ocupacaoCadeiras + comprimentoMedioDasFilas + tempoMedioDeAtendimentoPorCategoria
            + tempoMedioDeEsperaPorCategoria + numeroDeAtendimentosPorCategoria + numeroTotalDeClientesPorCategoria
            + linhaDivisoria;
    }
}
