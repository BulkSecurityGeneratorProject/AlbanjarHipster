import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AlbanjarHipsterAlbanjarCustomerAlbanjarModule } from './albanjar-customer-albanjar/albanjar-customer-albanjar.module';
import { AlbanjarHipsterAlbanjarProductAlbanjarModule } from './albanjar-product-albanjar/albanjar-product-albanjar.module';
import { AlbanjarHipsterAlbanjarDeliveryAlbanjarModule } from './albanjar-delivery-albanjar/albanjar-delivery-albanjar.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        AlbanjarHipsterAlbanjarCustomerAlbanjarModule,
        AlbanjarHipsterAlbanjarProductAlbanjarModule,
        AlbanjarHipsterAlbanjarDeliveryAlbanjarModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AlbanjarHipsterEntityModule {}
