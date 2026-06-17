# ♻️ Recicla Trampo

Aplicativo **Android** que conecta **empresas** e **catadores** para a coleta de materiais recicláveis, facilitando a logística da reciclagem e gerando impacto socioambiental. Desenvolvido na **FIAP**.

## 💡 Sobre o projeto

O Recicla Trampo aproxima quem gera resíduos recicláveis de quem realiza a coleta, organizando todo o ciclo dentro do app:

- **Empresa** — cadastra coletas disponíveis (material, peso estimado, endereço, data e hora)
- **Catador** — visualiza as coletas abertas, aceita e finaliza o serviço

Cada coleta percorre um fluxo de status: `Solicitada → Aceita → Finalizada`.

## ⚙️ Funcionalidades

- 🔐 Cadastro e login com seleção de perfil (Empresa ou Catador)
- 📦 Criação e gestão de coletas de recicláveis
- 🔄 Acompanhamento do ciclo de coleta por status
- 📜 Histórico de coletas separado por perfil
- 💾 Persistência local com banco de dados Room

## 🛠️ Tecnologias

- **Kotlin**
- **Jetpack Compose** (UI declarativa)
- **Room** (banco de dados local)
- **Navigation Compose** (navegação por rotas)
- Arquitetura **MVVM** (Model–View–ViewModel) com Repository
- Material 3

## 📂 Estrutura

```
app/src/main/java/br/com/fiap/reciclatrampo/
├── data/         # Banco Room, DAOs, repositórios e sessão
├── model/        # Modelos (Coleta, ColetaStatus)
├── navigation/   # Rotas e grafo de navegação
├── screens/      # Telas (auth, criação, histórico, componentes)
├── viewmodel/    # Lógica de apresentação (Coleta, auth)
└── MainActivity.kt
```

## ▶️ Como executar

1. Clone o repositório
2. Abra o projeto no **Android Studio**
3. Aguarde o Gradle sincronizar as dependências
4. Rode o app em um emulador ou dispositivo Android

> O banco de dados Room é criado automaticamente no primeiro uso — não requer configuração externa.

## 👥 Equipe

Projeto acadêmico desenvolvido em grupo na **FIAP** — Análise e Desenvolvimento de Sistemas.

## 👤 Autora

**Maria Eduarda Barros** — [LinkedIn](https://www.linkedin.com/in/mariespbarros/)
