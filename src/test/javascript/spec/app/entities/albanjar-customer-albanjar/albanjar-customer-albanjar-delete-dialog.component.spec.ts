/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AlbanjarHipsterTestModule } from '../../../test.module';
import { AlbanjarCustomerAlbanjarDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/albanjar-customer-albanjar/albanjar-customer-albanjar-delete-dialog.component';
import { AlbanjarCustomerAlbanjarService } from '../../../../../../main/webapp/app/entities/albanjar-customer-albanjar/albanjar-customer-albanjar.service';

describe('Component Tests', () => {

    describe('AlbanjarCustomerAlbanjar Management Delete Component', () => {
        let comp: AlbanjarCustomerAlbanjarDeleteDialogComponent;
        let fixture: ComponentFixture<AlbanjarCustomerAlbanjarDeleteDialogComponent>;
        let service: AlbanjarCustomerAlbanjarService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AlbanjarHipsterTestModule],
                declarations: [AlbanjarCustomerAlbanjarDeleteDialogComponent],
                providers: [
                    AlbanjarCustomerAlbanjarService
                ]
            })
            .overrideTemplate(AlbanjarCustomerAlbanjarDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AlbanjarCustomerAlbanjarDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AlbanjarCustomerAlbanjarService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
