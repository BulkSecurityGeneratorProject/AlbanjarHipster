import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { AlbanjarCustomerAlbanjarComponent } from './albanjar-customer-albanjar.component';
import { AlbanjarCustomerAlbanjarDetailComponent } from './albanjar-customer-albanjar-detail.component';
import { AlbanjarCustomerAlbanjarPopupComponent } from './albanjar-customer-albanjar-dialog.component';
import { AlbanjarCustomerAlbanjarDeletePopupComponent } from './albanjar-customer-albanjar-delete-dialog.component';

export const albanjarCustomerRoute: Routes = [
    {
        path: 'albanjar-customer-albanjar',
        component: AlbanjarCustomerAlbanjarComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'albanjarHipsterApp.albanjarCustomer.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'albanjar-customer-albanjar/:id',
        component: AlbanjarCustomerAlbanjarDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'albanjarHipsterApp.albanjarCustomer.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const albanjarCustomerPopupRoute: Routes = [
    {
        path: 'albanjar-customer-albanjar-new',
        component: AlbanjarCustomerAlbanjarPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'albanjarHipsterApp.albanjarCustomer.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'albanjar-customer-albanjar/:id/edit',
        component: AlbanjarCustomerAlbanjarPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'albanjarHipsterApp.albanjarCustomer.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'albanjar-customer-albanjar/:id/delete',
        component: AlbanjarCustomerAlbanjarDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'albanjarHipsterApp.albanjarCustomer.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
