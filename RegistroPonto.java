import java.time.LocalDateTime;

    public class RegistroPonto {

    private final String codigo;
    private final LocalDateTime dataHora;
    private final TipoRegistro tipo;
    private final String justificativa;

    public RegistroPonto(String codigo,
                         LocalDateTime dataHora,
                         TipoRegistro tipo,
                         String justificativa) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código inválido.");
        }

        if (dataHora == null) {
            throw new IllegalArgumentException("Data/hora inválida.");
        }

        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de registro inválido.");
        }

        this.codigo = codigo;
        this.dataHora = dataHora;
        this.tipo = tipo;
        this.justificativa = justificativa;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public TipoRegistro getTipo() {
        return tipo;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public boolean isBatidaValida() {
        return codigo != null
                && !codigo.isBlank()
                && dataHora != null
                && tipo != null;
    }

    public boolean isEntrada() {
        return tipo == TipoRegistro.ENTRADA;
    }

    public boolean isSaida() {
        return tipo == TipoRegistro.SAIDA;
    }

    public boolean isInicioIntervalo() {
        return tipo == TipoRegistro.INTERVALO_INICIO;
    }

    public boolean isFimIntervalo() {
        return tipo == TipoRegistro.INTERVALO_FIM;
    }

    @Override
    public String toString() {
        return "[" + tipo + "] "
                + dataHora
                + " - Código: "
                + codigo;
    }
}