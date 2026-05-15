package model;

/**
 * Usuário com plano gratuito (Free).
 *
 * <p>A reprodução de músicas é acompanhada de anúncios, simulando a
 * experiência de um serviço de streaming freemium.</p>
 */
public class UsuarioFree extends Usuario {

    private int totalAnuncios;

    public UsuarioFree(String nome, String email) {
        super(nome, email);
        this.totalAnuncios = 0;
    }

    //  Implementação dos métodos abstratos

    @Override
    public void reproduzirMusica(Musica musica) {
        totalAnuncios++;
        System.out.println(" [ANÚNCIO] Exibindo propaganda antes da música...");
        System.out.print("  ");
        musica.reproduzir();
        historicoReproducao.add(musica);
    }

    @Override
    public String getTipoConta() {
        return "Free";
    }

    //  Sobrescrita de Estatistico

    @Override
    public void exibirEstatisticas() {
        super.exibirEstatisticas();
        System.out.println("  Anúncios  : " + totalAnuncios + " exibido(s)");
    }

    //  Getter

    public int getTotalAnuncios() {
        return totalAnuncios;
    }
}
