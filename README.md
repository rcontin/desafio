PRE REQUISITOS
--------------
. Maven instalado e corretamente configurado
. Java 8 instalado e JAVA_HOME corretamente configurado
. ChromeDriver deve estar localizado no caminho: "c:\chromedriver\chromedriver.exe"


EXECUÇÃO
--------
Atenção: Para que os testes sejam executados com sucesso, é necessário um ambiente de staging com a feature de CAPTCHA desabilitada.

1. Clonar o projeto do link:
		https://github.com/rcontin/desafio.git
		
2. Compilar o projeto:
		mvn clean test site

Após executado, o HTML Report estará disponível em workspace/target

O projeto também pode ser executado diretamente pelo Eclipse, Run As, JUnit Test
