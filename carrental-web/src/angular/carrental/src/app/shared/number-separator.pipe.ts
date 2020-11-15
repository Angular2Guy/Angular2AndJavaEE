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
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'numberSeparator'
})
export class NumberSeparatorPipe implements PipeTransform {

  transform(value: string | number, args?: number): string {
      if(!(value)) {
          return '0';
      }
      value = String(value).replace(/'/g,'').replace(/,/g,'');
      //console.log("value: "+value);
      if(!isNaN(parseInt(value)) && !isNaN(args)) {
          const digits = args;
          const arr = [];
          let myValue = String(value);
          while(myValue.length > digits) {
              const str = myValue.slice(myValue.length-digits, myValue.length);
              arr.push('\''+str);
              myValue = myValue.slice(0,myValue.length-digits);
          }
          arr.reverse();
          let result = '' + myValue +arr;
          result = result.replace(',','');
//          console.log("result1 "+result);
          return result;
      }
//      console.log("result2 "+value);
      return value;
  }

}
