import java.util.ArrayList;
import java.util.Scanner;

class Musica {
    String titulo;
    String artista;
    int duracao;
    String genero;

    void exibirDados() {
        int min = duracao / 60;
        int seg = duracao % 60;
        System.out.printf("Título: %-20s | Artista: %-15s | Gênero: %-10s | Duração: %d:%02d\n",
                titulo, artista, genero, min, seg);
    }
}

class Playlist {
    String nome;
    ArrayList<Musica> musicas = new ArrayList<>();

    void adicionarMusica(Musica m) {
        musicas.add(m);
    }

    void removerMusica(int indice) {
        if (indice >= 0 && indice < musicas.size()) {
            musicas.remove(indice);
            System.out.println("Música removida da playlist!");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    void listarMusicas() {
        System.out.println("\n--- Músicas na Playlist: " + nome + " ---");
        if (musicas.isEmpty()) {
            System.out.println("Esta playlist está vazia.");
        } else {
            for (int i = 0; i < musicas.size(); i++) {
                System.out.print("[" + i + "] ");
                musicas.get(i).exibirDados();
            }
        }
    }
}

public class StreamingMusica {
    static ArrayList<Musica> musica = new ArrayList<>();
    static ArrayList<Playlist> playlists = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        adicionarMusicasTeste();

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcao();
            processarOpcaoPrincipal(opcao);
        } while (opcao != 0);

        System.out.println("\n🎵 Sistema encerrado. Até logo! 🎵");
    }

    static void exibirMenuPrincipal() {
        System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música");
        System.out.println("4. Criar playlist");
        System.out.println("5. Gerenciar playlists");
        System.out.println("6. Exibir estatísticas");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    static void processarOpcaoPrincipal(int opcao) {
        switch (opcao) {
            case 1 -> cadastrarMusica();
            case 2 -> listarMusica();
            case 3 -> buscarMusica();
            case 4 -> criarPlaylist();
            case 5 -> gerenciarPlaylists();
            case 6 -> exibirEstatisticas();
            case 0 -> System.out.println("Saindo...");
            default -> System.out.println("Opção inválida!");
        }
    }

    static void gerenciarPlaylists() {
        int opcaoSub;
        do {
            System.out.println("\n=== GERENCIAR PLAYLISTS ===");
            System.out.println("1. Listar minhas playlists");
            System.out.println("2. Adicionar música a uma playlist");
            System.out.println("3. Remover música de uma playlist");
            System.out.println("4. Exibir detalhes de uma playlist");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcaoSub = lerOpcao();

            switch (opcaoSub) {
                case 1 -> listarPlaylists();
                case 2 -> adicionarMusicaPlaylist();
                case 3 -> removerMusicaPlaylist();
                case 4 -> exibirDetalhesPlaylist();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcaoSub != 0);
    }

    static void cadastrarMusica() {
        Musica nova = new Musica();
        System.out.print("Título: "); nova.titulo = scanner.nextLine();
        System.out.print("Artista: "); nova.artista = scanner.nextLine();
        System.out.print("Duração (seg): "); nova.duracao = Integer.parseInt(scanner.nextLine());
        System.out.print("Gênero: "); nova.genero = scanner.nextLine();
        musica.add(nova);
    }

    static void listarMusica() {
        System.out.println("\n--- Lista Completa ---");
        for (int i = 0; i < musica.size(); i++) {
            System.out.print("[" + i + "] ");
            musica.get(i).exibirDados();
        }
    }

    static void buscarMusica() {
        System.out.print("Buscar título: ");
        String busca = scanner.nextLine().toLowerCase();
        for (Musica m : musica) {
            if (m.titulo.toLowerCase().contains(busca)) m.exibirDados();
        }
    }

    static void criarPlaylist() {
        Playlist p = new Playlist();
        System.out.print("Nome da playlist: ");
        p.nome = scanner.nextLine();
        playlists.add(p);
        System.out.println("Playlist criada!");
    }

    static void listarPlaylists() {
        System.out.println("\n--- MINHAS PLAYLISTS ---");
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist cadastrada.");
            return;
        }
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println(i + ". " + playlists.get(i).nome);
        }
    }

    static void adicionarMusicaPlaylist() {
        listarPlaylists();
        if (playlists.isEmpty()) return;
        System.out.print("Escolha a playlist (Indice): ");
        int iP = Integer.parseInt(scanner.nextLine());

        listarMusica();
        System.out.print("Escolha uma musica (Indice): ");
        int iM = Integer.parseInt(scanner.nextLine());

        if (iP >= 0 && iP < playlists.size() && iM >= 0 && iM < musica.size()) {
            playlists.get(iP).adicionarMusica(musica.get(iM));
            System.out.println("Música adicionada!");
        }
    }

    static void removerMusicaPlaylist() {
        listarPlaylists();
        if (playlists.isEmpty()) return;
        System.out.print("Escolha a playlist (Indice): ");
        int iP = Integer.parseInt(scanner.nextLine());

        Playlist p = playlists.get(iP);
        p.listarMusicas();
        if (p.musicas.isEmpty()) return;

        System.out.print("Escolha o índice da música para remover: ");
        int iM = Integer.parseInt(scanner.nextLine());
        p.removerMusica(iM);
    }

    static void exibirDetalhesPlaylist() {
        listarPlaylists();
        if (playlists.isEmpty()) return;
        System.out.print("Escolha a playlist: ");
        int iP = Integer.parseInt(scanner.nextLine());
        playlists.get(iP).listarMusicas();
    }

    static void exibirEstatisticas() {
        int total = 0;
        for (Musica m : musica) total += m.duracao;
        System.out.println("\nMúsicas encontradas: " + musica.size());
        System.out.println("Playlists: " + playlists.size());
        System.out.println("Tempo total (seg): " + total);
    }

    static int lerOpcao() {
        try { return Integer.parseInt(scanner.nextLine()); }
        catch (Exception e) { return -1; }
    }

    static void adicionarMusicasTeste() {
        Musica m1 = new Musica(); m1.titulo = "Bohemian Rhapsody"; m1.artista = "Queen"; m1.duracao = 354; m1.genero = "Rock";
        musica.add(m1);
        Musica m2 = new Musica(); m2.titulo = "Billie Jean"; m2.artista = "Michael Jackson"; m2.duracao = 293; m2.genero = "Pop";
        musica.add(m2);
    }

}