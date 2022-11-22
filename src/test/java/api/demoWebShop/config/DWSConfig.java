package api.demoWebShop.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:properties/${environment}.properties"
})
public interface DWSConfig extends Config {

    String browser();
    String browserVersion();
    String browserSize();
    String remoteUrl();
}
