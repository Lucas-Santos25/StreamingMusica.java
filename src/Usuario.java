import java.util.ArrayList;

public class Usuario {
    protected String nome;
    protected String email;
    protected ArrayList<Musica> historicoReproducao;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.historicoReproducao = new ArrayList<>();
    }

    public void reproduzirMusica(Musica musica) {
        System.out.println("\n🎵 Reproduzindo: " + musica.getTitulo());
        historicoReproducao.add(musica);
    }

    public void exibirHistorico() {
        System.out.println("\n--- HISTÓRICO DE REPRODUÇÃO ---");
        if (historicoReproducao.isEmpty()) {
            System.out.println("Histórico vazio.");
        } else {
            for (Musica m : historicoReproducao) {
                m.exibir();
            }
        }
    }
}