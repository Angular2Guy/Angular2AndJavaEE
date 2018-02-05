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
import { CrTableRow, CrDetail, CrPeriod, CrPortfolio, CrMessage } from './crTypes';

export class CrTableRowImpl implements CrTableRow {
    constructor(
            public mietNr: string,
            public jahr: string,
            public status: string,
            public grund: string,
            public fahrzeugeTotal: number,
            public mieteGeplantTotal: number,
            public mieteAbgerechnetTotal: number,
            public anzahlPkw: number,
            public anzahlLkw: number,
            public mutatedAt: Date,
            public mutatedBy: string,
            public mahnstop: boolean
    ) {}
}

export class CrDetailImpl implements CrDetail {
    
    constructor(
            public id: number,
            public changeable: boolean, 
            public mieteNr: string, 
            public jahr: string, 
            public crTableRow: CrTableRow, 
            public crPeriods: CrPeriod[], 
            public crMessages: CrMessage[]) {}
}

export class CrPeriodImpl implements CrPeriod {
    constructor(
            public id: number,
            public from: Date,
            public to: Date,
            public crPortfolios: CrPortfolio[]) {}
}

export class CrPortfolioImpl implements CrPortfolio {
    constructor(
            public id: number,  
            public bezeichnung: string, 
            public anzahlPkw: number,
            public anzahlLkw: number,
            public anzahlTotal: number,
            public mieteGeplantPkw: number,
            public mieteGeplantLkw: number,
            public mieteGeplantTotal: number,
            public mieteAbgerechnetPkw: number,
            public mieteAbgerechnetLkw: number,
            public mieteAbgerechnetTotal: number) {}
}

export class CrMessageImpl implements CrMessage {
    constructor(
            public id: number,
            public msgType: string,
            public msg: string) {}
}