package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:local.properties"
})

public interface LocalConfig extends Config {

    @Key("platformName")
    @DefaultValue("android")
    String platformName();

    @Key("deviceName")
    @DefaultValue("Pixel 4 API 30")
    String deviceName();

    @Key("osVersion")
    @DefaultValue("11.0")
    String osVersion();
}