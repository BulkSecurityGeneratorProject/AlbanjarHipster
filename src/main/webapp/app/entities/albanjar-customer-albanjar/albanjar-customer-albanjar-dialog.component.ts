import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AlbanjarCustomerAlbanjar } from './albanjar-customer-albanjar.model';
import { AlbanjarCustomerAlbanjarPopupService } from './albanjar-customer-albanjar-popup.service';
import { AlbanjarCustomerAlbanjarService } from './albanjar-customer-albanjar.service';

@Component({
    selector: 'jhi-albanjar-customer-albanjar-dialog',
    templateUrl: './albanjar-customer-albanjar-dialog.component.html'
})
export class AlbanjarCustomerAlbanjarDialogComponent implements OnInit {

    albanjarCustomer: AlbanjarCustomerAlbanjar;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private albanjarCustomerService: AlbanjarCustomerAlbanjarService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.albanjarCustomer.id !== undefined) {
            this.subscribeToSaveResponse(
                this.albanjarCustomerService.update(this.albanjarCustomer));
        } else {
            this.subscribeToSaveResponse(
                this.albanjarCustomerService.create(this.albanjarCustomer));
        }
    }

    private subscribeToSaveResponse(result: Observable<AlbanjarCustomerAlbanjar>) {
        result.subscribe((res: AlbanjarCustomerAlbanjar) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AlbanjarCustomerAlbanjar) {
        this.eventManager.broadcast({ name: 'albanjarCustomerListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-albanjar-customer-albanjar-popup',
    template: ''
})
export class AlbanjarCustomerAlbanjarPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private albanjarCustomerPopupService: AlbanjarCustomerAlbanjarPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.albanjarCustomerPopupService
                    .open(AlbanjarCustomerAlbanjarDialogComponent as Component, params['id']);
            } else {
                this.albanjarCustomerPopupService
                    .open(AlbanjarCustomerAlbanjarDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
