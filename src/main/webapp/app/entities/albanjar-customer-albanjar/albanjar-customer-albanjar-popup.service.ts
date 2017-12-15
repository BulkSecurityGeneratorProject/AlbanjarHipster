import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AlbanjarCustomerAlbanjar } from './albanjar-customer-albanjar.model';
import { AlbanjarCustomerAlbanjarService } from './albanjar-customer-albanjar.service';

@Injectable()
export class AlbanjarCustomerAlbanjarPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private albanjarCustomerService: AlbanjarCustomerAlbanjarService

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
                this.albanjarCustomerService.find(id).subscribe((albanjarCustomer) => {
                    this.ngbModalRef = this.albanjarCustomerModalRef(component, albanjarCustomer);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.albanjarCustomerModalRef(component, new AlbanjarCustomerAlbanjar());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    albanjarCustomerModalRef(component: Component, albanjarCustomer: AlbanjarCustomerAlbanjar): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.albanjarCustomer = albanjarCustomer;
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
