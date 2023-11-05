jar cvf ./production/primature.jar -C ./bin .
jar cvf ./web/WEB-INF/lib/primature.jar -C ./bin .
jar cvf ./production/primature.war -C ./web .
jar cvf C:\wildfly-29.0.1.Final\standalone\deployments\primature.ear -C ./production .
