package model.venda;

import java.time.LocalDateTime;
import model.enums.StatusPagamento;

public class PagamentoDinheiro extends Pagamento {

    private double valorRecebido;
    private double troco;

    @Override
    public void processar(double valor) {
        this.valorTotal = valor;
        this.dataPagamento = LocalDateTime.now();
        this.status = StatusPagamento.APROVADO;
        this.valorRecebido = valor;
        this.troco = 0;
    }

    @Override
    public String getMetodoPagamento() {
        return "Dinheiro";
    }

    public double getValorRecebido() {
        return valorRecebido;
    }

    public double getTroco() {
        return troco;
    }
}

