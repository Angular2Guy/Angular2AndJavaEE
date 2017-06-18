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
  
  getListTableLength() {      
      let elems =  by.exactRepeater('let row of tableRows | async');      
      let table = element.all(elems);
      return table;            
  }
}