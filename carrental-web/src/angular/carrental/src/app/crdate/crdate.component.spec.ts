import { ComponentFixture, TestBed, waitForAsync } from "@angular/core/testing";

import { CrdateComponent } from "./crdate.component";

describe("CrdateComponent", () => {
  let component: CrdateComponent;
  let fixture: ComponentFixture<CrdateComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [CrdateComponent],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
