
package planificationrencontre;

import java.util.Set;


@javax.ws.rs.ApplicationPath("rencontre")
public class ApplicationConfig extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

   
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(planificationrencontre.Planification.class);
    }
    
}
