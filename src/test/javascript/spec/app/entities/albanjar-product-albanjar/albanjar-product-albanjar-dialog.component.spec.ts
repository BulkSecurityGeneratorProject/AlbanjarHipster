/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AlbanjarHipsterTestModule } from '../../../test.module';
import { AlbanjarProductAlbanjarDialogComponent } from '../../../../../../main/webapp/app/entities/albanjar-product-albanjar/albanjar-product-albanjar-dialog.component';
import { AlbanjarProductAlbanjarService } from '../../../../../../main/webapp/app/entities/albanjar-product-albanjar/albanjar-product-albanjar.service';
import { AlbanjarProductAlbanjar } from '../../../../../../main/webapp/app/entities/albanjar-product-albanjar/albanjar-product-albanjar.model';

describe('Component Tests', () => {

    describe('AlbanjarProductAlbanjar Management Dialog Component', () => {
        let comp: AlbanjarProductAlbanjarDialogComponent;
        let fixture: ComponentFixture<AlbanjarProductAlbanjarDialogComponent>;
        let service: AlbanjarProductAlbanjarService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AlbanjarHipsterTestModule],
                declarations: [AlbanjarProductAlbanjarDialogComponent],
                providers: [
                    AlbanjarProductAlbanjarService
                ]
            })
            .overrideTemplate(AlbanjarProductAlbanjarDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AlbanjarProductAlbanjarDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AlbanjarProductAlbanjarService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new AlbanjarProductAlbanjar(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.albanjarProduct = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'albanjarProductListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new AlbanjarProductAlbanjar();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.albanjarProduct = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'albanjarProductListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
