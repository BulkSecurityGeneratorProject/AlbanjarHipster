import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { AlbanjarDeliveryAlbanjarComponent } from './albanjar-delivery-albanjar.component';
import { AlbanjarDeliveryAlbanjarDetailComponent } from './albanjar-delivery-albanjar-detail.component';
import { AlbanjarDeliveryAlbanjarPopupComponent } from './albanjar-delivery-albanjar-dialog.component';
import { AlbanjarDeliveryAlbanjarDeletePopupComponent } from './albanjar-delivery-albanjar-delete-dialog.component';

@Injectable()
export class AlbanjarDeliveryAlbanjarResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const albanjarDeliveryRoute: Routes = [
    {
        path: 'albanjar-delivery-albanjar',
        component: AlbanjarDeliveryAlbanjarComponent,
        resolve: {
            'pagingParams': AlbanjarDeliveryAlbanjarResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'albanjarHipsterApp.albanjarDelivery.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'albanjar-delivery-albanjar/:id',
        component: AlbanjarDeliveryAlbanjarDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'albanjarHipsterApp.albanjarDelivery.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const albanjarDeliveryPopupRoute: Routes = [
    {
        path: 'albanjar-delivery-albanjar-new',
        component: AlbanjarDeliveryAlbanjarPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'albanjarHipsterApp.albanjarDelivery.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'albanjar-delivery-albanjar/:id/edit',
        component: AlbanjarDeliveryAlbanjarPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'albanjarHipsterApp.albanjarDelivery.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'albanjar-delivery-albanjar/:id/delete',
        component: AlbanjarDeliveryAlbanjarDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'albanjarHipsterApp.albanjarDelivery.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
