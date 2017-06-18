import { TestprojectPage } from './app.po';
import { browser, by, element, By, $, $$, ExpectedConditions } from 'protractor';

describe('testproject App', () => {
  let page: TestprojectPage;

  beforeEach(() => {
    page = new TestprojectPage();
  });

  it('should display welcome message', done => {
    page.navigateTo();
    page.getParagraphText()
      .then(msg => expect(msg).toEqual('carrental works!'))
      .then(done, done.fail);
  });
  it('should display the list page', done => {
      page.navigateToList();
      page.getParagraphTextList()
          .then(msg => expect(msg).toEqual('crlist works!'))
          .then(done, done.fail);
  });
//  it('should display 2 lines in the table', done => {
//      
//      page.getListTableLength()
//         .then(length => expect(length).toEqual(2))
//         .then(done, done.fail);
//  });
});
