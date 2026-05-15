package model;

/**
 * Usuário com plano Premium (Individual ou Família).
 *
 * <p>Desfruta de reprodução em alta qualidade, sem anúncios, e com acesso
 * a todos os recursos da plataforma.</p>
 */
public class UsuarioPremium extends Usuario {

    private final String plano; // "Individual" ou "Familia"

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        this.plano = plano;
    }

    //  Implementação dos métodos abstratos

    @Override
    public void reproduzirMusica(Musica musica) {
        System.out.println("   [PREMIUM " + plano.toUpperCase() + "] Alta qualidade · Sem anúncios");
        System.out.print("  ");
        musica.reproduzir();
        historicoReproducao.add(musica);
    }

    @Override
    public String getTipoConta() {
        return "Premium " + plano;
    }

    //  Sobrescrita de Estatistico

    @Override
    public void exibirEstatisticas() {
        super.exibirEstatisticas();
        System.out.println("  Plano Det.: Premium " + plano);
    }

    //  Getter

    public String getPlano() {
        return plano;
    }
}
