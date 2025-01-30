# Proposta de solução ao desafio

- Migrar o gradle para .kts para obter mais performance, clareza e segurança com tipagem e erros em 
tempo de compilação. Usar o versionamento através do version catalog para maior organização.
- Atualizar e otimizar as dependencias utilizadas no projeto.
- Migrar a view para viewbinding e passar a utilizar o Single Activity Pattern, deixando o app 
preparado para possível migração para compose no futuro.
- Modularização para ganho de performance e otimização de build, além de maior organização caso o
projeto cresça.
- Utilização de Clean Architecture para maior escalabilidade e divisão de responsabilidade do código.
- Utilização de MVI como pattern da camada de Presentation junto com FlowState para gerenciamento 
de estado
- Implementar DI utilizando Koin.
- Adicionar cobertura de testes unitarios para todos os niveis da arquitetura.
- Adicionar testes instrumentados para assegurar que as views estão funcionando corretamente
- Utilizar padrão Network first, para cache persistente

## **Etapas**

- Criar estrutura da clean Architecture;
- Implementar módulo de network e core;
- Implementar módulo de usuário;
- Implementar testes unitários paras os modulos
- Migrar view para Single Activity Pattern;
- Implementar cache e testes para cache
- Implementar teste instrumentado
