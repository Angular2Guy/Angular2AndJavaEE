import { CarrentalPage } from './app.po';

describe('carrental App', function() {
  let page: CarrentalPage;

  beforeEach(() => {
    page = new LsdeklPage();
  })

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('carrental works!');
  });
});
