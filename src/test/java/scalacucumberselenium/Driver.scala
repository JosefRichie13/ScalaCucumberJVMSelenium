package scalacucumberselenium

import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.{By, WebDriver, WebElement}

import scala.jdk.CollectionConverters._



class Driver {

  def loadAURL(url: String): Unit ={
    webDriver.driver.get(url)
  }

  def typeText(textToType: String, element: By): Unit ={
    webDriver.driver.findElement(element).sendKeys(textToType)
  }

  def clickButton(element: By): Unit ={
    webDriver.driver.findElement(element).click()
  }

  def getTextFromElement(element: By): String ={
    webDriver.driver.findElement(element).getText
  }

  def getSpecificTextFromAListOfElements(element: By, index: Int): String ={
    webDriver.driver.findElements(element).get(index).getText
  }

  def getAllTextFromAListOfElements(element: By): List[String] = {
    val elements = webDriver.driver.findElements(element).asScala.toList
    elements.map(_.getText)
  }

  def selectFromDropdownUsingText(element: By, selectOptionInText: String): Unit ={
    val elementToSelect: WebElement = webDriver.driver.findElement(element)
    val select: Select = new Select(elementToSelect)
    select.selectByVisibleText(selectOptionInText)
  }

  // Checks if an element is present in the DOM or not
  // Returns FALSE if element is NOT visible, which means the size will be 0
  // Returns TRUE if element is visible, which means the size will be greater than 0
  def elementVisibleOrNot(element: By): Boolean ={
    val status = webDriver.driver.findElements(element).size()
    if(status == 0){
      false
    }
    else {
      true
    }
  }

  def getTheCurrentURL: String ={
    webDriver.driver.getCurrentUrl
  }

  def switchBetweenTabs(tabNumber: Int): WebDriver = {
    val setOfHandleIDS = webDriver.driver.getWindowHandles
    val listOfHandleIDS = setOfHandleIDS.toArray(new Array[String](0)).toList
    webDriver.driver.switchTo().window(listOfHandleIDS(tabNumber))
  }

}
