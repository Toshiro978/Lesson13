package config;

import com.codeborne.selenide.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.google.common.collect.ImmutableMap;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.net.NetworkUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Browser {

    public static void configSelenoid() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome"); // Встановлення браузера, наприклад, Chrome
        capabilities.setCapability("selenoid:options",
                ImmutableMap.of("enableVNC", true, "enableVideo", false));

        //Proxy
        capabilities.setCapability("proxy", ImmutableMap.of(
                "proxyType", "manual",
                "httpProxy", "http://192.168.0.161:4444"
        ));

        URL selenoidURL = null;
        try {
            selenoidURL = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        WebDriver driver = new RemoteWebDriver(selenoidURL, capabilities);
        WebDriverRunner.setWebDriver(driver);
        // driver.get("https://www.saucedemo.com/");

    }

    public static void setBrowser() {
        var selenideConfig = new SelenideConfig();
        // selenideConfig.remote("http://localhost:4444/wd/hub"); // Use for Selenoid
        selenideConfig.browser("chrome");
        selenideConfig.browserSize("1280x720");
        selenideConfig.pageLoadStrategy("normal");
        selenideConfig.screenshots(true);
        selenideConfig.savePageSource(true);
        selenideConfig.pageLoadTimeout(15000);
        selenideConfig.timeout(10000);
        selenideConfig.textCheck(TextCheck.FULL_TEXT);

        var selenideDriver = new SelenideDriver(selenideConfig);
        WebDriverRunner.setWebDriver(selenideDriver.getAndCheckWebDriver());

    }

    public static void closeBrowser() {
        WebDriverRunner.closeWebDriver();
    }

    public static List<LogEntry> getLogs() {
        return WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER).getAll();
    }

    public static List<LogEntry> getLogsByLevel(Level logLevel) {
        var logs = getLogs();
        return logs.stream().filter(logEntry -> logLevel.equals(logEntry.getLevel())).toList();
    }


}