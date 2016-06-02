# SysRally

#### Requisitos do Software

Construir um software com o objetivo de controlar informações sobre o funcionamento de carros de corridas em um
campeonato de rally de regularidade. O modelo de domínio deve obrigatoriamente manter dados persistentes.
Utilize tecnologias e frameworks abordados na disciplina para a construção da solução.


 [] Permitir operações C- create, R – read, U – update, D – delete, L – list) sobre os dados persistentes.
 [] Há a exigência de conter pelo menos duas entidades persistentes no modelo.
 [] Linguagem de Programação: Java Enterprise Edition.
 [] Arquitetura: Client/Servidor (Servidor inteligente).
 [] Utilizar um Banco de Dados para a persistência.

 [] O servidor deve trocar informações com um ou mais carros (objetos que simulam o funcionamento do carro).
 [] Cada carro deve ser representado na memória do computador por um objeto que estende Runnable (Thread), que, por
sua vez, geram as informações sobre o funcionamento do carro durante a corrida. 

 [] Para os testes de funcionamento, Inicializa-se o servidor e em seguida inicializa-se uma ou mais threads que simulam o comportamento dos carros durante a corrida. 

 [] Os carros- simuladores enviam dados para o servidor, e este, permite a visualização, configuração,
acompanhamento da corrida. 

 [] Neste processo, o servidor produz análises on-line sobre os dados, para informar o
operador do console a respeito do andamento da corrida e detalhamento do funcionamento dos carros.