import './vendor.ts';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2Webstorage } from 'ngx-webstorage';

import { AlbanjarHipsterSharedModule, UserRouteAccessService } from './shared';
import { AlbanjarHipsterAppRoutingModule} from './app-routing.module';
import { AlbanjarHipsterHomeModule } from './home/home.module';
import { AlbanjarHipsterAdminModule } from './admin/admin.module';
import { AlbanjarHipsterAccountModule } from './account/account.module';
import { AlbanjarHipsterEntityModule } from './entities/entity.module';
import { customHttpProvider } from './blocks/interceptor/http.provider';
import { PaginationConfig } from './blocks/config/uib-pagination.config';

// jhipster-needle-angular-add-module-import JHipster will add new module here

import {
    JhiMainComponent,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ActiveMenuDirective,
    ErrorComponent
} from './layouts';

@NgModule({
    imports: [
        BrowserModule,
        AlbanjarHipsterAppRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        AlbanjarHipsterSharedModule,
        AlbanjarHipsterHomeModule,
        AlbanjarHipsterAdminModule,
        AlbanjarHipsterAccountModule,
        AlbanjarHipsterEntityModule,
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        ActiveMenuDirective,
        FooterComponent
    ],
    providers: [
        ProfileService,
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService
    ],
    bootstrap: [ JhiMainComponent ]
})
export class AlbanjarHipsterAppModule {}
