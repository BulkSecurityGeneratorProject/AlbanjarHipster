import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AlbanjarDeliveryAlbanjar } from './albanjar-delivery-albanjar.model';
import { AlbanjarDeliveryAlbanjarPopupService } from './albanjar-delivery-albanjar-popup.service';
import { AlbanjarDeliveryAlbanjarService } from './albanjar-delivery-albanjar.service';
import { AlbanjarCustomerAlbanjar, AlbanjarCustomerAlbanjarService } from '../albanjar-customer-albanjar';
import { AlbanjarProductAlbanjar, AlbanjarProductAlbanjarService } from '../albanjar-product-albanjar';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-albanjar-delivery-albanjar-dialog',
    templateUrl: './albanjar-delivery-albanjar-dialog.component.html'
})
export class AlbanjarDeliveryAlbanjarDialogComponent implements OnInit {

    albanjarDelivery: AlbanjarDeliveryAlbanjar;
    isSaving: boolean;

    albanjarcustomers: AlbanjarCustomerAlbanjar[];

    albanjarproducts: AlbanjarProductAlbanjar[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private albanjarDeliveryService: AlbanjarDeliveryAlbanjarService,
        private albanjarCustomerService: AlbanjarCustomerAlbanjarService,
        private albanjarProductService: AlbanjarProductAlbanjarService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.albanjarCustomerService.query()
            .subscribe((res: ResponseWrapper) => { this.albanjarcustomers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.albanjarProductService.query()
            .subscribe((res: ResponseWrapper) => { this.albanjarproducts = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.albanjarDelivery.id !== undefined) {
            this.subscribeToSaveResponse(
                this.albanjarDeliveryService.update(this.albanjarDelivery));
        } else {
            this.subscribeToSaveResponse(
                this.albanjarDeliveryService.create(this.albanjarDelivery));
        }
    }

    private subscribeToSaveResponse(result: Observable<AlbanjarDeliveryAlbanjar>) {
        result.subscribe((res: AlbanjarDeliveryAlbanjar) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AlbanjarDeliveryAlbanjar) {
        this.eventManager.broadcast({ name: 'albanjarDeliveryListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackAlbanjarCustomerById(index: number, item: AlbanjarCustomerAlbanjar) {
        return item.id;
    }

    trackAlbanjarProductById(index: number, item: AlbanjarProductAlbanjar) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-albanjar-delivery-albanjar-popup',
    template: ''
})
export class AlbanjarDeliveryAlbanjarPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private albanjarDeliveryPopupService: AlbanjarDeliveryAlbanjarPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.albanjarDeliveryPopupService
                    .open(AlbanjarDeliveryAlbanjarDialogComponent as Component, params['id']);
            } else {
                this.albanjarDeliveryPopupService
                    .open(AlbanjarDeliveryAlbanjarDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
