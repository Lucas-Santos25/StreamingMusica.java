package interfaces;

/**
 * Interface que define operações de gerenciamento de playlists.
 * Implementada por classes que suportam adição e remoção de músicas.
 */
public interface Gerenciavel {

    /**
     * Adiciona uma música ao conteúdo gerenciável.
     *
     * @param musica a música a ser adicionada
     */
    void adicionarMusica(model.Musica musica);

    /**
     * Remove uma música pelo título.
     *
     * @param titulo o título da música a remover
     * @return true se removida com sucesso, false caso contrário
     */
    boolean removerMusica(String titulo);

    /**
     * Retorna o total de músicas gerenciadas.
     */
    int getTotalMusicas();
}
