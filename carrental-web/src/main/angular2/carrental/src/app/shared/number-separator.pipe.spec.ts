import { NumberSeparatorPipe } from './number-separator.pipe';

describe('NumberSeparatorPipe', () => {
  it('create an instance', () => {
    const pipe = new NumberSeparatorPipe(); 
    let result = pipe.transform('10000', '3');
    expect(result).toBe("10'000");
  });
  
  it('create an instance', () => {
      const pipe = new NumberSeparatorPipe(); 
      let result = pipe.transform('10000000', '3');
      expect(result).toBe("10'000'000");
    });
  
  it('create an instance', () => {
      const pipe = new NumberSeparatorPipe(); 
      let result = pipe.transform('12345678', '3');
      expect(result).toBe("12'345'678");
    });
});
