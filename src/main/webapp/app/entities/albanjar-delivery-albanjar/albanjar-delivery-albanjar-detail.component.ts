import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AlbanjarDeliveryAlbanjar } from './albanjar-delivery-albanjar.model';
import { AlbanjarDeliveryAlbanjarService } from './albanjar-delivery-albanjar.service';

@Component({
    selector: 'jhi-albanjar-delivery-albanjar-detail',
    templateUrl: './albanjar-delivery-albanjar-detail.component.html'
})
export class AlbanjarDeliveryAlbanjarDetailComponent implements OnInit, OnDestroy {

    albanjarDelivery: AlbanjarDeliveryAlbanjar;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private albanjarDeliveryService: AlbanjarDeliveryAlbanjarService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAlbanjarDeliveries();
    }

    load(id) {
        this.albanjarDeliveryService.find(id).subscribe((albanjarDelivery) => {
            this.albanjarDelivery = albanjarDelivery;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAlbanjarDeliveries() {
        this.eventSubscriber = this.eventManager.subscribe(
            'albanjarDeliveryListModification',
            (response) => this.load(this.albanjarDelivery.id)
        );
    }
}
