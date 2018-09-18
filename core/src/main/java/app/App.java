package app;

import org.messtin.framework.core.Context;
import org.messtin.framework.core.Core;

/**
 * Just for test, will delete it after project complete
 *
 * @author majinliang
 */
public class App {
    public static void main(String[] args) throws Exception {
        String packet = "app";
        Context context = Core.init(packet);
        House house = (House) context.getBean("House");
        System.out.println(house);
    }
}
