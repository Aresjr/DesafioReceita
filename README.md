# Desafio Receita

## Compilação e uso
```mvn clean install```
- Irá baixar as dependências do Maven e gerar o arquivo jar "DesafioReceita\projeto\target\DesafioReceita-0.0.1-SNAPSHOT.jar"
- Pode ser executado como serviço "standalone" com ```java -jar DesafioReceita-0.0.1-SNAPSHOT.jar```

## Arquivo de leitura
Projeto configurado para a leitura do arquivo no caminho C:\receita\arquivo.csv para que funcione independente da execução do projeto sendo pela IDE ou pelo jar gerado, caso necessite a alteração do caminho, pode ser alterado no arquivo ```messages.properties```

## Testes Unitários
```mvn test```
