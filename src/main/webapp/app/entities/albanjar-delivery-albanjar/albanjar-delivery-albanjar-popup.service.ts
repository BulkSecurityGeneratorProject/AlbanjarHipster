import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AlbanjarDeliveryAlbanjar } from './albanjar-delivery-albanjar.model';
import { AlbanjarDeliveryAlbanjarService } from './albanjar-delivery-albanjar.service';

@Injectable()
export class AlbanjarDeliveryAlbanjarPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private albanjarDeliveryService: AlbanjarDeliveryAlbanjarService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.albanjarDeliveryService.find(id).subscribe((albanjarDelivery) => {
                    this.ngbModalRef = this.albanjarDeliveryModalRef(component, albanjarDelivery);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.albanjarDeliveryModalRef(component, new AlbanjarDeliveryAlbanjar());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    albanjarDeliveryModalRef(component: Component, albanjarDelivery: AlbanjarDeliveryAlbanjar): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.albanjarDelivery = albanjarDelivery;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
