package app.bean;

import org.messtin.framework.core.annotation.Autowired;
import org.messtin.framework.core.annotation.Bean;

@Bean("House")
public class House {

    @Autowired("AirCondition")
    public AirCondition airCondition;

    public AirCondition getAirCondition() {
        return airCondition;
    }

    public void setAirCondition(AirCondition airCondition) {
        this.airCondition = airCondition;
    }

    @Override
    public String toString() {
        return "House{" +
                "airCondition=" + airCondition +
                '}';
    }
}
