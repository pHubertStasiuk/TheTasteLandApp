import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatBadgeModule } from '@angular/material/badge';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatChipsModule} from '@angular/material/chips';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { MatRippleModule } from '@angular/material/core';
import {MatInputModule, MatCheckboxModule} from '@angular/material';
import {MatTooltipModule} from '@angular/material/tooltip';


const modules = [
  MatButtonModule,
  MatRippleModule,
  CommonModule,
  MatCheckboxModule,
  MatBadgeModule,
  MatToolbarModule,
  MatCardModule,
  MatIconModule,
  MatChipsModule,
  MatFormFieldModule,
  MatInputModule,
  MatProgressSpinnerModule,
  MatTooltipModule
];


@NgModule({
  declarations: [],
  imports: [...modules],
  exports: [...modules]
})
export class MaterialModule {
}
