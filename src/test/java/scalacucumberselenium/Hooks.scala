package scalacucumberselenium

import io.cucumber.scala.{EN, ScalaDsl, Scenario}
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.{OutputType, TakesScreenshot, WebDriver}

object webDriver {
  var driver: WebDriver = _
}

class Hooks extends ScalaDsl with EN{

  Before { (_: Scenario) =>
    System.out.print("Starting before hook")
    WebDriverManager.chromedriver.setup()
    webDriver.driver = new ChromeDriver
  }

  After { (scenario: Scenario) =>
    val screenshot = webDriver.driver.asInstanceOf[TakesScreenshot].getScreenshotAs(OutputType.BYTES)
    scenario.attach(screenshot, "image/png", "image1")
    webDriver.driver.close()
  }

}
