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

  Then("""the sort {string} should work correctly"""){ (sortType: String) =>
    sortType match {
      /*
      We get the list of prices from the home page, remove the $ sign and convert the prices to floats
      and store them in a List, noSortPricesInFloatWithoutSign
      We then sort it Descending and store it in an List, sortedPricesHighToLow
      We then select the Price (high to low) option from the dropdown, repeat step 1
      We end up with prices sorted by the application in an List, priceAfterSortByUI
      Finally we compare both Lists if they are the same
      */
      case "Price (high to low)" =>
        val noSortPricesInFloatWithoutSign = driverMethods.getAllTextFromAListOfElements(selectors.priceList).map(_.substring(1)).map(_.toFloat)
        val sortedPricesHighToLow = noSortPricesInFloatWithoutSign.sorted.reverse
        driverMethods.selectFromDropdownUsingText(selectors.productSort, sortType)
        val priceAfterSortByUI = driverMethods.getAllTextFromAListOfElements(selectors.priceList).map(_.substring(1)).map(_.toFloat)
        assertEquals(sortedPricesHighToLow, priceAfterSortByUI)

      case "Price (low to high)" =>
        val noSortPricesInFloatWithoutSign = driverMethods.getAllTextFromAListOfElements(selectors.priceList).map(_.substring(1)).map(_.toFloat)
        val sortedPricesLowToHigh = noSortPricesInFloatWithoutSign.sorted
        driverMethods.selectFromDropdownUsingText(selectors.productSort, sortType)
        val priceAfterSortByUI = driverMethods.getAllTextFromAListOfElements(selectors.priceList).map(_.substring(1)).map(_.toFloat)
        assertEquals(sortedPricesLowToHigh, priceAfterSortByUI)

      /*
      We get the names of products from the home page and store them in a List, noSortNames
      We then sort it Descending and store it in a List, sortedNamesZtoA
      We then select the Name (Z to A) option from the dropdown
      We end up with prices sorted by the application in a List, namesAfterSortByUI
      Finally we compare both Lists if they are the same
      */
      case "Name (Z to A)" =>
        val noSortNames = driverMethods.getAllTextFromAListOfElements(selectors.productList)
        val sortedNamesZtoA = noSortNames.sorted.reverse
        driverMethods.selectFromDropdownUsingText(selectors.productSort, sortType)
        val namesAfterSortByUI = driverMethods.getAllTextFromAListOfElements(selectors.productList)
        assertEquals(sortedNamesZtoA, namesAfterSortByUI)

      case "Name (A to Z)" =>
        val noSortNames = driverMethods.getAllTextFromAListOfElements(selectors.productList)
        val sortedNamesAtoZ = noSortNames.sorted
        driverMethods.selectFromDropdownUsingText(selectors.productSort, sortType)
        val namesAfterSortByUI = driverMethods.getAllTextFromAListOfElements(selectors.productList)
        assertEquals(sortedNamesAtoZ, namesAfterSortByUI)

      case _ =>
        throw new IllegalArgumentException("Incorrect sort option : " + sortType)
    }
  }

}
