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
export interface CrTableRow {
    mietNr: string;
    jahr: string;
    status: string;
    grund: string;
    fahrzeugeTotal: number;
    mieteGeplantTotal: number;
    mieteAbgerechnetTotal: number;
    anzahlPkw: number;
    anzahlLkw: number;
    mutatedAt: Date;
    mutatedBy: string;
    mahnstop: boolean;
}

export interface CrDetail {
    id: number;
    changeable: boolean;
    mieteNr: string;
    jahr: string;
    crTableRow: CrTableRow;
    crPeriods: CrPeriod[];
    crMessages: CrMessage[];
}

export interface CrPeriod {
    id: number;
    from: Date;
    to: Date;
    crPortfolios: CrPortfolio[];    
}

export interface CrPortfolio {
    id: number;  
    bezeichnung: string; 
    anzahlPkw: number;
    anzahlLkw: number;
    anzahlTotal: number;
    mieteGeplantPkw: number;
    mieteGeplantLkw: number;
    mieteGeplantTotal: number;
    mieteAbgerechnetPkw: number;
    mieteAbgerechnetLkw: number;
    mieteAbgerechnetTotal: number;
}

export interface CrMessage {
    id: number;
    msgType: string;
    msg: string;  
}

export interface CrLogMsg {
    logLevel: string;
    logMsg: string;
}
