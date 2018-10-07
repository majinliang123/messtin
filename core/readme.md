# Core Module
The Core module to support Ioc,AOP.

### Quick Start
1. Ioc.
    
    A) Run `Core.init("org.messtin.framework.example")`, the param is the package you need scan.
    
    B) Create class like below.
```java
@Bean("AirCondition")
public class AirCondition {
    
    private String name = "Haier";
    private int price = 1000;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
```

```java
@Bean("House")
public class House {
    
    @Autowired("AirCondition")
    private AirCondition airCondition;
    
    public int getAirConditionPrice(){
        return  airCondition.getPrice();
    }
}

```

2. AOP

    A) Run `Core.init("org.messtin.framework.example")`, the param is the package you need scan.

    B) Create aspect class like below.
```java
@Aspect(clazz = "org.messtin.framework.example.AirCondition", method = "getPrice")
public class AirConditionAspect extends AbstractAspect {

    /**
     * Run before getPrice method run.
     */
    @Override
    public void beforeMethod() {
        System.out.println("before method run.");
    }

    /**
     * Run before getPrice method return run.
     */
    @Override
    public void beforeMethodReturn() {
        System.out.println("before method return run.");
    }

    /**
     * Run after getPrice method run.
     */
    @Override
    public Object afterMethod(Object result) {
        System.out.println("after method run.");
        return result;
    }
}
```
