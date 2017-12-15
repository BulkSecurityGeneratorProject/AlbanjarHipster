import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { AlbanjarProductAlbanjarComponent } from './albanjar-product-albanjar.component';
import { AlbanjarProductAlbanjarDetailComponent } from './albanjar-product-albanjar-detail.component';
import { AlbanjarProductAlbanjarPopupComponent } from './albanjar-product-albanjar-dialog.component';
import { AlbanjarProductAlbanjarDeletePopupComponent } from './albanjar-product-albanjar-delete-dialog.component';

export const albanjarProductRoute: Routes = [
    {
        path: 'albanjar-product-albanjar',
        component: AlbanjarProductAlbanjarComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'albanjarHipsterApp.albanjarProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'albanjar-product-albanjar/:id',
        component: AlbanjarProductAlbanjarDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'albanjarHipsterApp.albanjarProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const albanjarProductPopupRoute: Routes = [
    {
        path: 'albanjar-product-albanjar-new',
        component: AlbanjarProductAlbanjarPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'albanjarHipsterApp.albanjarProduct.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'albanjar-product-albanjar/:id/edit',
        component: AlbanjarProductAlbanjarPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'albanjarHipsterApp.albanjarProduct.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'albanjar-product-albanjar/:id/delete',
        component: AlbanjarProductAlbanjarDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'albanjarHipsterApp.albanjarProduct.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
