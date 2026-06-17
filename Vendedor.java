import java.util.EnumSet;
import java.util.Set;

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
