/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';
import { Headers } from '@angular/http';

import { AlbanjarHipsterTestModule } from '../../../test.module';
import { AlbanjarProductAlbanjarComponent } from '../../../../../../main/webapp/app/entities/albanjar-product-albanjar/albanjar-product-albanjar.component';
import { AlbanjarProductAlbanjarService } from '../../../../../../main/webapp/app/entities/albanjar-product-albanjar/albanjar-product-albanjar.service';
import { AlbanjarProductAlbanjar } from '../../../../../../main/webapp/app/entities/albanjar-product-albanjar/albanjar-product-albanjar.model';

describe('Component Tests', () => {

    describe('AlbanjarProductAlbanjar Management Component', () => {
        let comp: AlbanjarProductAlbanjarComponent;
        let fixture: ComponentFixture<AlbanjarProductAlbanjarComponent>;
        let service: AlbanjarProductAlbanjarService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AlbanjarHipsterTestModule],
                declarations: [AlbanjarProductAlbanjarComponent],
                providers: [
                    AlbanjarProductAlbanjarService
                ]
            })
            .overrideTemplate(AlbanjarProductAlbanjarComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AlbanjarProductAlbanjarComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AlbanjarProductAlbanjarService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new AlbanjarProductAlbanjar(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.albanjarProducts[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
