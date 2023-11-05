package util;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import remote.RemoteBanque;
import remote.RemoteFoncier;

public class Remote {
    public static RemoteFoncier foncier() {
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        try {
            Context context = new InitialContext(env);
            RemoteFoncier app = (RemoteFoncier) context
                    .lookup("ejb:foncier/foncier.jar/SessionBean!remote.RemoteFoncier");
            return app;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static RemoteBanque banque() {
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        try {
            Context context = new InitialContext(env);
            RemoteBanque app = (RemoteBanque) context
                    .lookup("ejb:banque/banque.jar/SessionBean!remote.RemoteBanque");
            return app;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
