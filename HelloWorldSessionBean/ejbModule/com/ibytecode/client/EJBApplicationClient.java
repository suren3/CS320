package com.ibytecode.client;
 
import javax.naming.Context;
import javax.naming.NamingException;
 
import com.ibytecode.business.HelloWorld;
import com.ibytecode.businesslogic.HelloWorldBean;
import com.ibytecode.clientutility.ClientUtility;
 
public class EJBApplicationClient {
     
    public static void main(String[] args) {
        HelloWorld bean = doLookup();
        System.out.println(bean.sayHello()); // 4. Call business logic
    }
 
    private static HelloWorld doLookup() {
        Context context = null;
        HelloWorld bean = null;
        try {
            // 1. Obtaining Context
            context = ClientUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            String lookupName = getLookupName();
            // 3. Lookup and cast
            bean = (HelloWorld) context.lookup(lookupName);
 
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return bean;
    }
 
    private static String getLookupName() {
/* 
The app name is the EAR name of the deployed EJB without .ear suffix. 
Since we haven't deployed the application as a .ear, 
the app name for us will be an empty string
*/
        String appName = "";
 
        /* The module name is the JAR name of the deployed EJB 
        without the .jar suffix.
        */
        String moduleName = "HelloWorldSessionBean";
 
/*AS7 allows each deployment to have an (optional) distinct name. 
This can be an empty string if distinct name is not specified.
*/
        String distinctName = "";
 
        // The EJB bean implementation class name
        String beanName = HelloWorldBean.class.getSimpleName();
 
        // Fully qualified remote interface name
        final String interfaceName = HelloWorld.class.getName();
 
        // Create a look up string name
        String name = "ejb:" + appName + "/" + moduleName + "/" + 
            distinctName    + "/" + beanName + "!" + interfaceName;
 
        return name;
    }
}