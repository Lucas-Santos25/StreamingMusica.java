package model;

import java.util.List;

/**
 * Playlist gerada automaticamente pelo sistema com base em um critério.
 *
 * <p>Estende {@link Playlist} e implementa {@link #reproduzir()} exibindo
 * o critério de geração antes de listar as músicas.</p>
 */
public class PlaylistAutomatica extends Playlist {

    private final String criterio;

    public PlaylistAutomatica(String nome, String criterio) {
        super(nome);
        this.criterio = criterio;
    }

    /**
     * Atualiza o conteúdo da playlist a partir da biblioteca global,
     * simulando um algoritmo de recomendação/seleção.
     *
     * @param biblioteca lista de músicas disponíveis na plataforma
     */
    public void atualizar(List<Musica> biblioteca) {
        this.musicas.clear();
        // Limita a 10 músicas para simular curadoria
        int limite = Math.min(10, biblioteca.size());
        for (int i = 0; i < limite; i++) {
            this.musicas.add(biblioteca.get(i));
        }
        System.out.println("   Playlist gerada com " + musicas.size() + " música(s)!");
    }

    // Implementação do método abstrato

    @Override
    public void reproduzir() {
        exibirCabecalho();
        System.out.println("  Critério: " + criterio);
        System.out.println();
        if (musicas.isEmpty()) {
            System.out.println("    Nenhuma música na playlist.");
            return;
        }
        for (Musica m : musicas) {
            m.reproduzir();
        }
    }

    public String getCriterio() {
        return criterio;
    }
}
