% Informações de cliente
cliente(123, 'Alice', 'NYC-123', 'New York', '01-01-2020').
cliente(456, 'Bob', 'CHI-456', 'Chicago', '02-02-2020').
cliente(789, 'Charlie', 'LA-789', 'Los Angeles', '03-03-2020').
cliente(752, 'John', 'CHI-456', 'Chicago', '03-03-2020').

% Balanço total da conta (valor corrente + valor disponível de crédito)
balanco_total(123, 2500).
balanco_total(456, 500).
balanco_total(789, 50).
balanco_total(752, 5000).

% Balanço de crédito
balanco_credito(123, 0).
balanco_credito(456, 5000).
balanco_credito(789, 2000).

% Movimentos (valor e data do movimento)
movimento(123, -100, '01-01-2020').
movimento(456, 200, '02-02-2020').
movimento(789, -10, '03-03-2020').
movimento(789, -20, '04-03-2020').

% Predicados

% Predicado para obter todos os clientes
todos_clientes(NumCliente, Nome, Agencia, Cidade, DataAbertura) :-
    cliente(NumCliente, Nome, Agencia, Cidade, DataAbertura).

% Predicado para obter todos os clientes de uma cidade
clientes_cidade(Cidade, NumCliente, Nome) :-
    cliente(NumCliente, Nome, _, Cidade, _).

% Predicado para obter todos os clientes de uma agência
clientes_elegiveis_credito(NumCliente, Nome, Agencia, Cidade, DataAbertura) :-
    cliente(NumCliente, Nome, Agencia, Cidade, DataAbertura),
    balanco_total(NumCliente, Saldo),
    Saldo > 100,
    \+ balanco_credito(NumCliente, _).

% Predicado para obter o saldo real de um cliente
saldo_real(NumCliente, SaldoReal) :-
    balanco_total(NumCliente, BalancoTotal),
    (balanco_credito(NumCliente, BalancoCredito) -> SaldoReal is BalancoTotal - BalancoCredito ; SaldoReal = BalancoTotal).

% Predicado para obter o balanço de crédito de um cliente
credito(NumCliente, BalancoCredito) :-
    balanco_credito(NumCliente, BalancoCredito).

% Predicado para obter movimentos de um cliente
movimentos_cliente(NumCliente, Valor, Data) :-
    movimento(NumCliente, Valor, Data).
