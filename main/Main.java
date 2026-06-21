import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import model.funcionario.Vendedor;
import model.item.estoque.Eletronico;
import model.item.estoque.ItemEstoque;
import model.item.estoque.Livro;
import model.item.estoque.Papelaria;
import model.venda.Cliente;
import model.venda.Pagamento;
import model.venda.PagamentoDinheiro;
import model.venda.PagamentoPix;
import model.venda.Venda;
import model.venda.VendaRepository;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Cadastro do cliente ===");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine().trim();
        System.out.print("Nome: ");
        String nomeCliente = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        Cliente cliente = new Cliente(cpf, nomeCliente, email, LocalDate.now());

        System.out.println("\n=== Vendedor Escolhido ===");
        Vendedor vendedor = gerarVendedorAutomatico();
        System.out.println("Vendedor: " + vendedor.getNome() + " (Matrícula: " + vendedor.getMatricula() + ", CPF: " + vendedor.getCpf() + ")\n");
        
        List<ItemEstoque> catalogo = new ArrayList<>();

        Livro livro = new Livro();
        livro.setCodigo("L001");
        livro.setTitulo("Senhor dos Anéis");
        livro.setPrecoVenda(100.00);
        livro.setQuantidadeAtual(10);
        livro.setQuantidadeMinima(2);
        livro.setIsbn("978-1234567890");
        livro.setAutor("Tolkien");
        livro.setEditora("HarperCollins");
        livro.setEdicao(1);
        livro.setGenero("Fantasia");
        catalogo.add(livro);

        Eletronico eletronico = new Eletronico(12, "SN12345");
        eletronico.setCodigo("E001");
        eletronico.setTitulo("PC GAMER BOLADÃO XEON TOP10 DAS BALADINHA");
        eletronico.setPrecoVenda(1500.00);
        eletronico.setQuantidadeAtual(5);
        eletronico.setQuantidadeMinima(1);
        catalogo.add(eletronico);

        Papelaria papelaria = new Papelaria();
        papelaria.setCodigo("P001");
        papelaria.setTitulo("Caderno da Barbie");
        papelaria.setPrecoVenda(25.00);
        papelaria.setQuantidadeAtual(20);
        papelaria.setQuantidadeMinima(5);
        papelaria.setMarca("Tilibra");
        papelaria.setMaterialEscolar(true);
        catalogo.add(papelaria);

        Venda venda = new Venda("V0001", cliente, vendedor);

        System.out.println("\n=== Produtos disponíveis ===");
        for (int i = 0; i < catalogo.size(); i++) {
            ItemEstoque item = catalogo.get(i);
            System.out.printf("%d - %s (R$ %.2f)%n", i + 1, item.getTitulo(), item.getPrecoVenda());
        }

        String continuar = "s";
        while (continuar.equalsIgnoreCase("s")) {
            System.out.print("Escolha o número do produto: ");
            int escolha = Integer.parseInt(scanner.nextLine().trim());
            if (escolha < 1 || escolha > catalogo.size()) {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }

            ItemEstoque selecionado = catalogo.get(escolha - 1);
            System.out.print("Quantidade: ");
            int quantidade = Integer.parseInt(scanner.nextLine().trim());
            if (quantidade <= 0) {
                System.out.println("Quantidade inválida. Tente novamente.");
                continue;
            }

            venda.adicionarItem(selecionado, quantidade);
            System.out.print("Adicionar outro produto? (s/n): ");
            continuar = scanner.nextLine().trim();
        }

        System.out.print("Deseja aplicar desconto? (s/n): ");
        String aplicarDesconto = scanner.nextLine().trim();
        if (aplicarDesconto.equalsIgnoreCase("s")) {
            System.out.print("Valor do desconto: ");
            double desconto = Double.parseDouble(scanner.nextLine().trim());
            venda.aplicarDesconto(desconto);
        }

        System.out.println("\nEscolha a forma de pagamento:");
        System.out.println("1 - Dinheiro");
        System.out.println("2 - PIX");
        System.out.print("Opção: ");
        int opcaoPagamento = Integer.parseInt(scanner.nextLine().trim());

        Pagamento pagamento;
        if (opcaoPagamento == 2) {
            pagamento = new PagamentoPix();
            System.out.println("PIX do estabelecimento: " + PagamentoPix.getChavePix());
        } else {
            pagamento = new PagamentoDinheiro();
        }

        String confirmacaoPagamento;
        do {
            System.out.print("Pagamento efetuado? (s/n): ");
            confirmacaoPagamento = scanner.nextLine().trim();
        } while (!confirmacaoPagamento.equalsIgnoreCase("s") && !confirmacaoPagamento.equalsIgnoreCase("n"));

        if (confirmacaoPagamento.equalsIgnoreCase("n")) {
            System.out.println("Pagamento não confirmado. Venda não finalizada.");
            scanner.close();
            return;
        }

        pagamento.processar(venda.calcularTotal());
        venda.setPagamento(pagamento);
        venda.finalizar();
        VendaRepository.salvarVenda(venda);
        scanner.close();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("\nVenda realizada com sucesso:");
        System.out.println("Código de venda: " + venda.getCodigoVenda());
        System.out.println("Data: " + venda.getDataVenda().format(formatter));
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Funcionário: " + vendedor.getNome());
        System.out.println("Total: R$ " + String.format("%.2f", venda.calcularTotal()));
        System.out.println("Pagamento: " + pagamento.getMetodoPagamento());

        for (Map.Entry<ItemEstoque, Integer> entry : venda.getItensVenda().entrySet()) {
            ItemEstoque item = entry.getKey();
            if (item instanceof Livro) {
                Livro livroComprado = (Livro) item;
                System.out.println("\nLivro comprado: " + livroComprado.getTitulo());
                System.out.println("Gênero: " + livroComprado.getGenero());
                System.out.println("Autor: " + livroComprado.getAutor());
                System.out.println("Editora: " + livroComprado.getEditora());
            }
        }

        System.out.println("Status da venda: " + venda.getStatus());
    }

    private static Vendedor gerarVendedorAutomatico() {
        String[] nomes = {"Carlos Souza", "Marina Alves", "Pedro Lima", "Fernanda Costa", "João Pereira"};
        Random random = new Random();
        String nome = nomes[random.nextInt(nomes.length)];
        String matricula = "V" + (1000 + random.nextInt(9000));
        String cpf = String.format("%011d", random.nextInt(1_000_000_000));
        return new Vendedor(matricula, nome, cpf);
    }
}
