package model;

import interfaces.Gerenciavel;
import interfaces.Reproduzivel;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe ABSTRATA que representa uma playlist genérica.
 *
 * <p>Define o contrato e comportamento comum a todos os tipos de playlist.
 * Subclasses devem implementar {@link #reproduzir()} de acordo com seu
 * critério específico (ex.: ordem manual, automática, aleatória).</p>
 *
 * <p>Implementa {@link Reproduzivel} e {@link Gerenciavel} para garantir
 * que toda playlist possa ser reproduzida e gerenciada.</p>
 */
public abstract class Playlist implements Reproduzivel, Gerenciavel {

    protected final String nome;
    protected final List<Musica> musicas;

    public Playlist(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    // Implementação de Gerenciavel

    @Override
    public void adicionarMusica(Musica musica) {
        musicas.add(musica);
    }

    @Override
    public boolean removerMusica(String titulo) {
        return musicas.removeIf(m -> m.getTitulo().equalsIgnoreCase(titulo));
    }

    @Override
    public int getTotalMusicas() {
        return musicas.size();
    }

    // Implementação de Reproduzivel

    @Override
    public void pausar() {
        System.out.println("  ⏸ Playlist '" + nome + "' pausada.");
    }

    @Override
    public String getNome() {
        return nome;
    }

    //  Método abstrato

    /**
     * Reproduz a playlist de acordo com a lógica específica da subclasse.
     * Cada tipo de playlist define sua própria forma de reprodução.
     */
    @Override
    public abstract void reproduzir();

    // Getters e utilitários

    public List<Musica> getMusicas() {
        return musicas;
    }

    protected void exibirCabecalho() {
        System.out.println("\n=======================================");
        System.out.printf ("|   %-34s|%n", nome);
        System.out.printf ("|  %d música(s) na playlist%-14s|%n", musicas.size(), "");
        System.out.println("=========================================");
    }
}
