import { Component, OnInit, Input } from '@angular/core';
import { CrPortfolio } from '../crTypes';


@Component({
  selector: 'app-crvaluesd',
  templateUrl: './crvaluesd.component.html',
  styleUrls: ['./crvaluesd.component.css']
})
export class CrvaluesdComponent implements OnInit {
  @Input() crvalues: CrPortfolio;
  constructor() { }

  ngOnInit() {
      this.crvalues.anzahlTotal = this.crvalues.anzahlLkw + this.crvalues.anzahlPkw;
      this.crvalues.mieteAbgerechnetTotal = this.crvalues.mieteAbgerechnetPkw + this.crvalues.mieteAbgerechnetLkw;
      this.crvalues.mieteGeplantTotal = this.crvalues.mieteGeplantPkw + this.crvalues.mieteGeplantLkw;
  }

}
