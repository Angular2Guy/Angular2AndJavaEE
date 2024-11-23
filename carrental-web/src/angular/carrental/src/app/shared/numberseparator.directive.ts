import { Directive, ElementRef, HostListener } from "@angular/core";
import { NgControl } from "@angular/forms";
import { NumberSeparatorPipe } from "./number-separator.pipe";

@Directive({
    selector: "[myNumberseparator]",
    standalone: false
})
export class NumberseparatorDirective {
  constructor(private el: ElementRef, private control: NgControl) {}

  @HostListener("input", ["onchange"]) onEvent() {
    let myNumber = this.el.nativeElement.value;
    //        console.log("myNumber: "+myNumber);
    const pipe = new NumberSeparatorPipe();
    myNumber = pipe.transform(myNumber, 3);
    this.control.control.setValue(myNumber);
  }
}
