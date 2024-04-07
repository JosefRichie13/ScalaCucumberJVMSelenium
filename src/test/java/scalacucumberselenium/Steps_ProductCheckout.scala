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

  /* We get the Tax shown in the UI, extract the number, convert it into float and store it in a variable, taxCalculatedByAPP
     We get the non taxed sum shown in the UI, extract the number, convert into float, multiple it by 0.08 (8%), round the result off to 2 and store it in a variable, taxCalculatedByCODE
     Then we check if both taxCalculatedByAPP and taxCalculatedByCODE are equal

     We get the total shown in the UI, extract the number, convert it into float and store it in a variable, totalCalculatedByAPP
     We get the non tax added total shown in the UI, extract the number, convert into float, add the tax calculated (taxCalculatedByCODE) and store it in a variable, totalCalculatedByCODE
     Then we check if both totalCalculatedByAPP and totalCalculatedByCODE are equal
  */
  Then("""I should see the tax calculated at 8 percent"""){ () =>
    val taxCalculatedByAPP: Float = driverMethods.getTextFromElement(selectors.taxCalculated).replaceAll("[^\\d.]", "").toFloat
    val taxCalculatedByCODE: Float = BigDecimal(driverMethods.getTextFromElement(selectors.subtotal).replaceAll("[^\\d.]", "").toFloat * 0.08).setScale(2, BigDecimal.RoundingMode.HALF_UP).toFloat
    assertEquals(taxCalculatedByAPP, taxCalculatedByCODE, 0)

    val totalCalculatedByAPP: Float = driverMethods.getTextFromElement(selectors.fullTotal).replaceAll("[^\\d.]", "").toFloat
    val totalCalculatedByCODE: Float = driverMethods.getTextFromElement(selectors.subtotal).replaceAll("[^\\d.]", "").toFloat + taxCalculatedByAPP
    assertEquals(totalCalculatedByAPP, totalCalculatedByCODE, 0)
  }

  /*
  We get the individual prices into an array, individualPrices
  We remove the $ sign, convert the array elements into float and sum it. Storing it in a variable, sumCalculatedByCODE
  We get the total displayed in the UI, extract the number, convert into float and store it in a variable, sumCalculatedByAPP
  Then we check if sumCalculatedByCODE and sumCalculatedByAPP are equal
  */
  Then("""I should see the individual items total correctly"""){ () =>
    val individualPrices = driverMethods.getAllTextFromAListOfElements(selectors.priceList)
    val individualPricesInFloatWithoutSign = individualPrices.map(_.substring(1))
    val sumCalculatedByCODE = individualPricesInFloatWithoutSign.map(_.toFloat).sum

    val sumCalculatedByAPP : Float = driverMethods.getTextFromElement(selectors.subtotal).replaceAll("[^\\d.]", "").toFloat

    assertEquals(sumCalculatedByCODE, sumCalculatedByAPP, 0)
  }
}
