# Testando as rotas:<br>
Todo o tratamento das requisições deve ser feito na camada de controller.
<br>Uma vez estabelecidas e implementadas, as rotas da aplicação, idealmente, devem ser testadas!



## Por quê testar?<br>
Isso facilita enormemente o processo, uma vez que garante que certas funcionalidades estão se portando da maneira
esperada. Além disso é um processo independente do Frontend, o que poupa tempo da outra equipe e lhes dá
uma feature mais estável de ser trabalhada.


## Como são feitos os testes?<br>
Para testar as rotas iremos utilizar API clients, e alguns deles devem ser familiares! O intuito desses programas
é testar os comportamentos das rotas sob requisições dos diversos verbos HTTP (GET, POST, ...).
<br>Dessa forma, podemos montar requisições padronizadas que buscam testar funcionalidades específicas e
também verificar o conteúdo das respostas, tudo isso sem depender de um browser.


![image](https://github.com/user-attachments/assets/82b106a6-0aa2-4725-83ee-0e1f0ba4efa4)


### Opções(Puramente preferência):

#### ![image](https://github.com/user-attachments/assets/f29a68a4-1b6d-42d2-aff9-42c588ebccdd)<br>


[Bruno](https://www.usebruno.com/) é uma alternativa lightweight ao Postman e o Insomnia. Super leve, sem complicações, Open source, faz tudo
que se espera e mais. Sua proposta é ser mais simples e enxuto em relação aos competidores, mas tendo tudo que é essencial
para sua função. 


#### ![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)<br>


O [Postman](https://www.postman.com/) é possivelmente a opção mais popular. Tem de tudo (e possivelmente até coisa demais), não vai te deixar na mão. Contudo,
tem toda a burocracia de *precisar* de uma conta pra poder usar, o que pessoalmente não é um atrativo para mim (Daniel)
mas não me impede de usar. É bastante robusto, uma boa opção.


#### ![Insomnia](https://img.shields.io/badge/Insomnia-black?style=for-the-badge&logo=insomnia&logoColor=5849BE)<br>


[Insomnia](https://insomnia.rest/download): acho que o pessoal da rocketseat usa kkkk é tudo igual gente


### Como traduzir de Controller para Teste?<br>
### Exemplo(Entidade Usuario):
![image](https://github.com/user-attachments/assets/3a5a6d70-381e-48f2-99d6-0d745fd8f368)

![image](https://github.com/user-attachments/assets/efa5e929-acba-4304-a291-bd43436f6828)
<br>
Idealmente nas requisições que formos implementar, é sempre bom fazermos uma classe DTO (Data Transfer Object)
auxiliar para ser o "container" dos dados sendo passados. Os Records em Java servem muito bem pra essa função, uma vez
que são classes feitas para ser read-only. Isso tudo nos ajuda a manter a consistência dos dados ao evitar trabalhar
com objetos que representem as entidade da nossa aplicação diretamente.<br><br>
Essencialmente, a classe DTO serve para representar um conjunto específico de dados de uma entidade sem de fato
preciar depender dela. Pense no DTO como um "formulário" que contém todas as informações para uma dada tarefa, onde os
dados seriam referentes à entidade(um usuário específico, por exemplo).
<br>
Nesse exemplo, a classe DTO criada é para comportar os dados vindos, idealmente, do front, no formato JSON. E no nosso caso,
precisamos dos dados necessários para a criação de um novo usuário! Daí só precisamos criar uma classe que represente esses dados:


![image](https://github.com/user-attachments/assets/07eb6e81-a49a-4598-bedd-d95dbcf2e7f7)


![image](https://github.com/user-attachments/assets/f32eaf93-87c2-4707-83fb-96a0fbba3548)


Ao preparar o controller para receber as requisições e informar qual o formato esperado do body(RequestDTO), agora o Spring é
capaz de mapear corretamente um JSON enviado no body para o nosso objeto java e seus parametros!


![image](https://github.com/user-attachments/assets/282450d0-be67-46a9-8268-4a110f01669f)


Usuário criado!<br>
![image](https://github.com/user-attachments/assets/e99707d8-194f-45e9-b3d8-f6003ca6ff86)



Dessa forma, ao especificar como os dados devem ser enviados para o back, por meio da classe DTO, agora podemos
podemos usar as ferramentas de teste de API mencionadas anteriormente para fazer as requisições nesse formato e
ver que tipo de resposta o servidor dá ao cliente(Um JSON de resposta, qual código HTTP resultou daquela requisição, etc...).
<br>
**Assim, sempre que formos fazer uma rota, devemos pensar em como representar nossas entidades por meio de objetos
read-only, os DTOs, tanto nas requests tanto nas responses.**
