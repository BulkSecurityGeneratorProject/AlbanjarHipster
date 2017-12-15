import { BaseEntity } from './../../shared';

export class AlbanjarProductAlbanjar implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public pu?: number,
        public deliveries?: BaseEntity[],
    ) {
    }
}
