/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';

import { AlbanjarHipsterTestModule } from '../../../test.module';
import { AlbanjarDeliveryAlbanjarDetailComponent } from '../../../../../../main/webapp/app/entities/albanjar-delivery-albanjar/albanjar-delivery-albanjar-detail.component';
import { AlbanjarDeliveryAlbanjarService } from '../../../../../../main/webapp/app/entities/albanjar-delivery-albanjar/albanjar-delivery-albanjar.service';
import { AlbanjarDeliveryAlbanjar } from '../../../../../../main/webapp/app/entities/albanjar-delivery-albanjar/albanjar-delivery-albanjar.model';

describe('Component Tests', () => {

    describe('AlbanjarDeliveryAlbanjar Management Detail Component', () => {
        let comp: AlbanjarDeliveryAlbanjarDetailComponent;
        let fixture: ComponentFixture<AlbanjarDeliveryAlbanjarDetailComponent>;
        let service: AlbanjarDeliveryAlbanjarService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AlbanjarHipsterTestModule],
                declarations: [AlbanjarDeliveryAlbanjarDetailComponent],
                providers: [
                    AlbanjarDeliveryAlbanjarService
                ]
            })
            .overrideTemplate(AlbanjarDeliveryAlbanjarDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AlbanjarDeliveryAlbanjarDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AlbanjarDeliveryAlbanjarService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new AlbanjarDeliveryAlbanjar(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.albanjarDelivery).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
