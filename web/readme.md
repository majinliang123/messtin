# Messtin Web
Support web like spring mvc.

### Quick Start
1. Install **tomcat** and add this module into your dependency.
2. Create **web.xml** like below
```xml
<!-- set the package you want to scan -->
<context-param>
	<param-name>scanPacket</param-name>
	<!-- use comma to split the packages -->
	<param-value>org.coody.czone</param-value>
</context-param>
<!-- set loaders you want to use -->
<context-param>
	<param-name>initLoader</param-name>
	<!-- use comma to split the loaders -->
	<param-value>org.coody.framework.web.loader.WebAppLoader,org.coody.framework.task.loader.TaskLoader</param-value>
</context-param>
<!-- set listener -->
<listener>
	<listener-class>org.messtin.framework.web.listener.MesstinServletListener</listener-class>
</listener>
<!-- set dispatcher -->
<servlet>
	<servlet-name>DispatServlet</servlet-name>
	<servlet-class>org.messtin.framework.web.DispatServlet</servlet-class>
	<init-param>
		<!-- set static path -->
		<param-name>viewPath</param-name>
		<param-value>/</param-value>
	</init-param>
</servlet>
<!-- set mvc -->
<servlet-mapping>
	<servlet-name>DispatServlet</servlet-name>
	<url-pattern>*.do</url-pattern>
</servlet-mapping>
```

3. Create controller class. 
Add **Bean** annotation, so that we could get it from container.
Add **RequestMapping** annotation, so that we could project path to handler method.
Add **Adapter** annotation to set how we convert request params to method params.

```java
@Bean("UserController")
@RequestMapping("/user")
@Adapter(FormMealAdapt.class)
public class UserController {

    @RequestMapping("/get")
    public String getUser(){
        return "admin";
    }
}
```

