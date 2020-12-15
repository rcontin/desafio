PRE REQUISITOS
--------------
<p>. Maven instalado e corretamente configurado
<p>. Java 8 instalado e JAVA_HOME corretamente configurado<br>
<p>. ChromeDriver deve estar localizado no caminho: "c:\chromedriver\chromedriver.exe"
<p>

EXECUÇÃO
--------
<p>Atenção: Para que os testes sejam executados com sucesso, é necessário um ambiente de staging com a feature de CAPTCHA desabilitada.
<p>
<p>1. Clonar o projeto do link:
<p><tab>		https://github.com/rcontin/desafio.git
<p>
<p>2. Compilar o projeto:
<p><tab>		mvn clean test site
<p>
<p>Após executado, o HTML Report estará disponível em workspace/target
<p>
<p>O projeto também pode ser executado diretamente pelo Eclipse, Run As, JUnit Test
