import { ICaracteristica, NewCaracteristica } from './caracteristica.model';

export const sampleWithRequiredData: ICaracteristica = {
  id: 12295,
  nombre: 'boo',
  descripcion: 'afore',
};

export const sampleWithPartialData: ICaracteristica = {
  id: 6834,
  nombre: 'until',
  descripcion: 'hm hence why',
};

export const sampleWithFullData: ICaracteristica = {
  id: 31159,
  nombre: 'questionably clonk inconsequential',
  descripcion: 'doubtfully investigate alive',
};

export const sampleWithNewData: NewCaracteristica = {
  nombre: 'portray',
  descripcion: 'or',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
