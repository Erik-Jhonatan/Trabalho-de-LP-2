```mermaid
classDiagram
    direction TB

    %% ============================================
    %% NÚCLEO 1: FUNCIONÁRIOS
    %% ============================================

    class Funcionario {
        <<abstract>>
        - String matricula
        - String nome
        - String cpf
        - LocalDate dataAdmissao
        - double salarioBase
        - boolean ativo
        - List~RegistroPonto~ registrosPonto
        - List~Venda~ vendasRealizadas
        + calcularSalarioTotal(mes, ano)* double
        + calcularComissao(mes, ano) double
        + estaPresente() boolean
        + calcularHorasTrabalhadas(mes, ano) long
        + getPermissoes()* Set~Permissao~
        + baterPonto(tipo) void
        + getVendasRealizadas() List~Venda~
    }

    class Gerente {
        - double bonusMensal
        + calcularSalarioTotal(mes, ano) double
        + getPermissoes() Set~Permissao~
    }

    class Vendedor {
        - double percentualComissao
        + calcularSalarioTotal(mes, ano) double
        + calcularComissao(mes, ano) double
        + getPermissoes() Set~Permissao~
    }

    class Estoquista {
        - boolean habilitadoEmpilhadeira
        + podeMovimentarItemPesado() boolean
        + calcularSalarioTotal(mes, ano) double
        + getPermissoes() Set~Permissao~
    }

    class RegistroPonto {
        - String codigo
        - LocalDateTime dataHora
        - TipoRegistro tipo
        - String justificativa
        + isBatidaValida() boolean
    }

    class Permissao {
        <<enumeration>>
        APROVAR_DESCONTO
        CADASTRAR_FUNCIONARIO
        CANCELAR_VENDA
        ALTERAR_ESTOQUE
        VISUALIZAR_RELATORIOS
        REALIZAR_VENDA
        APLICAR_DESCONTO_PADRAO
        CONSULTAR_ESTOQUE
        CADASTRAR_PRODUTO
        APROVAR_PEDIDO
    }

    class TipoRegistro {
        <<enumeration>>
        ENTRADA
        SAIDA
        INTERVALO_INICIO
        INTERVALO_FIM
    }

    Funcionario <|-- Gerente
    Funcionario <|-- Vendedor
    Funcionario <|-- Estoquista
    Funcionario "1" *-- "0..*" RegistroPonto
    RegistroPonto --> TipoRegistro
    Funcionario --> Permissao

    %% ============================================
    %% NÚCLEO 2: ESTOQUE
    %% ============================================

    class ItemEstoque {
        <<abstract>>
        - String codigo
        - String titulo
        - int quantidadeAtual
        - int quantidadeMinima
        - double precoCusto
        - double precoVenda
        - LocalDate dataCadastro
        + getCategoria()* String
        + getPrazoDevolucaoDias()* int
        + precisaReposicao() boolean
        + baixarEstoque(qtd) void
        + reporEstoque(qtd) void
        + validarDados() void
    }

    class Livro {
        - String isbn
        - String autor
        - String editora
        - int edicao
        - Genero genero
        + getCategoria() String
        + getPrazoDevolucaoDias() int
        + validarIsbn() void
    }

    class Papelaria {
        - String marca
        - boolean materialEscolar
        + getCategoria() String
        + getPrazoDevolucaoDias() int
    }

    class Eletronico {
        - int garantiaMeses
        - String numeroSerie
        + getCategoria() String
        + getPrazoDevolucaoDias() int
    }

    class Genero {
        <<enumeration>>
        FICCAO
        NAO_FICCAO
        TECNICO
        DIDATICO
        INFANTIL
        BIOGRAFIA
    }

    class Fornecedor {
        - String cnpj
        - String razaoSocial
        - String telefone
        - int prazoEntregaMedioDias
        - List~ItemEstoque~ itensFornecidos
        - List~PedidoReposicao~ pedidosRealizados
        + calcularConfiabilidade() double
        + adicionarItem(item) void
        + getPrazoEntregaMedioDias() int
    }

    class PedidoReposicao {
        - String codigoPedido
        - LocalDate dataPedido
        - LocalDate dataPrevistaEntrega
        - LocalDate dataEntregaEfetiva
        - StatusPedido status
        - Map~ItemEstoque,Integer~ itensPedido
        + darEntrada() void
        + cancelar() void
        + getDataPrevistaEntrega() LocalDate
    }

    class StatusPedido {
        <<enumeration>>
        AGUARDANDO
        ENVIADO
        ENTREGUE
        CANCELADO
    }

    class Reserva {
        - String codigoReserva
        - LocalDate dataReserva
        - LocalDate dataLimite
        - StatusReserva status
        + expirar() void
        + confirmarRetirada() void
    }

    class StatusReserva {
        <<enumeration>>
        AGUARDANDO_REPOSICAO
        DISPONIVEL_PARA_RETIRADA
        RETIRADO
        EXPIRADO
    }

    ItemEstoque <|-- Livro
    ItemEstoque <|-- Papelaria
    ItemEstoque <|-- Eletronico
    Livro --> Genero
    ItemEstoque "0..*" --> "1" Fornecedor
    Fornecedor "1" --> "0..*" PedidoReposicao
    PedidoReposicao "1" --> "0..*" ItemEstoque
    PedidoReposicao --> StatusPedido
    PedidoReposicao "1" --> "1" Funcionario : funcionarioResponsavel
    Reserva "1" --> "1" ItemEstoque
    Reserva --> StatusReserva

    %% ============================================
    %% NÚCLEO 3: VENDAS
    %% ============================================

    class Cliente {
        - String cpf
        - String nome
        - String email
        - LocalDate dataCadastro
        - List~Venda~ historicoCompras
        - List~Reserva~ reservasAtivas
        + isClienteVIP() boolean
        + getHistoricoCompras() List~Venda~
        + adicionarReserva(reserva) void
    }

    class Venda {
        - String codigoVenda
        - LocalDateTime dataVenda
        - double descontoAplicado
        - String justificativaDesconto
        - StatusVenda status
        - Map~ItemEstoque,Integer~ itensVenda
        + finalizarVenda() void
        + cancelar() void
        + calcularSubtotal() double
        + calcularTotal() double
        + getDataVenda() LocalDateTime
    }

    class StatusVenda {
        <<enumeration>>
        FINALIZADA
        CANCELADA
        DEVOLVIDA_PARCIAL
        DEVOLVIDA_TOTAL
    }

    class Pagamento {
        <<abstract>>
        - double valorTotal
        - LocalDateTime dataPagamento
        - StatusPagamento status
        + processar(valor)* void
        + getMetodoPagamento()* String
    }

    class PagamentoDinheiro {
        - double valorRecebido
        - double troco
        + processar(valor) void
        + getMetodoPagamento() String
    }

    class PagamentoCartao {
        - String bandeira
        - int parcelas
        + processar(valor) void
        + getMetodoPagamento() String
    }

    class PagamentoPix {
        - String chavePix
        - String codigoTransacao
        + processar(valor) void
        + getMetodoPagamento() String
    }

    class StatusPagamento {
        <<enumeration>>
        PENDENTE
        APROVADO
        RECUSADO
    }

    class Devolucao {
        - LocalDate dataDevolucao
        - String motivo
        - double valorReembolsado
        - Map~ItemEstoque,Integer~ itensDevolvidos
        + processarDevolucao() void
        + getVendaOriginal() Venda
    }

    Venda "1" --> "1" Funcionario : funcionario
    Venda "1" --> "0..1" Cliente : cliente
    Venda "1" --> "1..*" ItemEstoque : itensVenda
    Venda "1" --> "1" Pagamento : pagamento
    Venda --> StatusVenda
    Pagamento <|-- PagamentoDinheiro
    Pagamento <|-- PagamentoCartao
    Pagamento <|-- PagamentoPix
    Pagamento --> StatusPagamento
    Cliente "1" --> "0..*" Venda : historicoCompras
    Cliente "1" --> "0..*" Reserva : reservasAtivas
    Devolucao "1" --> "1" Venda : vendaOriginal
    Devolucao "1" --> "1..*" ItemEstoque : itensDevolvidos
    Devolucao "1" --> "1" Funcionario : funcionario

    %% ============================================
    %% EXCEÇÕES
    %% ============================================

    class EstoqueInsuficienteException {
        + EstoqueInsuficienteException(item, solicitado, disponivel)
    }

    class PermissaoNegadaException {
        + PermissaoNegadaException(funcionario, permissao)
    }

    class PagamentoRecusadoException {
        + PagamentoRecusadoException(motivo)
    }

    class PrazoDevolucaoExpiradoException {
        + PrazoDevolucaoExpiradoException(item, diasPassados)
    }

    class GarantiaExpiradaException {
        + GarantiaExpiradaException(item, mesesPassados)
    }

    class BatidaPontoInvalidaException {
        + BatidaPontoInvalidaException(motivo)
    }

    class FuncionarioInativoException {
        + FuncionarioInativoException(funcionario)
    }

    class CpfDuplicadoException {
        + CpfDuplicadoException(cpf)
    }

    class IsbnInvalidoException {
        + IsbnInvalidoException(isbn)
    }

    class ReservaExpiradaException {
        + ReservaExpiradaException(reserva)
    }

    class DadosInvalidosException {
        + DadosInvalidosException(campo, motivo)
    }

    class EntidadeReferenciadaNaoEncontradaException {
        + EntidadeReferenciadaNaoEncontradaException(entidade, referencia)
    }

    class LinhaCorrompidaException {
        + LinhaCorrompidaException(arquivo, linha)
    }

    %% ============================================
    %% PERSISTÊNCIA
    %% ============================================

    class PersistenciaLivraria {
        - String DIRETORIO
        + carregarTudo() void
        + salvarTudo() void
        - carregarFornecedores() List~Fornecedor~
        - carregarItens(fornecedores) List~ItemEstoque~
        - carregarFuncionarios() List~Funcionario~
        - carregarPontos(funcionarios) List~RegistroPonto~
        - carregarClientes() List~Cliente~
        - carregarVendas(func, clientes, itens) List~Venda~
        - carregarDevolucoes(vendas, itens, func) List~Devolucao~
        - carregarReservas(itens, clientes) List~Reserva~
        - reconectarVinculos() void
        - salvarFuncionarios() void
        - salvarItens() void
        - salvarVendas() void
    }

    PersistenciaLivraria ..> Funcionario
    PersistenciaLivraria ..> ItemEstoque
    PersistenciaLivraria ..> Venda
    PersistenciaLivraria ..> Cliente
    PersistenciaLivraria ..> Fornecedor
    PersistenciaLivraria ..> PedidoReposicao
    PersistenciaLivraria ..> Devolucao
    PersistenciaLivraria ..> Reserva
    PersistenciaLivraria ..> RegistroPonto
    ```
