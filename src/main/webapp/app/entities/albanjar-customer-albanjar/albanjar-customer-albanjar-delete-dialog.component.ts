import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AlbanjarCustomerAlbanjar } from './albanjar-customer-albanjar.model';
import { AlbanjarCustomerAlbanjarPopupService } from './albanjar-customer-albanjar-popup.service';
import { AlbanjarCustomerAlbanjarService } from './albanjar-customer-albanjar.service';

@Component({
    selector: 'jhi-albanjar-customer-albanjar-delete-dialog',
    templateUrl: './albanjar-customer-albanjar-delete-dialog.component.html'
})
export class AlbanjarCustomerAlbanjarDeleteDialogComponent {

    albanjarCustomer: AlbanjarCustomerAlbanjar;

    constructor(
        private albanjarCustomerService: AlbanjarCustomerAlbanjarService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.albanjarCustomerService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'albanjarCustomerListModification',
                content: 'Deleted an albanjarCustomer'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-albanjar-customer-albanjar-delete-popup',
    template: ''
})
export class AlbanjarCustomerAlbanjarDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private albanjarCustomerPopupService: AlbanjarCustomerAlbanjarPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.albanjarCustomerPopupService
                .open(AlbanjarCustomerAlbanjarDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
