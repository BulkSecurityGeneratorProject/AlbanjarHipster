/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AlbanjarHipsterTestModule } from '../../../test.module';
import { AlbanjarCustomerAlbanjarDialogComponent } from '../../../../../../main/webapp/app/entities/albanjar-customer-albanjar/albanjar-customer-albanjar-dialog.component';
import { AlbanjarCustomerAlbanjarService } from '../../../../../../main/webapp/app/entities/albanjar-customer-albanjar/albanjar-customer-albanjar.service';
import { AlbanjarCustomerAlbanjar } from '../../../../../../main/webapp/app/entities/albanjar-customer-albanjar/albanjar-customer-albanjar.model';

describe('Component Tests', () => {

    describe('AlbanjarCustomerAlbanjar Management Dialog Component', () => {
        let comp: AlbanjarCustomerAlbanjarDialogComponent;
        let fixture: ComponentFixture<AlbanjarCustomerAlbanjarDialogComponent>;
        let service: AlbanjarCustomerAlbanjarService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AlbanjarHipsterTestModule],
                declarations: [AlbanjarCustomerAlbanjarDialogComponent],
                providers: [
                    AlbanjarCustomerAlbanjarService
                ]
            })
            .overrideTemplate(AlbanjarCustomerAlbanjarDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AlbanjarCustomerAlbanjarDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AlbanjarCustomerAlbanjarService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new AlbanjarCustomerAlbanjar(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.albanjarCustomer = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'albanjarCustomerListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new AlbanjarCustomerAlbanjar();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.albanjarCustomer = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'albanjarCustomerListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
