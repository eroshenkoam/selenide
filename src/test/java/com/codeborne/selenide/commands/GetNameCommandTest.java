package com.codeborne.selenide.commands;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetNameCommandTest {
  private SelenideElement proxy;
  private WebElementSource locator;
  private SelenideElement mockedElement;
  private GetName getNameCommand;

  @BeforeEach
  void setup() {
    getNameCommand = new GetName();
    proxy = mock(SelenideElement.class);
    locator = mock(WebElementSource.class);
    mockedElement = mock(SelenideElement.class);
    when(locator.getWebElement()).thenReturn(mockedElement);
  }

  @Test
  void testExecuteMethod() {
    String argument = "class";
    String elementAttribute = "hello";
    when(mockedElement.getAttribute("name")).thenReturn(elementAttribute);
    Assertions.assertEquals(elementAttribute, getNameCommand.execute(proxy, locator, new Object[]{argument, "something more"}));
  }
}
