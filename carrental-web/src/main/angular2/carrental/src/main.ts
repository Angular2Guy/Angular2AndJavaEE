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

import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import * as translateFn from './i18n-providers';
import { enableProdMode } from '@angular/core';
import { environment } from './environments/environment';
import { AppModule } from './app/app.module';

if (environment.production) {
  enableProdMode();
}

//bootstrap for Angular i18n with Jit Compiler
translateFn.getTranslationProviders().then(providers => {
    const options = { providers };
    platformBrowserDynamic().bootstrapModule(AppModule, options);
  });

//platformBrowserDynamic().bootstrapModule(AppModule);
