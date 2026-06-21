package model.funcionario;

import java.util.EnumSet;
import java.util.Set;

public class Gerente extends Funcionario {

    public Gerente(String matricula, String nome, String cpf) {
        super(matricula, nome, cpf);
    }

    public Set<Permissao> getPermissoes() {
        return EnumSet.allOf(Permissao.class);
    }

}