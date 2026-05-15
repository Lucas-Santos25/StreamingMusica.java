package interfaces;

/**
 * Interface que define o contrato para exibição de estatísticas.
 * Implementada por classes que expõem dados de uso do sistema.
 */
public interface Estatistico {

    /**
     * Exibe as estatísticas relevantes da entidade.
     */
    void exibirEstatisticas();

    /**
     * Retorna o total de reproduções registradas.
     */
    int getTotalReproducoes();
}
