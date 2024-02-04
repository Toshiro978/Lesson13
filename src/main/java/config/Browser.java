package config;

import com.codeborne.selenide.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public static void configSelenide(){

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

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome"); // Встановлення браузера, наприклад, Chrome
        capabilities.setVersion("latest"); // Встановлення версії браузера
        capabilities.setCapability("enableVNC", true); // Увімкнення функції VNC
        capabilities.setCapability("enableVideo", false); // Вимкнення запису відео
        capabilities.setCapability("selenoid:options",
                ImmutableMap.of("enableVNC", true, "videoName", "some.mp4"));
        //Proxy
        selenideConfig.proxyEnabled(true);
        selenideConfig.proxyHost(new NetworkUtils().getNonLoopbackAddressOfThisMachine());
        selenideConfig.fileDownload(FileDownloadMode.PROXY);

        URL selenoidURL = null;
        try {
            selenoidURL = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        var selenideDriver = new SelenideDriver(selenideConfig);

    //    WebDriver driver = new RemoteWebDriver(selenoidURL, capabilities);
        WebDriverRunner.setWebDriver(selenideDriver.getAndCheckWebDriver());
       // driver.get("https://www.saucedemo.com/");

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