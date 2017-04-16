import { TRANSLATIONS, TRANSLATIONS_FORMAT, LOCALE_ID } from '@angular/core';


export function getTranslationProviders(): Promise<Object[]> {
  // Get the locale id from the global
  const locale = navigator.language.split('-')[0] as string;  
  console.log("locale:"+locale);
  // return no providers if fail to get translation file for locale
  const noProviders: Object[] = [];
  // No locale or 'de': no translation providers
  if (!locale || locale === 'de') {
    return Promise.resolve(noProviders);
  }
  // Ex: 'locale/messages.es.xlf`
  return getTranslationFilesWithWebpack(locale)
    .then( (translations: string ) => [
      { provide: TRANSLATIONS, useValue: translations },
      { provide: TRANSLATIONS_FORMAT, useValue: 'xlf' },
      { provide: LOCALE_ID, useValue: locale }
    ])
   .catch(() => noProviders); // ignore if file not found
}

declare var require: any;

function getTranslationFilesWithWebpack(locale: string) : Promise<string> {
    return Promise.resolve(require('raw-loader!'+'./locale/messages.'+locale+'.xlf'));    
}
