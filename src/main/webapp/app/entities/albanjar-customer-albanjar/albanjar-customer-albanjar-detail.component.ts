import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AlbanjarCustomerAlbanjar } from './albanjar-customer-albanjar.model';
import { AlbanjarCustomerAlbanjarService } from './albanjar-customer-albanjar.service';

@Component({
    selector: 'jhi-albanjar-customer-albanjar-detail',
    templateUrl: './albanjar-customer-albanjar-detail.component.html'
})
export class AlbanjarCustomerAlbanjarDetailComponent implements OnInit, OnDestroy {

    albanjarCustomer: AlbanjarCustomerAlbanjar;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private albanjarCustomerService: AlbanjarCustomerAlbanjarService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAlbanjarCustomers();
    }

    load(id) {
        this.albanjarCustomerService.find(id).subscribe((albanjarCustomer) => {
            this.albanjarCustomer = albanjarCustomer;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAlbanjarCustomers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'albanjarCustomerListModification',
            (response) => this.load(this.albanjarCustomer.id)
        );
    }
}
