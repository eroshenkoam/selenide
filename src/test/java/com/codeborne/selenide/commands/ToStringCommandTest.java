package com.codeborne.selenide.commands;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.impl.WebElementSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ToStringCommandTest {
  private SelenideElement proxy;
  private WebElementSource locator;
  private ToString toStringCommand;
  private WebElement mockedFoundElement;

  @BeforeEach
  void setup() {
    toStringCommand = new ToString();
    proxy = mock(SelenideElement.class);
    locator = mock(WebElementSource.class);
    mockedFoundElement = mock(WebElement.class);
    when(locator.getWebElement()).thenReturn(mockedFoundElement);
  }

  @Test
  void testExecuteMethod() {
    when(mockedFoundElement.isSelected()).thenReturn(true);
    when(mockedFoundElement.isDisplayed()).thenReturn(true);
    String elementText = "text";
    when(mockedFoundElement.getText()).thenReturn(elementText);
    String elementString = toStringCommand.execute(proxy, locator, new Object[]{});
    Assertions.assertEquals("<null selected:true>text</null>", elementString);
  }

  @Test
  void testExecuteMethodWhenWebDriverExceptionIsThrown() {
    doThrow(new WebDriverException()).when(locator).getWebElement();
    String elementString = toStringCommand.execute(proxy, locator, new Object[]{});
    Assertions.assertTrue(elementString.contains("WebDriverException"));
  }

  @Test
  void testExecuteMethodWhenElementNotFoundIsThrown() {
    doThrow(new ElementNotFound(By.name(""), Condition.visible)).when(locator).getWebElement();
    String elementString = toStringCommand.execute(proxy, locator, new Object[]{});
    Assertions.assertEquals("Element not found {By.name: }", elementString);
  }

  @Test
  void testExecuteMethodWhenIndexOutOfBoundExceptionIsThrown() {
    doThrow(new IndexOutOfBoundsException()).when(locator).getWebElement();
    String elementString = toStringCommand.execute(proxy, locator, new Object[]{});
    Assertions.assertEquals("java.lang.IndexOutOfBoundsException", elementString);
  }
}
