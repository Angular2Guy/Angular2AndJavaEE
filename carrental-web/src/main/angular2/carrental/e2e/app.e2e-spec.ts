import { TestprojectPage } from './app.po';

describe('testproject App', function() {
  let page: TestprojectPage;

  beforeEach(() => {
    page = new TestprojectPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});