# Consumo de API - Java 8

Este é um exemplo de aplicação simples em Java 8 que consome uma API utilizando o método POST com autenticação Basic e envio de dados em formato JSON.

## Como usar

O método `consumirApi()` realiza as seguintes operações:

- Faz uma requisição **POST** para a URL especificada
- Usa **autenticação Basic** com as credenciais fornecidas
- Envia o **body JSON** contendo o CPF
- Imprime no console:
  - O **status HTTP** da resposta
  - O **body da resposta** como string JSON compacta
  - O **body da resposta** como JSON formatado (caso seja válido)

Para executar, basta chamar o método `main()`.

## Observações

- O código utiliza apenas **Java 8 puro**, sem bibliotecas externas
- Trata tanto respostas **JSON válidas** quanto **não-JSON**
- Fecha todos os recursos adequadamente usando **try-with-resources**
- Mostra **duas formas de exibir** o body da resposta
