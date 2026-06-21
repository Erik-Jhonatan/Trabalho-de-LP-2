package model.venda;

import java.time.LocalDateTime;
import model.enums.StatusPagamento;

public abstract class Pagamento {

    protected double valorTotal;
    protected LocalDateTime dataPagamento;
    protected StatusPagamento status;

    public abstract void processar(double valor);

    public abstract String getMetodoPagamento();

    public double getValorTotal() {
        return valorTotal;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public StatusPagamento getStatus() {
        return status;
    }
}
