import java.util.ArrayList;

public class UsuarioPremium extends Usuario {
    private String tipoPlano;
    private ArrayList<Musica> musicasBaixadas;

    public UsuarioPremium(String nome, String email, String tipoPlano) {
        super(nome, email);
        this.tipoPlano = tipoPlano;
        this.musicasBaixadas = new ArrayList<>();
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        System.out.println("\n💎 Reproduzindo em ALTA QUALIDADE: " + musica.getTitulo());
        historicoReproducao.add(musica); // Adiciona ao histórico herdado do pai
    }

    public void baixarMusica(Musica musica) {
        musicasBaixadas.add(musica);
        System.out.println("⬇️ '" + musica.getTitulo() + "' foi baixada com sucesso!");
    }

    public void listarMusicasBaixadas() {
        System.out.println("\n--- MÚSICAS BAIXADAS (Modo Offline) ---");
        for (Musica m : musicasBaixadas) {
            m.exibir();
        }
    }
}