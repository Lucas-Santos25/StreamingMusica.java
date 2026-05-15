# 🎵 StreamingMusic — Sistema de Streaming de Música

> Projeto Java orientado a objetos que simula uma plataforma de streaming musical com diferentes planos de usuário, playlists automáticas e estatísticas de uso.

---

## 🎯 Objetivos de Aprendizagem

Neste checkpoint você irá praticar:

- ✅ **Criar e implementar interfaces** — `Reproduzivel`, `Gerenciavel` e `Estatistico` definem contratos claros para todo o sistema
- ✅ **Organizar código em pacotes profissionais** — estrutura dividida em `model`, `service`, `interfaces` e `main`
- ✅ **Finalizar sistema completo e funcional** — menu interativo com criação de usuários, login, playlists e estatísticas
- ✅ **Aplicar código limpo e boas práticas** — nomes expressivos, métodos curtos, Javadoc, SRP e encapsulamento
- ✅ **Criar README.md do projeto** — você está lendo ele agora!
- ✅ **Demonstrar domínio completo de POO** — herança, polimorfismo, abstração e interfaces aplicados em conjunto

---

## 📁 Estrutura de Pacotes

```
src/
├── interfaces/
│   ├── Reproduzivel.java     # Contrato: reproduzir() e pausar()
│   ├── Gerenciavel.java      # Contrato: adicionar/remover músicas
│   └── Estatistico.java      # Contrato: exibir estatísticas de uso
│
├── model/
│   ├── Musica.java           # Entidade: título, artista, duração
│   ├── Playlist.java         # ABSTRATA: base para todos os tipos de playlist
│   ├── PlaylistAutomatica.java  # Gerada automaticamente por critério
│   ├── Usuario.java          # ABSTRATA: base para todos os tipos de usuário
│   ├── UsuarioFree.java      # Reprodução com anúncios
│   └── UsuarioPremium.java   # Reprodução em alta qualidade, sem anúncios
│
├── service/
│   └── StreamingService.java # Lógica de negócio: usuários, biblioteca, stats
│
└── main/
    └── StreamingMusica.java  # Ponto de entrada: menus e interação com usuário
```

---

## 🏗️ Diagrama de Classes (simplificado)

```
<<interface>>          <<interface>>          <<interface>>
 Reproduzivel           Gerenciavel             Estatistico
 + reproduzir()         + adicionarMusica()     + exibirEstatisticas()
 + pausar()             + removerMusica()       + getTotalReproducoes()
 + getNome()            + getTotalMusicas()
      ▲                       ▲                       ▲
      │                       │                       │
«abstract» Playlist ──────────┘               «abstract» Usuario ──┘
 # nome                                        # nome
 # musicas                                     # email
 + adicionarMusica()                           # historicoReproducao
 + removerMusica()                             + reproduzirMusica() [abstract]
 + reproduzir() [abstract]                     + getTipoConta()     [abstract]
      ▲                                               ▲
      │                                        ┌──────┴──────┐
PlaylistAutomatica                      UsuarioFree    UsuarioPremium
 - criterio                              - totalAnuncios  - plano
 + atualizar()                           + reproduzirMusica()
                                         + getTipoConta()

Musica implements Reproduzivel
```

---

## 🧩 Conceitos de POO Aplicados

| Conceito | Onde é aplicado |
|---|---|
| **Classe Abstrata** | `Usuario` e `Playlist` — definem estrutura sem implementação completa |
| **Interface** | `Reproduzivel`, `Gerenciavel`, `Estatistico` — contratos desacoplados |
| **Herança** | `UsuarioFree` e `UsuarioPremium` estendem `Usuario`; `PlaylistAutomatica` estende `Playlist` |
| **Polimorfismo** | `usuario.reproduzirMusica(m)` se comporta diferente para Free e Premium |
| **Encapsulamento** | Atributos `private`/`protected`, acesso via getters |
| **Pacotes** | Separação em `model`, `service`, `interfaces`, `main` |
| **SRP** | `StreamingService` cuida da lógica; `StreamingMusica` cuida da UI |

---

## ▶️ Como Executar

### Pré-requisitos
- Java 17 ou superior
- IDE com suporte a projetos Java (IntelliJ IDEA recomendada)

### Compilando pelo terminal

```bash
# Na raiz do projeto
find src -name "*.java" > sources.txt
javac -d out @sources.txt

# Executando
java -cp out main.StreamingMusica
```

### Pelo IntelliJ IDEA
1. Abra o projeto (`File > Open`)
2. Marque `src` como **Sources Root** (botão direito > Mark Directory as > Sources Root)
3. Execute `main/StreamingMusica.java`

---

## 💡 Funcionalidades

- **Criar usuário** — Free, Premium Individual ou Premium Família
- **Login** — selecione seu perfil na lista
- **Playlists automáticas** — Top 10, Recomendadas, Recentes
- **Experiência diferenciada** — Free ouve anúncios; Premium tem alta qualidade
- **Estatísticas individuais** — histórico de reproduções por usuário
- **Estatísticas globais** — totais de usuários, reproduções e anúncios

---

## 👩‍💻 Boas Práticas Adotadas

- ✔️ **Javadoc** em todas as classes e métodos públicos
- ✔️ **Nomes expressivos** em português, sem abreviações confusas
- ✔️ **Métodos curtos** com responsabilidade única
- ✔️ **`final`** em atributos imutáveis
- ✔️ **`instanceof` com polimorfismo** para comportamentos específicos
- ✔️ **Validação de entrada** com `try-catch` no `lerInt()`
- ✔️ **Separação UI / Lógica** entre `StreamingMusica` e `StreamingService`

---

## 📚 Tecnologias

- Java 17+
- IntelliJ IDEA
- POO pura (sem frameworks externos)
