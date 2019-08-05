package redeemsystems.com.ambience;

public class MyBean
{
    String refrigerator_id, refrigerator_status, External_temperature_1,External_humidity_1, External_temperature_2,
            External_humidity_2, External_temperature_3, External_humidity_3, freezer_temperature, freezer_humidity,
            vc_new, Door_state, power_consumption;

    public MyBean(String refrigerator_id, String refrigerator_status, String external_temperature_1, String external_humidity_1, String external_temperature_2, String external_humidity_2, String external_temperature_3, String external_humidity_3, String freezer_temperature, String freezer_humidity, String vc_new, String door_state, String power_consumption) {
        this.refrigerator_id = refrigerator_id;
        this.refrigerator_status = refrigerator_status;
        External_temperature_1 = external_temperature_1;
        External_humidity_1 = external_humidity_1;
        External_temperature_2 = external_temperature_2;
        External_humidity_2 = external_humidity_2;
        External_temperature_3 = external_temperature_3;
        External_humidity_3 = external_humidity_3;
        this.freezer_temperature = freezer_temperature;
        this.freezer_humidity = freezer_humidity;
        this.vc_new = vc_new;
        Door_state = door_state;
        this.power_consumption = power_consumption;
    }

    public String getRefrigerator_id() {
        return refrigerator_id;
    }

    public void setRefrigerator_id(String refrigerator_id) {
        this.refrigerator_id = refrigerator_id;
    }

    public String getRefrigerator_status() {
        return refrigerator_status;
    }

    public void setRefrigerator_status(String refrigerator_status) {
        this.refrigerator_status = refrigerator_status;
    }

    public String getExternal_temperature_1() {
        return External_temperature_1;
    }

    public void setExternal_temperature_1(String external_temperature_1) {
        External_temperature_1 = external_temperature_1;
    }

    public String getExternal_humidity_1() {
        return External_humidity_1;
    }

    public void setExternal_humidity_1(String external_humidity_1) {
        External_humidity_1 = external_humidity_1;
    }

    public String getExternal_temperature_2() {
        return External_temperature_2;
    }

    public void setExternal_temperature_2(String external_temperature_2) {
        External_temperature_2 = external_temperature_2;
    }

    public String getExternal_humidity_2() {
        return External_humidity_2;
    }

    public void setExternal_humidity_2(String external_humidity_2) {
        External_humidity_2 = external_humidity_2;
    }

    public String getExternal_temperature_3() {
        return External_temperature_3;
    }

    public void setExternal_temperature_3(String external_temperature_3) {
        External_temperature_3 = external_temperature_3;
    }

    public String getExternal_humidity_3() {
        return External_humidity_3;
    }

    public void setExternal_humidity_3(String external_humidity_3) {
        External_humidity_3 = external_humidity_3;
    }

    public String getFreezer_temperature() {
        return freezer_temperature;
    }

    public void setFreezer_temperature(String freezer_temperature) {
        this.freezer_temperature = freezer_temperature;
    }

    public String getFreezer_humidity() {
        return freezer_humidity;
    }

    public void setFreezer_humidity(String freezer_humidity) {
        this.freezer_humidity = freezer_humidity;
    }

    public String getVc_new() {
        return vc_new;
    }

    public void setVc_new(String vc_new) {
        this.vc_new = vc_new;
    }

    public String getDoor_state() {
        return Door_state;
    }

    public void setDoor_state(String door_state) {
        Door_state = door_state;
    }

    public String getPower_consumption() {
        return power_consumption;
    }

    public void setPower_consumption(String power_consumption) {
        this.power_consumption = power_consumption;
    }


}
