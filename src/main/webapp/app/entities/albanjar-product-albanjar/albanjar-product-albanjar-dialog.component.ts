import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AlbanjarProductAlbanjar } from './albanjar-product-albanjar.model';
import { AlbanjarProductAlbanjarPopupService } from './albanjar-product-albanjar-popup.service';
import { AlbanjarProductAlbanjarService } from './albanjar-product-albanjar.service';

@Component({
    selector: 'jhi-albanjar-product-albanjar-dialog',
    templateUrl: './albanjar-product-albanjar-dialog.component.html'
})
export class AlbanjarProductAlbanjarDialogComponent implements OnInit {

    albanjarProduct: AlbanjarProductAlbanjar;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private albanjarProductService: AlbanjarProductAlbanjarService,
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
        if (this.albanjarProduct.id !== undefined) {
            this.subscribeToSaveResponse(
                this.albanjarProductService.update(this.albanjarProduct));
        } else {
            this.subscribeToSaveResponse(
                this.albanjarProductService.create(this.albanjarProduct));
        }
    }

    private subscribeToSaveResponse(result: Observable<AlbanjarProductAlbanjar>) {
        result.subscribe((res: AlbanjarProductAlbanjar) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AlbanjarProductAlbanjar) {
        this.eventManager.broadcast({ name: 'albanjarProductListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-albanjar-product-albanjar-popup',
    template: ''
})
export class AlbanjarProductAlbanjarPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private albanjarProductPopupService: AlbanjarProductAlbanjarPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.albanjarProductPopupService
                    .open(AlbanjarProductAlbanjarDialogComponent as Component, params['id']);
            } else {
                this.albanjarProductPopupService
                    .open(AlbanjarProductAlbanjarDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
