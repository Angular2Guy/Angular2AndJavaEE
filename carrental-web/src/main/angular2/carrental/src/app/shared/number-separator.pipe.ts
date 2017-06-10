import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'numberSeparator'
})
export class NumberSeparatorPipe implements PipeTransform {

  transform(value: string, args?: string): string {
      if(!isNaN(+value) && !isNaN(+args[0])) {          
          let digits = Number(args);
          let arr = [];                                 
          while(value.length > digits) {
              let str = value.slice(value.length-digits, value.length);
              arr.push("'"+str);
              value = value.slice(0,value.length-digits);
          }           
          arr.reverse();
          let result = "" + value +arr;          
          return result.replace(",","");
      }          
      return value;
  }

}
