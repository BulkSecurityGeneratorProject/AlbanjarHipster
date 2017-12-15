import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AlbanjarProductAlbanjar } from './albanjar-product-albanjar.model';
import { AlbanjarProductAlbanjarPopupService } from './albanjar-product-albanjar-popup.service';
import { AlbanjarProductAlbanjarService } from './albanjar-product-albanjar.service';

@Component({
    selector: 'jhi-albanjar-product-albanjar-delete-dialog',
    templateUrl: './albanjar-product-albanjar-delete-dialog.component.html'
})
export class AlbanjarProductAlbanjarDeleteDialogComponent {

    albanjarProduct: AlbanjarProductAlbanjar;

    constructor(
        private albanjarProductService: AlbanjarProductAlbanjarService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.albanjarProductService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'albanjarProductListModification',
                content: 'Deleted an albanjarProduct'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-albanjar-product-albanjar-delete-popup',
    template: ''
})
export class AlbanjarProductAlbanjarDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private albanjarProductPopupService: AlbanjarProductAlbanjarPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.albanjarProductPopupService
                .open(AlbanjarProductAlbanjarDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
