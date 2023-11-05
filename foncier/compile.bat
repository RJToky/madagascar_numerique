jar cvf ./production/foncier.jar -C ./bin .
jar cvf ./production/foncier.war -C ./web .
jar cvf C:\wildfly-29.0.1.Final\standalone\deployments\foncier.ear -C ./production .