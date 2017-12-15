/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';
import { Headers } from '@angular/http';

import { AlbanjarHipsterTestModule } from '../../../test.module';
import { AlbanjarDeliveryAlbanjarComponent } from '../../../../../../main/webapp/app/entities/albanjar-delivery-albanjar/albanjar-delivery-albanjar.component';
import { AlbanjarDeliveryAlbanjarService } from '../../../../../../main/webapp/app/entities/albanjar-delivery-albanjar/albanjar-delivery-albanjar.service';
import { AlbanjarDeliveryAlbanjar } from '../../../../../../main/webapp/app/entities/albanjar-delivery-albanjar/albanjar-delivery-albanjar.model';

describe('Component Tests', () => {

    describe('AlbanjarDeliveryAlbanjar Management Component', () => {
        let comp: AlbanjarDeliveryAlbanjarComponent;
        let fixture: ComponentFixture<AlbanjarDeliveryAlbanjarComponent>;
        let service: AlbanjarDeliveryAlbanjarService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AlbanjarHipsterTestModule],
                declarations: [AlbanjarDeliveryAlbanjarComponent],
                providers: [
                    AlbanjarDeliveryAlbanjarService
                ]
            })
            .overrideTemplate(AlbanjarDeliveryAlbanjarComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AlbanjarDeliveryAlbanjarComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AlbanjarDeliveryAlbanjarService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new AlbanjarDeliveryAlbanjar(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.albanjarDeliveries[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
