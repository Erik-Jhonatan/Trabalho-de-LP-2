package model.funcionario;

import java.util.Set;

public class Vendedor extends Funcionario {

    private double percentualComissao;

    public double getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(double percentualComissao) {
        this.percentualComissao = percentualComissao;
    }

    @Override
    public double calcularSalarioTotal(int mes, int ano) {
        return salarioBase + calcularComissao(mes, ano);
    }

    @Override
    public double calcularComissao(int mes, int ano) {
        return 0;
    }

    @Override
public Set<Permissao> getPermissoes() {
    return Set.of(
        Permissao.REALIZAR_VENDA,
        Permissao.APLICAR_DESCONTO_PADRAO,
        Permissao.CONSULTAR_ESTOQUE
    );
}

    public Vendedor(String m, String n, String cpf, double sBase, double percentualComissao) {
        super(m, n, cpf, sBase);
        this.percentualComissao = percentualComissao;
    }

}

.
