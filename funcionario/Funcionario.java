package model.funcionario;

import java.util.List;
import java.util.Set;
import model.permissao.Permissao;
import model.registro.RegistroPonto;

public abstract class Funcionario {

    protected String matricula;
    protected String nome;
    protected String cpf;
    protected boolean ativo;
    protected List<RegistroPonto> registrosPonto;

    protected Funcionario(String m, String n, String cpf){
        this.matricula = m;
        this.nome = n;
        this.cpf = cpf;
        this.registrosPonto = new java.util.ArrayList<>();
        this.ativo = true;
    }

    public void adicionarRegistro(RegistroPonto registro) {
        registrosPonto.add(registro);
    }

    public List<RegistroPonto> getRegistrosPonto() {
        return List.copyOf(registrosPonto);
    }

    public String getMatricula(){
        return this.matricula;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setMatricula(String m){
        this.matricula = m;
    }

    public String getNome(){
        return this.nome;
    }

    protected void setNome(String n){
        this.nome = n;
    }

    public boolean isAtivo(){
        return this.ativo;
    }

    public abstract Set<Permissao> getPermissoes();
}