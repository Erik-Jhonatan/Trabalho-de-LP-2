package model.funcionario;

import java.util.EnumSet;
import java.util.Set;
import model.permissao.Permissao;

public class Vendedor extends Funcionario {

    public Vendedor(String matricula, String nome, String cpf) {
        super(matricula, nome, cpf);
    }

    public Set<Permissao> getPermissoes() {
        return EnumSet.of(
            Permissao.REALIZAR_VENDA,
            Permissao.APLICAR_DESCONTO_PADRAO,
            Permissao.CONSULTAR_ESTOQUE
        );
    }
}