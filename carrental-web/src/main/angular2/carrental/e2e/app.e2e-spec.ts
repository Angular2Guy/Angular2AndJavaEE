import { CarrentalPage } from './app.po';

describe('carrental App', function() {
  let page: CarrentalPage;

  beforeEach(() => {
    page = new CarrentalPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
