package model.funcionario;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public abstract class Funcionario {

    protected String matricula;
    protected String nome;
    protected String cpf;
    protected LocalDate dataAdmissao;
    protected double salarioBase;
    protected boolean ativo;

    protected List<RegistroPonto> registrosPonto;

    public abstract double calcularSalarioTotal(int mes, int ano);

    public double calcularComissao(int mes, int ano){
        return 0;
    }

    protected Funcionario(String m, String n, String cpf, double sBase){
        this.matricula = m;
        this.nome = n;
        this.cpf = cpf;
        this.salarioBase = sBase;
        this.dataAdmissao = LocalDate.now();
        this.registrosPonto = new java.util.ArrayList<>();
        this.ativo = true;
    }

    public String getMatricula(){
        return this.matricula;
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

    public double getSalarioBase(){
        return this.salarioBase;
    }

    public void setSalarioBase(double s){
        this.salarioBase = s;
    }

    protected boolean getPresenca(){
        return this.ativo;
    }

    protected void setPresenca(boolean ativo){
        this.ativo = ativo;
    }

    public abstract Set<Permissao> getPermissoes();
}

.