import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GerenciadorEventos gerenciador = new GerenciadorEventos();

        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Cidade: ");
        String cidade = sc.nextLine();
        Usuario usuario = new Usuario(nome, email, cidade);

        int opcao;
        do {
            System.out.println("\n[1] Cadastrar Evento\n[2] Listar Eventos\n[3] Confirmar Participação\n[4] Ver Confirmados\n[5] Cancelar Participação\n[0] Sair");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do evento: ");
                    String nomeE = sc.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = sc.nextLine();
                    System.out.print("Categoria: ");
                    String categoria = sc.nextLine();
                    System.out.print("Horário (dd/MM/yyyy HH:mm): ");
                    String dataStr = sc.nextLine();
                    LocalDateTime horario = LocalDateTime.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                    System.out.print("Descrição: ");
                    String descricao = sc.nextLine();
                    gerenciador.cadastrarEvento(new Evento(nomeE, endereco, categoria, horario, descricao));
                    break;
                case 2:
                    int i = 0;
                    for (Evento e : gerenciador.listarEventos()) {
                        System.out.println("[" + i + "] " + e);
                        i++;
                    }
                    break;
                case 3:
                    System.out.print("Informe o índice do evento para participar: ");
                    int idx = sc.nextInt();
                    gerenciador.confirmarParticipacao(idx);
                    break;
                case 4:
                    i = 0;
                    for (Evento e : gerenciador.listarConfirmados()) {
                        System.out.println("[" + i + "] " + e);
                        i++;
                    }
                    break;
                case 5:
                    System.out.print("Informe o índice da confirmação a cancelar: ");
                    int idc = sc.nextInt();
                    gerenciador.cancelarParticipacao(idc);
                    break;
            }
        } while (opcao != 0);

        System.out.println("Sistema encerrado.");
        sc.close();
    }
}
