import { CarrentalPage } from './app.po';

describe('carrental App', function() {
  let page: LsdeklPage;

  beforeEach(() => {
    page = new LsdeklPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
