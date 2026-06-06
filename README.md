# ToDoList App - Projeto Final Android

Este projeto é a entrega final do módulo intermediário da trilha de Desenvolvimento Android -
Capacita Brasil Residência em TIC 20.
Trata-se de um aplicativo de Lista de Tarefas (ToDo List) construído com os padrões modernos de 
desenvolvimento nativo exigidos na formação.

## ✨ Funcionalidades Principais
* **Gerenciamento Completo (CRUD):** Criação, visualização, edição (com título e descrição) e 
exclusão de tarefas.
* **Pesquisa em Tempo Real:** Barra de busca (`SearchBar`) para filtrar a lista instantaneamente 
pelo título ou conteúdo da tarefa.
* **Filtros de Status:** *Chips* de seleção rápida para visualizar todas as tarefas ("All") ou 
filtrar especificamente as pendentes ("Pending") e concluídas ("Completed").
* **Swipe-to-Delete:** Exclusão intuitiva de itens da lista deslizando o *Card* da tarefa para a 
direita.

## 🎯 Justificativa da Escolha do Tema
O projeto de Lista de Tarefas foi escolhido por ser uma aplicação prática e muito comum no 
desenvolvimento mobile, permitindo organizar atividades do dia a dia. Este tipo de aplicativo é 
ideal para praticar conceitos vitais do Jetpack Compose, como interfaces declarativas e atualização 
automática da UI através do gerenciamento de estado. O tema permitiu aplicar o fluxo completo de 
dados, desde a entrada do usuário até a persistência local e navegação segura.

## 📱 Descrição do Funcionamento
O aplicativo é composto por duas telas principais interligadas:
1. **My Tasks (Tela Principal):** Apresenta um campo para inserção de novas tarefas, uma barra de 
pesquisa, filtros de status e exibe a lista de tarefas salvas. Cada item possui uma *checkbox* que 
permite marcar a tarefa como concluída ou pendente em tempo real, além de suportar o gesto de 
deslizar para excluir.
2. **Task Details (Tela de Edição/Detalhes):** Ao clicar em qualquer tarefa da lista, o usuário é 
navegado para esta tela, que exibe os dados detalhados para edição (título e descrição) e os botões 
para salvar as alterações ou excluir a tarefa, retornando à tela anterior.

## 🛠️ Tecnologias e Arquitetura
O desenvolvimento seguiu estritamente os requisitos técnicos da atividade:
* **Linguagem:** Kotlin.
* **Interface (UI):** Desenvolvida utilizando **Jetpack Compose**, incluindo componentes modernos do 
Material 3 (`SwipeToDismissBox`, `FilterChip`).
* **Navegação:** Utilização do **Navigation Compose** para gerenciar as rotas (`taskList` e 
`taskDetail/{taskId}`) e o empilhamento de telas.
* **Persistência Local:** Implementada com **Room Database**, contendo a entidade `Task`, o DAO com 
suporte a Coroutines (`suspend`) e a emissão reativa de dados com `Flow`.
* **Arquitetura MVVM:** Separação de responsabilidades com o uso de `ViewModel`, que atua como ponte 
entre a camada de dados (Room) e a camada visual (Compose).

## 🚀 Como Executar o App
Para testar e rodar este projeto localmente, siga os passos abaixo:

1. **Pré-requisitos:** Certifique-se de ter o [Android Studio](https://developer.android.com/studio) 
mais recente instalado em sua máquina.
2. **Clonar o Repositório:**
```bash
   git clone [COLE_A_URL_REPOSITORIO_AQUI]
```
3. **Abrir o Projeto:** Inicie o Android Studio, selecione *Open* e navegue até a pasta do projeto.
4. **Sincronização:** Aguarde o Gradle sincronizar as dependências automaticamente.
5. **Execução:** Conecte um dispositivo Android físico via cabo USB/Wi-Fi 
(com a depuração USB ativada) ou inicie um Emulador no próprio Android Studio.
6. **Rodar:** Clique no botão verde de **Run (Play)** na barra superior ou pressione `Shift + F10`.

---

## 👨‍💻 Autor

**Renan Lucas de Moura**
* Estudante de Engenharia da Computação
* [Github](www.github.com/RenanLM)
* [Linkedin](www.linkedin.com/in/renanlmoura)