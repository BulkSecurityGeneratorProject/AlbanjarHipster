/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AlbanjarHipsterTestModule } from '../../../test.module';
import { AlbanjarDeliveryAlbanjarDialogComponent } from '../../../../../../main/webapp/app/entities/albanjar-delivery-albanjar/albanjar-delivery-albanjar-dialog.component';
import { AlbanjarDeliveryAlbanjarService } from '../../../../../../main/webapp/app/entities/albanjar-delivery-albanjar/albanjar-delivery-albanjar.service';
import { AlbanjarDeliveryAlbanjar } from '../../../../../../main/webapp/app/entities/albanjar-delivery-albanjar/albanjar-delivery-albanjar.model';
import { AlbanjarCustomerAlbanjarService } from '../../../../../../main/webapp/app/entities/albanjar-customer-albanjar';
import { AlbanjarProductAlbanjarService } from '../../../../../../main/webapp/app/entities/albanjar-product-albanjar';

describe('Component Tests', () => {

    describe('AlbanjarDeliveryAlbanjar Management Dialog Component', () => {
        let comp: AlbanjarDeliveryAlbanjarDialogComponent;
        let fixture: ComponentFixture<AlbanjarDeliveryAlbanjarDialogComponent>;
        let service: AlbanjarDeliveryAlbanjarService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AlbanjarHipsterTestModule],
                declarations: [AlbanjarDeliveryAlbanjarDialogComponent],
                providers: [
                    AlbanjarCustomerAlbanjarService,
                    AlbanjarProductAlbanjarService,
                    AlbanjarDeliveryAlbanjarService
                ]
            })
            .overrideTemplate(AlbanjarDeliveryAlbanjarDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AlbanjarDeliveryAlbanjarDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AlbanjarDeliveryAlbanjarService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new AlbanjarDeliveryAlbanjar(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.albanjarDelivery = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'albanjarDeliveryListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new AlbanjarDeliveryAlbanjar();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.albanjarDelivery = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'albanjarDeliveryListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
