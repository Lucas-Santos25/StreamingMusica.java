package main;

import model.*;
import service.StreamingService;

import java.util.Scanner;

/**
 * Ponto de entrada do sistema de Streaming de Música.
 *
 * <p>Responsável exclusivamente pela interface com o usuário (menus, leitura
 * de entradas e exibição de saídas). Toda a lógica de negócio é delegada
 * ao {@link StreamingService}.</p>
 */
public class StreamingMusica {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StreamingService service = new StreamingService();
    private static Usuario usuarioLogado = null;

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("|     BEM-VINDO AO STREAMINGMUSIC   |");
        System.out.println("=====================================");

        int op;
        do {
            exibirMenuPrincipal();
            op = lerInt();

            switch (op) {
                case 1 -> criarUsuario();
                case 2 -> realizarLogin();
                case 3 -> service.exibirEstatisticasGlobais();
                case 0 -> System.out.println("\n Até logo!");
                default -> System.out.println("    Opção inválida.");
            }
        } while (op != 0);
    }

    //  Menus

    private static void exibirMenuPrincipal() {
        System.out.println("\n=================");
        System.out.println("  MENU PRINCIPAL");
        System.out.println("====================");
        System.out.println("  1. Criar novo usuário");
        System.out.println("  2. Login");
        System.out.println("  3. Estatísticas do sistema");
        System.out.println("  0. Sair");
        System.out.print("\n  Escolha: ");
    }

    private static void menuPlaylists() {
        int op;
        do {
            System.out.println("\n=======================================");
            System.out.printf ("  Logado como: %s (%s)%n",
                    usuarioLogado.getNome(), usuarioLogado.getTipoConta());
            System.out.println("========================================");
            System.out.println("  1. Top 10 Mais Tocadas");
            System.out.println("  2. Recomendadas para Você");
            System.out.println("  3. Adicionadas Recentemente");
            System.out.println("  4. Ver minhas estatísticas");
            System.out.println("  0. Logout");
            System.out.print("\n  Escolha: ");
            op = lerInt();

            switch (op) {
                case 1 -> gerarEReproduzir("Top 10 Mais Tocadas",      "Mais reproduzidas globalmente");
                case 2 -> gerarEReproduzir("Recomendadas para Você",   "Baseado no seu histórico");
                case 3 -> gerarEReproduzir("Adicionadas Recentemente", "Lançamentos recentes");
                case 4 -> {
                    System.out.println();
                    usuarioLogado.exibirEstatisticas();
                }
                case 0 -> {
                    usuarioLogado = null;
                    System.out.println("   Logout realizado.");
                }
                default -> System.out.println("    Opção inválida.");
            }
        } while (op != 0);
    }

    //  Ações

    private static void criarUsuario() {
        System.out.println("\n Criar Usuário ");
        System.out.print("  Nome  : ");
        String nome = scanner.nextLine().trim();

        String email;
        do {
            System.out.print("  Email : ");
            email = scanner.nextLine().trim();
            if (service.existeEmail(email)) {
                System.out.println("    Email já cadastrado. Tente outro.");
            }
        } while (service.existeEmail(email));

        System.out.println("\n  Tipo de conta:");
        System.out.println("  1. Free");
        System.out.println("  2. Premium Individual");
        System.out.println("  3. Premium Família");
        System.out.print("  Escolha: ");
        int tipo = lerInt();

        Usuario novoUsuario = switch (tipo) {
            case 2  -> new UsuarioPremium(nome, email, "Individual");
            case 3  -> new UsuarioPremium(nome, email, "Familia");
            default -> new UsuarioFree(nome, email);
        };

        service.adicionarUsuario(novoUsuario);
        System.out.println("   Usuário '" + nome + "' criado com sucesso! (" + novoUsuario.getTipoConta() + ")");
    }

    private static void realizarLogin() {
        if (service.getUsuarios().isEmpty()) {
            System.out.println("    Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("\nLogin ");
        System.out.println("  Usuários cadastrados:");
        var usuarios = service.getUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.printf("  %d. %-20s (%s)%n",
                    i + 1, usuarios.get(i).getNome(), usuarios.get(i).getTipoConta());
        }

        System.out.print("\n  Escolha o usuário: ");
        int index = lerInt() - 1;

        if (index < 0 || index >= usuarios.size()) {
            System.out.println("    Seleção inválida.");
            return;
        }

        usuarioLogado = usuarios.get(index);
        System.out.println("   Bem-vindo, " + usuarioLogado.getNome() + "!");
        menuPlaylists();
    }

    private static void gerarEReproduzir(String nome, String criterio) {
        System.out.println("\n   Gerando playlist \"" + nome + "\"...");
        PlaylistAutomatica pa = new PlaylistAutomatica(nome, criterio);
        pa.atualizar(service.getBiblioteca());
        service.reproduzirPlaylist(usuarioLogado, pa);
    }

    //  Utilitário de leitura segura

    private static int lerInt() {
        try {
            String linha = scanner.nextLine();
            return Integer.parseInt(linha.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
