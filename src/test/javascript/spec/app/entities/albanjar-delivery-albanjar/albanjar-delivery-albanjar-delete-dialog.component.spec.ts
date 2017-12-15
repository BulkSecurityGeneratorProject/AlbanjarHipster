/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AlbanjarHipsterTestModule } from '../../../test.module';
import { AlbanjarDeliveryAlbanjarDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/albanjar-delivery-albanjar/albanjar-delivery-albanjar-delete-dialog.component';
import { AlbanjarDeliveryAlbanjarService } from '../../../../../../main/webapp/app/entities/albanjar-delivery-albanjar/albanjar-delivery-albanjar.service';

describe('Component Tests', () => {

    describe('AlbanjarDeliveryAlbanjar Management Delete Component', () => {
        let comp: AlbanjarDeliveryAlbanjarDeleteDialogComponent;
        let fixture: ComponentFixture<AlbanjarDeliveryAlbanjarDeleteDialogComponent>;
        let service: AlbanjarDeliveryAlbanjarService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AlbanjarHipsterTestModule],
                declarations: [AlbanjarDeliveryAlbanjarDeleteDialogComponent],
                providers: [
                    AlbanjarDeliveryAlbanjarService
                ]
            })
            .overrideTemplate(AlbanjarDeliveryAlbanjarDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AlbanjarDeliveryAlbanjarDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AlbanjarDeliveryAlbanjarService);
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
