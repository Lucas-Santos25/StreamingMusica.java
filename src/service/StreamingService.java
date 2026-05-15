package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Serviço central do sistema de streaming.
 *
 * <p>Encapsula toda a lógica de negócio: gerenciamento de usuários,
 * biblioteca de músicas e coleta de estatísticas globais. Segue o
 * princípio de responsabilidade única (SRP) — a classe principal
 * ({@code StreamingMusica}) fica responsável apenas pela interface com
 * o usuário.</p>
 */
public class StreamingService {

    private final List<Usuario> usuarios;
    private final List<Musica> biblioteca;

    private int totalReproducoesFree;
    private int totalReproducoesPremium;
    private int totalAnuncios;

    public StreamingService() {
        this.usuarios = new ArrayList<>();
        this.biblioteca = new ArrayList<>();
        this.totalReproducoesFree = 0;
        this.totalReproducoesPremium = 0;
        this.totalAnuncios = 0;
        popularBiblioteca();
    }

    // ── Biblioteca ───────────────────────────────────────────────────────────

    private void popularBiblioteca() {
        biblioteca.add(new Musica("Bohemian Rhapsody",  "Queen",           354));
        biblioteca.add(new Musica("Imagine",             "John Lennon",     183));
        biblioteca.add(new Musica("Billie Jean",         "Michael Jackson", 294));
        biblioteca.add(new Musica("Hotel California",    "Eagles",          391));
        biblioteca.add(new Musica("Smells Like Teen Spirit","Nirvana",      301));
        biblioteca.add(new Musica("Shape of You",        "Ed Sheeran",      234));
        biblioteca.add(new Musica("Blinding Lights",     "The Weeknd",      200));
        biblioteca.add(new Musica("Levitating",          "Dua Lipa",        203));
        biblioteca.add(new Musica("Bad Guy",             "Billie Eilish",   194));
        biblioteca.add(new Musica("Watermelon Sugar",    "Harry Styles",    174));
        biblioteca.add(new Musica("Stay With Me",        "Sam Smith",       172));
        biblioteca.add(new Musica("Someone Like You",    "Adele",           285));
    }

    public List<Musica> getBiblioteca() {
        return biblioteca;
    }

    //  Usuários

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public boolean existeEmail(String email) {
        return usuarios.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    //  Reprodução com contagem de estatísticas

    public void reproduzirPlaylist(Usuario usuario, PlaylistAutomatica playlist) {
        if (usuario instanceof UsuarioFree) {
            totalReproducoesFree++;
            totalAnuncios++;
        } else {
            totalReproducoesPremium++;
        }
        playlist.reproduzir();
    }

    //  Estatísticas globais

    public void exibirEstatisticasGlobais() {
        long freeCount    = usuarios.stream().filter(u -> u instanceof UsuarioFree).count();
        long premiumCount = usuarios.stream().filter(u -> u instanceof UsuarioPremium).count();
        int totalRep      = totalReproducoesFree + totalReproducoesPremium;

        System.out.println("\n======================================");
        System.out.println("|     ESTATÍSTICAS DO SISTEMA        |");
        System.out.println("=======================================");
        System.out.println("  Total de usuários : " + usuarios.size());
        System.out.println("  -- Free           : " + freeCount);
        System.out.println("  -- Premium        : " + premiumCount);
        System.out.println();
        System.out.println("  Reproduções totais: " + totalRep);
        if (totalRep > 0) {
            int pctFree    = totalReproducoesFree    * 100 / totalRep;
            int pctPremium = totalReproducoesPremium * 100 / totalRep;
            System.out.println("  -- Free           : " + totalReproducoesFree    + " (" + pctFree    + "%)");
            System.out.println("  -- Premium        : " + totalReproducoesPremium + " (" + pctPremium + "%)");
        }
        System.out.println();
        System.out.println("  Anúncios exibidos : " + totalAnuncios);
        System.out.println("  Músicas na lib.   : " + biblioteca.size());
    }
}
