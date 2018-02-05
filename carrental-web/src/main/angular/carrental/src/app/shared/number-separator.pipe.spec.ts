/**
 *    Copyright 2016 Sven Loesekann

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
import { NumberSeparatorPipe } from './number-separator.pipe';
  
describe('NumberSeparatorPipe', () => {
    it('create an instance', () => {
        const pipe = new NumberSeparatorPipe(); 
        let result = pipe.transform('100', '3');
        expect(result).toBe("100");
      });
    it('create an instance', () => {
        const pipe = new NumberSeparatorPipe(); 
        let result = pipe.transform('1000', '3');
        expect(result).toBe("1'000");
      });
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
  it('create an instance', () => {
      const pipe = new NumberSeparatorPipe(); 
      let result = pipe.transform('1a', '3');
      expect(result).toBe("1a");
    });
  it('create an instance', () => {
      const pipe = new NumberSeparatorPipe(); 
      let result = pipe.transform("1'000'000", '3');
      expect(result).toBe("1'000'000");
    });
  it('create an instance', () => {
      const pipe = new NumberSeparatorPipe(); 
      let result = pipe.transform(null, '3');
      expect(result).toBe("0");
    });
});
