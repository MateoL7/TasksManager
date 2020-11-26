package config;


import services.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


@ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(ToDoTaskServices.class);
        resources.add(DoingTaskServices.class);
        resources.add(DoneTaskServices.class);
        return resources;
    }
}
