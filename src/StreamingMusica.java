import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Musica> bibliotecaGlobal = new ArrayList<>();

    public static void main(String[] args) {
        popularBiblioteca();

        System.out.println("=== BEM-VINDO AO STREAMING ===");
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();

        System.out.println("\nEscolha o tipo de conta:");
        System.out.println("1. Free (Gratuito)");
        System.out.println("2. Premium (Pago)");
        System.out.print("Escolha: ");
        int tipo = Integer.parseInt(scanner.nextLine());

        if (tipo == 1) {
            UsuarioFree user = new UsuarioFree(nome, email);
            System.out.println("✅ Conta Free criada!");
            menuFree(user);
        } else {
            System.out.print("\nEscolha o plano Premium (Mensal/Anual): ");
            String plano = scanner.nextLine();

            UsuarioPremium user = new UsuarioPremium(nome, email, plano);
            System.out.println("✅ Conta Premium criada!");
            menuPremium(user);
        }
    }

    static void menuFree(UsuarioFree u) {
        int op;
        do {
            System.out.println("\n--- MENU FREE ---");
            System.out.println("1. Reproduzir música");
            System.out.println("2. Ver histórico");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            op = Integer.parseInt(scanner.nextLine());

            if (op == 1) u.reproduzirMusica(bibliotecaGlobal.get(0));
            if (op == 2) u.exibirHistorico();
        } while (op != 0);
    }

    static void menuPremium(UsuarioPremium u) {
        int op;
        do {
            System.out.println("\n--- MENU PREMIUM ---");
            System.out.println("1. Reproduzir (Alta Qualidade)");
            System.out.println("2. Ver histórico");
            System.out.println("3. Baixar música");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            op = Integer.parseInt(scanner.nextLine());

            if (op == 1) u.reproduzirMusica(bibliotecaGlobal.get(1));
            if (op == 2) u.exibirHistorico();
            if (op == 3) u.baixarMusica(bibliotecaGlobal.get(1));
        } while (op != 0);
    }

    static void popularBiblioteca() {
        bibliotecaGlobal.add(new Musica("Bohemian Rhapsody", "Queen", 354));
        bibliotecaGlobal.add(new Musica("Imagine", "John Lennon", 183));
    }
}