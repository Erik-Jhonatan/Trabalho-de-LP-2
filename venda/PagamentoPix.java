package model.venda;

import java.time.LocalDateTime;
import model.enums.StatusPagamento;

public class PagamentoPix extends Pagamento {

    private static final String CHAVE_PIX = "+55(84)94002-8922";

    public PagamentoPix() {
    }

    @Override
    public void processar(double valor) {
        this.valorTotal = valor;
        this.dataPagamento = LocalDateTime.now();
        this.status = StatusPagamento.APROVADO;
    }

    @Override
    public String getMetodoPagamento() {
        return "PIX";
    }

    public static String getChavePix() {
        return CHAVE_PIX;
    }
}
