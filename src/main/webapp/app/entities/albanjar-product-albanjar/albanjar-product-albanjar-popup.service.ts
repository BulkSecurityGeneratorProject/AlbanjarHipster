import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AlbanjarProductAlbanjar } from './albanjar-product-albanjar.model';
import { AlbanjarProductAlbanjarService } from './albanjar-product-albanjar.service';

@Injectable()
export class AlbanjarProductAlbanjarPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private albanjarProductService: AlbanjarProductAlbanjarService

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
                this.albanjarProductService.find(id).subscribe((albanjarProduct) => {
                    this.ngbModalRef = this.albanjarProductModalRef(component, albanjarProduct);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.albanjarProductModalRef(component, new AlbanjarProductAlbanjar());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    albanjarProductModalRef(component: Component, albanjarProduct: AlbanjarProductAlbanjar): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.albanjarProduct = albanjarProduct;
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
