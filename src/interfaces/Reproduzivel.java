package interfaces;

/**
 * Interface que define o contrato de reprodução de conteúdo musical.
 * Qualquer entidade capaz de ser reproduzida deve implementar esta interface.
 */
public interface Reproduzivel {

    /**
     * Reproduz o conteúdo (música, playlist, etc.).
     */
    void reproduzir();

    /**
     * Pausa a reprodução.
     */
    void pausar();

    /**
     * Retorna o nome/título do conteúdo reproduzível.
     */
    String getNome();
}
