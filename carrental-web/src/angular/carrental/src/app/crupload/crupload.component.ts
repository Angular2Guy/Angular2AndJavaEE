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

import {
  Component,
  OnInit,
  Input,
  OnChanges,
  SimpleChange,
} from "@angular/core";

@Component({
    selector: "app-crupload",
    templateUrl: "./crupload.component.html",
    styleUrls: ["./crupload.component.scss"],
    standalone: false
})
export class CruploadComponent implements OnInit, OnChanges {
  @Input() visible: boolean;
  protected currentFile: File;
  protected filetext: string;

  constructor() {}

  filechange(fileInput: any) {
    this.currentFile = fileInput.target.files[0];

    const reader = new FileReader();
    reader.onload = () => {
      const textstr: string = <string>reader.result;
      const textstrs = textstr.split("base64,");
      this.filetext = atob(textstrs[1]);
      //console.log(textstr);
    };
    reader.readAsDataURL(this.currentFile);
  }

  ngOnInit() {
    const modal = document.getElementById("crModal");
    modal.style.display = this.visible ? "block" : "none";
  }

  ngOnChanges(changes: { [propKey: string]: SimpleChange }): void {
    for (const propName in changes) {
      const changedProp = changes[propName];
      console.log(propName + " " + changedProp.currentValue);
      const modal = document.getElementById("crModal");
      modal.style.display = "block";
    }
  }

  close() {
    const modal = document.getElementById("crModal");
    modal.style.display = "none";
  }
}
