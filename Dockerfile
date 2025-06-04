FROM jboss/wildfly

USER root

# Cria os diretórios para o módulo do PostgreSQL
RUN mkdir -p /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main

# Copia os arquivos do driver e do módulo
COPY config/postgresql/main/postgresql-42.7.5.jar /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main/
COPY config/postgresql/main/module.xml /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main/

# Copia o standalone.xml
COPY config/standalone.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml

# Ajusta permissões
RUN chown -R jboss:root /opt/jboss/wildfly/modules/system/layers/base/org/postgresql && \
    chown -R jboss:root /opt/jboss/wildfly/standalone/configuration

# Retorna ao usuário padrão
USER jboss

RUN /opt/jboss/wildfly/bin/jboss-cli.sh --command="module add --name=org.jboss.as.datasource --resources=/opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main/postgresql-42.7.5.jar --dependencies=javax.api,javax.transaction.api"

# Expõe as portas
EXPOSE 8080 9990
