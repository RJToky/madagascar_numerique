package config;

import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("app")
public class AppConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        // TODO Auto-generated method stub
        return super.getClasses();
    }

}
