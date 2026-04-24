public class UsuarioFree extends Usuario {
    private int contadorReproducoes;

    public UsuarioFree(String nome, String email) {
        super(nome, email); // super chama o construtor do pai (Usuario)
        this.contadorReproducoes = 0;
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        contadorReproducoes++;

        // Regra do Checkpoint: A cada 3 músicas, exibe anúncio
        if (contadorReproducoes % 3 == 0) {
            exibirAnuncio();
        }

        super.reproduzirMusica(musica); // Usa o método original do pai
    }

    private void exibirAnuncio() {
        System.out.println("\n📢 [ANÚNCIO] Assine o Premium para ouvir sem interrupções!");
    }
}