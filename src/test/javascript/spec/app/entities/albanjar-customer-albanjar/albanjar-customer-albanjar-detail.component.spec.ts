/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';

import { AlbanjarHipsterTestModule } from '../../../test.module';
import { AlbanjarCustomerAlbanjarDetailComponent } from '../../../../../../main/webapp/app/entities/albanjar-customer-albanjar/albanjar-customer-albanjar-detail.component';
import { AlbanjarCustomerAlbanjarService } from '../../../../../../main/webapp/app/entities/albanjar-customer-albanjar/albanjar-customer-albanjar.service';
import { AlbanjarCustomerAlbanjar } from '../../../../../../main/webapp/app/entities/albanjar-customer-albanjar/albanjar-customer-albanjar.model';

describe('Component Tests', () => {

    describe('AlbanjarCustomerAlbanjar Management Detail Component', () => {
        let comp: AlbanjarCustomerAlbanjarDetailComponent;
        let fixture: ComponentFixture<AlbanjarCustomerAlbanjarDetailComponent>;
        let service: AlbanjarCustomerAlbanjarService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AlbanjarHipsterTestModule],
                declarations: [AlbanjarCustomerAlbanjarDetailComponent],
                providers: [
                    AlbanjarCustomerAlbanjarService
                ]
            })
            .overrideTemplate(AlbanjarCustomerAlbanjarDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AlbanjarCustomerAlbanjarDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AlbanjarCustomerAlbanjarService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new AlbanjarCustomerAlbanjar(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.albanjarCustomer).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
