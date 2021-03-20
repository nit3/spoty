import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { SpotySharedModule } from 'app/shared/shared.module';
import { SpotyCoreModule } from 'app/core/core.module';
import { SpotyAppRoutingModule } from './app-routing.module';
import { SpotyHomeModule } from './home/home.module';
import { SpotyEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    SpotySharedModule,
    SpotyCoreModule,
    SpotyHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    SpotyEntityModule,
    SpotyAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class SpotyAppModule {}
