class Cliente {
    private int categoria;
    private int tempoServico;
    private long entradaFilaTimestamp; // Tempo quando o cliente, seja qual for entrou na fila

    public Cliente(int categoria, int tempoServico) {
        this.categoria = categoria;
        this.tempoServico = tempoServico;
        this.entradaFilaTimestamp = Barbearia.obterTempoEsperaEntrada();
    }


    public synchronized  int getCategoria() {
        return categoria;
    }

    public synchronized  int getTempoServico() {
        return tempoServico;
    }

    public synchronized long getEntradaFilaTimestamp() {
        return entradaFilaTimestamp;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "categoria=" + categoria +
                ", tempoServico=" + tempoServico +
                '}';
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public void setTempoServico(int tempoServico) {
        this.tempoServico = tempoServico;
    }
}