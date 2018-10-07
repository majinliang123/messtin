# Task Module
Support linux crontab like task.

### Quick Start
1. Add **Bean** to class.
2. Add **CronTask** to the method you want run.

```java
@Bean("ClockTask")
public class ClockTask {

    @CronTask("0 */1 * * * *")
    public void printEveryMinute(){
        System.out.println("Now is: " + new Date());
    }
}
```