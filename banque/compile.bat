jar cvf ./production/banque.jar -C ./bin .
jar cvf ./production/banque.war -C ./web .
jar cvf C:\wildfly-29.0.1.Final\standalone\deployments\banque.ear -C ./production .
