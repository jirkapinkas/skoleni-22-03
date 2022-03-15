package com.test

import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import spock.lang.Specification

class FirstTest extends Specification {

    private static ChromeDriver driver

    def setupSpec() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-99.exe");
        driver = new ChromeDriver()
    }

    def cleanupSpec() {
        if (driver != null) {
            driver.close()
        }
    }

    def "test 1"() {
        given:
            driver.navigate().to("https://www.java-skoleni.cz/nabidka")
            def wait = new WebDriverWait(driver, 10)
        when:
            def xpathInputBox = "//input[@id='inputSearch']"
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathInputBox)))

            def searchBox = driver.findElement(By.xpath(xpathInputBox))
            searchBox.sendKeys("hibernate")

            def xpathImage = "//div[5]/div/a/div/img"
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathImage)))

            def submitSearchButton = driver.findElement(By.xpath(xpathImage))
            submitSearchButton.click()
        then:
            driver.currentUrl == "https://www.java-skoleni.cz/kurz/hibernate"
    }

}
