import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AlbanjarProductAlbanjar } from './albanjar-product-albanjar.model';
import { AlbanjarProductAlbanjarService } from './albanjar-product-albanjar.service';

@Component({
    selector: 'jhi-albanjar-product-albanjar-detail',
    templateUrl: './albanjar-product-albanjar-detail.component.html'
})
export class AlbanjarProductAlbanjarDetailComponent implements OnInit, OnDestroy {

    albanjarProduct: AlbanjarProductAlbanjar;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private albanjarProductService: AlbanjarProductAlbanjarService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAlbanjarProducts();
    }

    load(id) {
        this.albanjarProductService.find(id).subscribe((albanjarProduct) => {
            this.albanjarProduct = albanjarProduct;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAlbanjarProducts() {
        this.eventSubscriber = this.eventManager.subscribe(
            'albanjarProductListModification',
            (response) => this.load(this.albanjarProduct.id)
        );
    }
}
