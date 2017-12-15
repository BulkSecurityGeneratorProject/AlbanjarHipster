import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AlbanjarHipsterSharedModule } from '../../shared';
import {
    AlbanjarDeliveryAlbanjarService,
    AlbanjarDeliveryAlbanjarPopupService,
    AlbanjarDeliveryAlbanjarComponent,
    AlbanjarDeliveryAlbanjarDetailComponent,
    AlbanjarDeliveryAlbanjarDialogComponent,
    AlbanjarDeliveryAlbanjarPopupComponent,
    AlbanjarDeliveryAlbanjarDeletePopupComponent,
    AlbanjarDeliveryAlbanjarDeleteDialogComponent,
    albanjarDeliveryRoute,
    albanjarDeliveryPopupRoute,
    AlbanjarDeliveryAlbanjarResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...albanjarDeliveryRoute,
    ...albanjarDeliveryPopupRoute,
];

@NgModule({
    imports: [
        AlbanjarHipsterSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        AlbanjarDeliveryAlbanjarComponent,
        AlbanjarDeliveryAlbanjarDetailComponent,
        AlbanjarDeliveryAlbanjarDialogComponent,
        AlbanjarDeliveryAlbanjarDeleteDialogComponent,
        AlbanjarDeliveryAlbanjarPopupComponent,
        AlbanjarDeliveryAlbanjarDeletePopupComponent,
    ],
    entryComponents: [
        AlbanjarDeliveryAlbanjarComponent,
        AlbanjarDeliveryAlbanjarDialogComponent,
        AlbanjarDeliveryAlbanjarPopupComponent,
        AlbanjarDeliveryAlbanjarDeleteDialogComponent,
        AlbanjarDeliveryAlbanjarDeletePopupComponent,
    ],
    providers: [
        AlbanjarDeliveryAlbanjarService,
        AlbanjarDeliveryAlbanjarPopupService,
        AlbanjarDeliveryAlbanjarResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AlbanjarHipsterAlbanjarDeliveryAlbanjarModule {}
