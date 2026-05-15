package model;

import interfaces.Estatistico;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe ABSTRATA que representa um usuário genérico do sistema de streaming.
 *
 * <p>Define atributos e comportamentos comuns a todos os tipos de usuário.
 * Subclasses devem implementar {@link #reproduzirMusica(Musica)} com as
 * regras específicas do seu plano (ex.: anúncios para Free, alta qualidade
 * para Premium).</p>
 *
 * <p>Implementa {@link Estatistico} para que cada usuário exponha seus
 * dados de uso de forma padronizada.</p>
 */
public abstract class Usuario implements Estatistico {

    protected final String nome;
    protected final String email;
    protected final List<Musica> historicoReproducao;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.historicoReproducao = new ArrayList<>();
    }

    //  Método abstrato

    /**
     * Reproduz uma música aplicando as regras do plano do usuário.
     * Cada subclasse define sua experiência de reprodução.
     *
     * @param musica a música a ser reproduzida
     */
    public abstract void reproduzirMusica(Musica musica);

    /**
     * Retorna o tipo de conta do usuário (ex.: "Free", "Premium Individual").
     */
    public abstract String getTipoConta();

    // Implementação de Estatistico

    @Override
    public int getTotalReproducoes() {
        return historicoReproducao.size();
    }

    @Override
    public void exibirEstatisticas() {
        System.out.println("  Usuário   : " + nome);
        System.out.println("  Email     : " + email);
        System.out.println("  Plano     : " + getTipoConta());
        System.out.println("  Histórico : " + historicoReproducao.size() + " música(s) reproduzida(s)");
    }

    //  Getters

    public String getNome()  { return nome; }
    public String getEmail() { return email; }

    public List<Musica> getHistoricoReproducao() {
        return historicoReproducao;
    }

    @Override
    public String toString() {
        return String.format("Usuario{nome='%s', tipo='%s'}", nome, getTipoConta());
    }
}
