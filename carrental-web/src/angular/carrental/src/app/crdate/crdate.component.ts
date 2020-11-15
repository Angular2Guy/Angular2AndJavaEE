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
import { Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-crdate',
  templateUrl: './crdate.component.html',
  styleUrls: ['./crdate.component.scss']
})
export class CrdateComponent implements OnInit {

  constructor() { }
  @Input() myobject: any;
  @Input() myvalue: string;
  @Input() mytype: string;
  newDate: number;
  newString: string;

  ngOnInit() {
      if(!this.myobject) {return;} //for the tests
      if(!this.mytype) {
          this.newDate = this.myobject[this.myvalue];
      } else {
          this.newString = this.myobject[this.myvalue];
//          console.log(this.newString);
      }
  }

  setStr(event: any): void {
      this.myobject[this.myvalue] = event.target.value;
      //console.log(event.target.value);
  }

  convertToDate(event: any): void {
     const myDate = this.myobject[this.myvalue];
     console.log(this.myvalue+': '+myDate);
     const dateStr = event.target.value;
     const dtStr = dateStr.split('-');
     const newStr = dtStr[1]+'/'+dtStr[2]+'/'+dtStr[0];
     const newDate = new Date(newStr);
     //console.log(newStr);
     //console.log(newDate.getTime());
     this.myobject[this.myvalue] = newDate.getTime();
     this.newDate = newDate.getTime();
  }

}
