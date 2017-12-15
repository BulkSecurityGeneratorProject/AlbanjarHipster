import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { AlbanjarDeliveryAlbanjar } from './albanjar-delivery-albanjar.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AlbanjarDeliveryAlbanjarService {

    private resourceUrl = SERVER_API_URL + 'api/albanjar-deliveries';

    constructor(private http: Http) { }

    create(albanjarDelivery: AlbanjarDeliveryAlbanjar): Observable<AlbanjarDeliveryAlbanjar> {
        const copy = this.convert(albanjarDelivery);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(albanjarDelivery: AlbanjarDeliveryAlbanjar): Observable<AlbanjarDeliveryAlbanjar> {
        const copy = this.convert(albanjarDelivery);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<AlbanjarDeliveryAlbanjar> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to AlbanjarDeliveryAlbanjar.
     */
    private convertItemFromServer(json: any): AlbanjarDeliveryAlbanjar {
        const entity: AlbanjarDeliveryAlbanjar = Object.assign(new AlbanjarDeliveryAlbanjar(), json);
        return entity;
    }

    /**
     * Convert a AlbanjarDeliveryAlbanjar to a JSON which can be sent to the server.
     */
    private convert(albanjarDelivery: AlbanjarDeliveryAlbanjar): AlbanjarDeliveryAlbanjar {
        const copy: AlbanjarDeliveryAlbanjar = Object.assign({}, albanjarDelivery);
        return copy;
    }
}
