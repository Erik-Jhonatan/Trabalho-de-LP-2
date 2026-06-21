package model.item.estoque;

public class Eletronico extends ItemEstoque {

    private int garantiaMeses;
    private String numeroSerie;

    public Eletronico() {
    }

    public Eletronico(int garantiaMeses, String numeroSerie) {
        this.garantiaMeses = garantiaMeses;
        this.numeroSerie = numeroSerie;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    @Override
    public String getCategoria() {
        return "Eletrônico";
    }

    @Override
    public int getPrazoDevolucaoDias() {
        return 30;
    }
}