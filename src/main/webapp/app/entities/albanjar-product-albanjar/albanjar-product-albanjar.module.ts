import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AlbanjarHipsterSharedModule } from '../../shared';
import {
    AlbanjarProductAlbanjarService,
    AlbanjarProductAlbanjarPopupService,
    AlbanjarProductAlbanjarComponent,
    AlbanjarProductAlbanjarDetailComponent,
    AlbanjarProductAlbanjarDialogComponent,
    AlbanjarProductAlbanjarPopupComponent,
    AlbanjarProductAlbanjarDeletePopupComponent,
    AlbanjarProductAlbanjarDeleteDialogComponent,
    albanjarProductRoute,
    albanjarProductPopupRoute,
} from './';

const ENTITY_STATES = [
    ...albanjarProductRoute,
    ...albanjarProductPopupRoute,
];

@NgModule({
    imports: [
        AlbanjarHipsterSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        AlbanjarProductAlbanjarComponent,
        AlbanjarProductAlbanjarDetailComponent,
        AlbanjarProductAlbanjarDialogComponent,
        AlbanjarProductAlbanjarDeleteDialogComponent,
        AlbanjarProductAlbanjarPopupComponent,
        AlbanjarProductAlbanjarDeletePopupComponent,
    ],
    entryComponents: [
        AlbanjarProductAlbanjarComponent,
        AlbanjarProductAlbanjarDialogComponent,
        AlbanjarProductAlbanjarPopupComponent,
        AlbanjarProductAlbanjarDeleteDialogComponent,
        AlbanjarProductAlbanjarDeletePopupComponent,
    ],
    providers: [
        AlbanjarProductAlbanjarService,
        AlbanjarProductAlbanjarPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AlbanjarHipsterAlbanjarProductAlbanjarModule {}
