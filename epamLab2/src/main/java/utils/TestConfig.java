package utils;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.Sources;

@Sources({"classpath:test.properties"})
public interface TestConfig extends Config{

    @Key("webdriver.chrome.driver")
    String pathToDriver();

    @Key("homepage.url")
    String homePageURL();
}
