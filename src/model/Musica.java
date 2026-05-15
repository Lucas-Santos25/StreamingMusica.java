package model;

import interfaces.Reproduzivel;

/**
 * Representa uma música no sistema de streaming.
 * Implementa Reproduzivel para garantir o contrato de reprodução.
 */
public class Musica implements Reproduzivel {

    private final String titulo;
    private final String artista;
    private final int duracaoSegundos;
    private boolean emReproducao;

    public Musica(String titulo, String artista, int duracaoSegundos) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracaoSegundos = duracaoSegundos;
        this.emReproducao = false;
    }

    //  Implementação de Reproduzivel

    @Override
    public void reproduzir() {
        this.emReproducao = true;
        System.out.printf("  ▶ %-22s | Artista: %-15s | %s%n",
                titulo, artista, formatarDuracao());
    }

    @Override
    public void pausar() {
        this.emReproducao = false;
        System.out.println("  ⏸ Pausado: " + titulo);
    }

    @Override
    public String getNome() {
        return titulo;
    }

    //  Getters

    public String getTitulo()  { return titulo; }
    public String getArtista() { return artista; }
    public int getDuracao()    { return duracaoSegundos; }

    // Utilitários

    private String formatarDuracao() {
        int min = duracaoSegundos / 60;
        int seg = duracaoSegundos % 60;
        return String.format("%d:%02d", min, seg);
    }

    @Override
    public String toString() {
        return String.format("Musica{titulo='%s', artista='%s', duracao=%s}",
                titulo, artista, formatarDuracao());
    }
}
