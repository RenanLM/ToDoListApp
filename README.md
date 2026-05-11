# ToDoList App - Projeto Final Android

Este projeto é entrega final do módulo intermediário da trilha de Desenvolvimento Android - 
Capacita Brasil Residência em TIC 20.
Trata-se de um aplicativo de Lista de Tarefas (ToDo List) construído com os padrões modernos de desenvolvimento nativo exigidos na formação.


## 🎯 Justificativa da Escolha do Tema
O projeto de Lista de Tarefas foi escolhido por ser uma aplicação prática e muito comum no desenvolvimento mobile, permitindo organizar atividades do dia a dia. Este tipo de aplicativo é ideal para praticar conceitos vitais do Jetpack Compose, como interfaces declarativas e atualização automática da UI através do gerenciamento de estado. O tema permitiu aplicar o fluxo completo de dados, desde a entrada do usuário até a persistência local e navegação segura.

## 📱 Descrição do Funcionamento
O aplicativo é composto por duas telas principais interligadas:
1. **My Tasks (Tela Principal):** Apresenta um campo para inserção de novas tarefas e exibe a lista de tarefas salvas. Cada item possui uma *checkbox* que permite marcar a tarefa como concluída ou pendente em tempo real.
2. **Task Details (Tela de Detalhes):** Ao clicar em qualquer tarefa da lista, o usuário é navegado para esta tela, que exibe os dados detalhados da entidade (título e status atual) e um botão para retornar à tela anterior.

## 🛠️ Tecnologias e Arquitetura
O desenvolvimento seguiu estritamente os requisitos técnicos da atividade:
* **Linguagem:** Kotlin.
* **Interface (UI):** Desenvolvida utilizando **Jetpack Compose**.
* **Navegação:** Utilização do **Navigation Compose** para gerenciar as rotas (`taskList` e `taskDetail/{taskId}`) e o empilhamento de telas.
* **Persistência Local:** Implementada com **Room Database**, contendo a entidade `Task`, o DAO com suporte a Coroutines (`suspend`) e a emissão reativa de dados com `Flow`.
* **Arquitetura MVVM:** Separação de responsabilidades com o uso de `ViewModel`, que atua como ponte entre a camada de dados (Room) e a camada visual (Compose), convertendo o fluxo de dados em `StateFlow` para reatividade na UI.

## 📝 Observações Adicionais
A injeção do banco de dados na ViewModel foi realizada de forma nativa utilizando o padrão de 
*Factory* na `MainActivity`, garantindo a aplicação do padrão Singleton do Room, mantendo o projeto 
leve e focado no escopo da disciplina.