package scalacucumberselenium

import io.cucumber.datatable.DataTable
import io.cucumber.scala.{EN, ScalaDsl, Scenario}
import org.junit.Assert._

class Steps_ProductCheckout extends ScalaDsl with EN {

  val driverMethods = new Driver
  val configs = new Configs
  val selectors = new Selectors

  And("""I add {string} to the cart"""){(productToAdd: String) =>
    addOrRemoveProductFromCart(productToAdd)
  }

  And("""I remove {string} from the cart"""){(productToRemove: String) =>
    addOrRemoveProductFromCart(productToRemove)
  }

  def addOrRemoveProductFromCart(productType: String): Unit = {
    productType match {
      case "Sauce Labs Backpack" => driverMethods.clickButton(selectors.productBackpack)
      case "Sauce Labs Bike Light" => driverMethods.clickButton(selectors.productBikelight)
      case "Sauce Labs Bolt T-Shirt" => driverMethods.clickButton(selectors.productTshirt)
      case "Sauce Labs Fleece Jacket" => driverMethods.clickButton(selectors.productJacket)
      case "Sauce Labs Onesie" => driverMethods.clickButton(selectors.productOnesie)
      case "Test.allTheThings() T-Shirt (Red)" => driverMethods.clickButton(selectors.productTshirtRed)
      case _ => throw new IllegalArgumentException("Unavailable product : " + productType)
    }
  }

  And("""I click on the cart"""){ () =>
    driverMethods.clickButton(selectors.cart)
  }

  And("""I checkout"""){ () =>
    driverMethods.clickButton(selectors.checkout)
  }

  And("""I enter my information to continue"""){(table: DataTable) =>
    driverMethods.typeText(table.asMaps.get(0).get("FirstName"), selectors.firstName)
    driverMethods.typeText(table.asMaps.get(0).get("LastName"), selectors.lastName)
    driverMethods.typeText(table.asMaps.get(0).get("Zip"), selectors.zipCode)
    driverMethods.clickButton(selectors.continueButton)
  }

  And("""I confirm my order"""){ () =>
    driverMethods.clickButton(selectors.finishButton)
  }

  And("""I should see {string} after the order is placed"""){(message: String) =>
    assertEquals(driverMethods.getTextFromElement(selectors.checkoutBanner), message)
  }

}
