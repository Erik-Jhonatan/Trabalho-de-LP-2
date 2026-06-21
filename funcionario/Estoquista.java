package model.funcionario;

import java.util.EnumSet;
import java.util.Set;

public class Estoquista extends Funcionario {

    private final boolean habilitadoEmpilhadeira;

    public Estoquista(String matricula, String nome, String cpf, boolean habilitadoEmpilhadeira) {
        super(matricula, nome, cpf);
        this.habilitadoEmpilhadeira = habilitadoEmpilhadeira;
    }

    public boolean podeMovimentarItemPesado(){
        return habilitadoEmpilhadeira;
    }

    public Set<Permissao> getPermissoes() {
        return EnumSet.of(
            Permissao.CONSULTAR_ESTOQUE,
            Permissao.ATUALIZAR_ESTOQUE
        );
    }
}