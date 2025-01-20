  Feature: Selecionar Produto
    Scenario Outline: Selecionar Produto com Sucesso
      Given que preciso comprar um presente
      When clico no <produto>
      Then exibe a página com o nome do <produto> e <preco>



      Examples:
      | produto                 | preco     |
      | "Sauce Labs Backpack"   | "$ 29.99" |
      | "Sauce Labs Bike Light" | "$ 9.99"  |
      | "Sauce Labs Onesie"     | "$ 7.99"  |