public class Musica {
    private String titulo;
    private String artista;
    private int duracao;

    public Musica(String titulo, String artista, int duracao) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracao = duracao;
    }

    public String getTitulo() { return titulo; }

    public void exibir() {
        System.out.printf("Musica: %-20s | Artista: %-15s\n", titulo, artista);
    }
}
