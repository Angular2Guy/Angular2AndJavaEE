 import { TRANSLATIONS, TRANSLATIONS_FORMAT, LOCALE_ID } from '@angular/core';

 export class TranslationProviders {

   public getTranslationFile = (): Promise<any> => {
     let noProviders: Object[] = [];

     // Define a way to retrieve the local information
     let userLang = navigator.language.split('-')[0]; // use navigator lang if available
     let locale = /(de|fr|it|en)/gi.test(userLang) ? userLang : 'en';     

     // Set the directory to the translation files
     let file: string = `../locale/messages.${locale}.xlf`;

     if(!locale || locale === 'en') return Promise.resolve(noProviders);

     return new Promise(function (resolve, reject) {
       let xhr = new XMLHttpRequest;
       xhr.open('GET', file);
       xhr.onload = (data: any) => resolve(
         [
           { provide: TRANSLATIONS, useValue: data.target.response },
           { provide: TRANSLATIONS_FORMAT, useValue: 'xlf' },
           { provide: LOCALE_ID, useValue: locale }
         ]
       );
       xhr.onerror = () => reject(noProviders);
       xhr.send();
     });
   } 
 };
