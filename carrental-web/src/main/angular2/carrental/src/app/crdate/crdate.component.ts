import { Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-crdate',
  templateUrl: './crdate.component.html',
  styleUrls: ['./crdate.component.css']
})
export class CrdateComponent implements OnInit {

  constructor() { }
  @Input() myobject: any;
  @Input() myvalue: string;
  @Input() mytype: string;
  newDate: number;
  newString: string;
  
  ngOnInit() {
      if(!this.mytype) {
          this.newDate = this.myobject[this.myvalue];          
      } else {
          this.newString = this.myobject[this.myvalue];
          console.log(this.newString);
      }
  }
  
  setStr(event: any) : void {
      this.myobject[this.myvalue] = event.target.value;
  }
  
  convertToDate(event: any) : void {
     let myDate = this.myobject[this.myvalue];
     console.log(myDate);
     let dateStr = event.target.value;
     let dtStr = dateStr.split("-");
     let newStr = dtStr[1]+"/"+dtStr[2]+"/"+dtStr[0];
     let newDate = new Date(newStr);
     //console.log(newStr);
     //console.log(newDate.getTime());
     this.myobject[this.myvalue] = newDate.getTime();
     this.newDate = newDate.getTime();
  }
  
} 
