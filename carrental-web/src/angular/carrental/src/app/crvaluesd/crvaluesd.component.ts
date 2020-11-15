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
import { Component, OnInit, Input } from '@angular/core';
import { CrPortfolio } from '../dtos/crTypes';
import { NumberSeparatorPipe } from '../shared/number-separator.pipe';
import { Utils } from '../shared/utils';


@Component({
  selector: 'app-crvaluesd',
  templateUrl: './crvaluesd.component.html',
  styleUrls: ['./crvaluesd.component.scss']
})
export class CrvaluesdComponent implements OnInit {
  @Input() crvalues: CrPortfolio;
  constructor() { }

  ngOnInit() {
      this.crvalues.anzahlTotal = this.crvalues.anzahlLkw + this.crvalues.anzahlPkw;
      const abgPkw = Utils.removeSeparators( this.crvalues.mieteAbgerechnetPkw ? this.crvalues.mieteAbgerechnetPkw.toString() : '0');
      const abgLkw = Utils.removeSeparators( this.crvalues.mieteAbgerechnetLkw ? this.crvalues.mieteAbgerechnetLkw.toString() : '0');
      this.crvalues.mieteAbgerechnetTotal = abgPkw + abgLkw;
      const gplPkw = Utils.removeSeparators( this.crvalues.mieteGeplantPkw ? this.crvalues.mieteGeplantPkw.toString() : '0');
      const gplLkw = Utils.removeSeparators( this.crvalues.mieteGeplantLkw ? this.crvalues.mieteGeplantLkw.toString() : '0');
      this.crvalues.mieteGeplantTotal = gplPkw + gplLkw;
  }

}
