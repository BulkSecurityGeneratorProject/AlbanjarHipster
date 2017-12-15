import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AlbanjarDeliveryAlbanjar } from './albanjar-delivery-albanjar.model';
import { AlbanjarDeliveryAlbanjarPopupService } from './albanjar-delivery-albanjar-popup.service';
import { AlbanjarDeliveryAlbanjarService } from './albanjar-delivery-albanjar.service';

@Component({
    selector: 'jhi-albanjar-delivery-albanjar-delete-dialog',
    templateUrl: './albanjar-delivery-albanjar-delete-dialog.component.html'
})
export class AlbanjarDeliveryAlbanjarDeleteDialogComponent {

    albanjarDelivery: AlbanjarDeliveryAlbanjar;

    constructor(
        private albanjarDeliveryService: AlbanjarDeliveryAlbanjarService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.albanjarDeliveryService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'albanjarDeliveryListModification',
                content: 'Deleted an albanjarDelivery'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-albanjar-delivery-albanjar-delete-popup',
    template: ''
})
export class AlbanjarDeliveryAlbanjarDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private albanjarDeliveryPopupService: AlbanjarDeliveryAlbanjarPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.albanjarDeliveryPopupService
                .open(AlbanjarDeliveryAlbanjarDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
