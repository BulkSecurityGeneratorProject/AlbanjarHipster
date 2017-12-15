/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';
import { Headers } from '@angular/http';

import { AlbanjarHipsterTestModule } from '../../../test.module';
import { AlbanjarCustomerAlbanjarComponent } from '../../../../../../main/webapp/app/entities/albanjar-customer-albanjar/albanjar-customer-albanjar.component';
import { AlbanjarCustomerAlbanjarService } from '../../../../../../main/webapp/app/entities/albanjar-customer-albanjar/albanjar-customer-albanjar.service';
import { AlbanjarCustomerAlbanjar } from '../../../../../../main/webapp/app/entities/albanjar-customer-albanjar/albanjar-customer-albanjar.model';

describe('Component Tests', () => {

    describe('AlbanjarCustomerAlbanjar Management Component', () => {
        let comp: AlbanjarCustomerAlbanjarComponent;
        let fixture: ComponentFixture<AlbanjarCustomerAlbanjarComponent>;
        let service: AlbanjarCustomerAlbanjarService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AlbanjarHipsterTestModule],
                declarations: [AlbanjarCustomerAlbanjarComponent],
                providers: [
                    AlbanjarCustomerAlbanjarService
                ]
            })
            .overrideTemplate(AlbanjarCustomerAlbanjarComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AlbanjarCustomerAlbanjarComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AlbanjarCustomerAlbanjarService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new AlbanjarCustomerAlbanjar(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.albanjarCustomers[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
