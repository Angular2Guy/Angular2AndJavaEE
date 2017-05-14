import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CrdateComponent } from './crdate.component';

describe('CrdateComponent', () => {
  let component: CrdateComponent;
  let fixture: ComponentFixture<CrdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
