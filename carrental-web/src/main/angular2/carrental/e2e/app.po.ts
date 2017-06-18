import { browser, by, element, By, $, $$, ExpectedConditions } from 'protractor';

export class TestprojectPage {
  navigateTo() {
    return browser.get('/');
  }

  getParagraphText() {
    return element(by.css('app-root h1')).getText();
  }
  
  navigateToList() {
      return element(by.id('myList')).click();      
  }
  
  getParagraphTextList() {
      return element(by.id('myListId')).getText();
  }
  
  async getListTableLength() {
      let table = element.all(by.repeater('let row of tableRows | async'));
      if((await table).length > 0) {          
          return (await table).length;
      }      
  }
}