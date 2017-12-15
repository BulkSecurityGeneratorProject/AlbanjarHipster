import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AlbanjarHipsterSharedModule } from '../../shared';
import {
    AlbanjarCustomerAlbanjarService,
    AlbanjarCustomerAlbanjarPopupService,
    AlbanjarCustomerAlbanjarComponent,
    AlbanjarCustomerAlbanjarDetailComponent,
    AlbanjarCustomerAlbanjarDialogComponent,
    AlbanjarCustomerAlbanjarPopupComponent,
    AlbanjarCustomerAlbanjarDeletePopupComponent,
    AlbanjarCustomerAlbanjarDeleteDialogComponent,
    albanjarCustomerRoute,
    albanjarCustomerPopupRoute,
} from './';

const ENTITY_STATES = [
    ...albanjarCustomerRoute,
    ...albanjarCustomerPopupRoute,
];

@NgModule({
    imports: [
        AlbanjarHipsterSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        AlbanjarCustomerAlbanjarComponent,
        AlbanjarCustomerAlbanjarDetailComponent,
        AlbanjarCustomerAlbanjarDialogComponent,
        AlbanjarCustomerAlbanjarDeleteDialogComponent,
        AlbanjarCustomerAlbanjarPopupComponent,
        AlbanjarCustomerAlbanjarDeletePopupComponent,
    ],
    entryComponents: [
        AlbanjarCustomerAlbanjarComponent,
        AlbanjarCustomerAlbanjarDialogComponent,
        AlbanjarCustomerAlbanjarPopupComponent,
        AlbanjarCustomerAlbanjarDeleteDialogComponent,
        AlbanjarCustomerAlbanjarDeletePopupComponent,
    ],
    providers: [
        AlbanjarCustomerAlbanjarService,
        AlbanjarCustomerAlbanjarPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AlbanjarHipsterAlbanjarCustomerAlbanjarModule {}
