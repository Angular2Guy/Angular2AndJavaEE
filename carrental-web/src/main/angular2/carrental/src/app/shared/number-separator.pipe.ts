import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'numberSeparator'
})
export class NumberSeparatorPipe implements PipeTransform {

  transform(value: string, args?: string): string {      
      if(!isNaN(parseInt(value)) && !isNaN(parseInt(args))) {          
          let digits = parseInt(args);
          let arr = [];                                 
          let myValue = String(value);
          while(myValue.length > digits) {
              let str = myValue.slice(myValue.length-digits, myValue.length);
              arr.push("'"+str);
              myValue = myValue.slice(0,myValue.length-digits);
          }           
          arr.reverse();
          let result = "" + myValue +arr;          
          result = result.replace(",","");
//          console.log("result1 "+result);
          return result;
      }          
//      console.log("result2 "+value);
      return value;
  }

}
