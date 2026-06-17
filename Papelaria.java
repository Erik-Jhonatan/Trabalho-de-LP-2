package model.item.estoque;

public class Papelaria extends ItemEstoque {

    private String marca;
    private boolean materialEscolar;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean isMaterialEscolar() {
        return materialEscolar;
    }

    public void setMaterialEscolar(boolean materialEscolar) {
        this.materialEscolar = materialEscolar;
    }

    @Override
    public String getCategoria() {
        return "Papelaria";
    }

    @Override
    public int getPrazoDevolucaoDias() {
        return 30;
    }
}