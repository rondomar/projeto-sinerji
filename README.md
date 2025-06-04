# Projeto de Cadastro de Pessoas com Endereços

Este projeto é uma aplicação Java utilizando o framework JSF (JavaServer Faces) com o banco de dados PostgreSQL. O objetivo faz parte do desafio proposto para vaga de desenvolvedor java, a aplicação deverá permitir o cadastro de pessoas e seus endereços.

## Tecnologias Utilizadas

- **Java 11**
- **JSF (PrimeFaces)**
- **PostgreSQL 14.4.0**
- **WildFly 18**

---

## 1. Ambiente

### 1.1. Eclipse

1. **Eclipse IDE**:
   - Eclipse IDE for Java Developers [aqui](https://www.eclipse.org/downloads/).
   

### 1.2. PostgreSQL

1. **PostgreSQL**:
   - PostgreSQL [aqui](https://www.postgresql.org/download/).
   

### 1.3. WildFly

1. **WildFly 18**:
   - WildFly [aqui](https://www.wildfly.org/downloads/).


## 2. Arquivos de configuração

### 2.1. persistence.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence
                                 http://java.sun.com/xml/ns/persistence/persistence_2_1.xsd"
             version="2.1">

    <persistence-unit name="pessoaPU" transaction-type="JTA">
        <jta-data-source>java:/PostgresDS</jta-data-source>
        
        <!-- Configurações de conexão -->
        <properties>
            <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>
            <property name="hibernate.jdbc.lob.non_contextual_creation" value="true"/>
            
            <!-- Configurações do JDBC -->
            <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://192.168.25.24:5433/sinerji"/>
            <property name="javax.persistence.jdbc.user" value="postgres"/>
            <property name="javax.persistence.jdbc.password" value="123456"/>
            <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver"/>
        </properties>
    </persistence-unit>
</persistence>
```


### 2.2. web.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         version="3.1"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                             http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd">

    <display-name>pessoa-web</display-name>

    <context-param>
        <param-name>javax.faces.PROJECT_STAGE</param-name>
        <param-value>Development</param-value>
    </context-param>

    <servlet>
        <servlet-name>FacesServlet</servlet-name>
        <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>FacesServlet</servlet-name>
        <url-pattern>*.xhtml</url-pattern>
    </servlet-mapping>

    <welcome-file-list>
        <welcome-file>pages/pessoa.xhtml</welcome-file>
    </welcome-file-list>

</web-app>
```


### 2.3. pom.xml
```
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>br.com.sinerji</groupId>
    <artifactId>pessoaweb</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>war</packaging>

    <name>pessoaweb</name>
    <description>Projeto desafio SINERJI</description>

    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

<dependencies>

	<dependency>
	    <groupId>org.glassfish</groupId>
	    <artifactId>javax.faces</artifactId>
	    <version>2.2.20</version>
	    <scope>provided</scope>
	</dependency>


    <!-- PrimeFaces -->
    <dependency>
        <groupId>org.primefaces</groupId>
        <artifactId>primefaces</artifactId>
        <version>8.0</version>
    </dependency>

    <!-- EJB -->
    <dependency>
        <groupId>javax.ejb</groupId>
        <artifactId>javax.ejb-api</artifactId>
        <version>3.2</version>
        <scope>provided</scope>
    </dependency>

    <!-- JPA -->
    <dependency>
        <groupId>javax.persistence</groupId>
        <artifactId>javax.persistence-api</artifactId>
        <version>2.2</version>
        <scope>provided</scope>
    </dependency>

    <!-- CDI -->
    <dependency>
        <groupId>javax.inject</groupId>
        <artifactId>javax.inject</artifactId>
        <version>1</version>
        <scope>provided</scope>
    </dependency>
	<dependency>
	    <groupId>javax.enterprise</groupId>
	    <artifactId>cdi-api</artifactId>
	    <version>2.0</version>
	    <scope>provided</scope>
	</dependency>


    <!-- Hibernate -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>5.4.30.Final</version>
    </dependency>

    <!-- PostgreSQL JDBC -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.2.5</version>
    </dependency>

    <!-- JUnit para testes -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>


    <build>
        <finalName>pessoaweb</finalName>
        <plugins>
            <!-- Plugin para compilar projetos WAR -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>3.3.2</version>
            </plugin>
        </plugins>
    </build>
</project>

```


## 3. Banco de dados - arquivos de estrutura

### 3.1. Pessoa.ddl
```
CREATE TABLE public.pessoa (
	id bigserial NOT NULL,
	idade date NULL,
	nome varchar(150) NOT NULL,
	sexo varchar(1) NULL,
	CONSTRAINT pessoa_pkey PRIMARY KEY (id)
);
```

### 3.2. Endereco.ddl
```
CREATE TABLE public.endereco (
	id bigserial NOT NULL,
	cep varchar(8) NULL,
	cidade varchar(100) NULL,
	estado varchar(2) NOT NULL,
	logradouro varchar(100) NULL,
	numero int4 NOT NULL,
	id_pessoa int8 NULL,
	CONSTRAINT endereco_pkey PRIMARY KEY (id),
	CONSTRAINT fk36sv4vy3hi3flqeiswatu14sl FOREIGN KEY (id_pessoa) REFERENCES public.pessoa(id)
);
```

## 4. Docker

### 4.1. Arquivo docker-compose.yml
```
version: "3.8"

services:

  web_sinerji:
    container_name: web_sinerji
    image: jboss/wildfly
    mem_limit: 1G
    mem_reservation: 100MB
    restart: always 
    environment:
      - GENERIC_TIMEZONE=America/Sao_Paulo
      - LANG=pt_BR.UTF-8
      - LC_TIME=pt_BR.UTF-8
      - PUID=1000
      - PGID=1000
    volumes:
      - /etc/localtime:/etc/localtime:ro
      - /etc/timezone:/etc/timezone:ro
      - ./target/pessoaweb.war:/opt/jboss/wildfly/standalone/deployments/pessoaweb.war
    labels:
      - traefik.enable=true
      - traefik.http.routers.web_sinerji.rule=Host(`sinerji.lab.autobits.interno`)
      - traefik.http.routers.web_sinerji.entrypoints=web
      - traefik.http.routers.web_sinerji.tls=false
      - traefik.http.services.web_sinerji.loadbalancer.server.port=8080
    networks:
      - frontend
      - backend
    depends_on:
      - db_autobits_postgresql144_dev
    command: ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

networks:
  frontend:
    external: true
  backend:
    external: true
```

### 4.1.1. **Subindo o docker**:
```
1. Pré-requisito, docker.
2. Via comando: docker compose up
3. Acessar container: docker exec -it web_sinerji /bin/bash
4. Executar comando dentro do container para criar usuário no wildfly: /opt/jboss/wildfly/bin/add-user.sh

Obs.: 
- O container está em duas networks para testes local, sugiro alterar para rodar em outro ambiente
- É pré-requisito um banco de dados com o nome sinerji.
```

=======
# projeto-sinerji
projeto-sinerji
>>>>>>> 8b718cd1cacef5156abbba8ed9ec04485c827392
