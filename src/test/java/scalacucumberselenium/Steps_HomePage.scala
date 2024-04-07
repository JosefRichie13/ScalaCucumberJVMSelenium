package scalacucumberselenium

import io.cucumber.scala.{EN, ScalaDsl}
import org.junit.Assert._

class Steps_HomePage extends ScalaDsl with EN{

  val driverMethods = new Driver
  val configs = new Configs
  val selectors = new Selectors

  Then("""the number of items in the cart bubble is {string}"""){ (cartNumber: String) =>
    assertEquals(driverMethods.getTextFromElement(selectors.itemNumberInCart), cartNumber)
  }

}
