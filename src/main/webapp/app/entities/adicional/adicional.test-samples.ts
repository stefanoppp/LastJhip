import { IAdicional, NewAdicional } from './adicional.model';

export const sampleWithRequiredData: IAdicional = {
  id: 9737,
  nombre: 'especially imaginary give',
  descripcion: 'anenst hence',
  precio: 10517.22,
  precioGratis: 8892.32,
};

export const sampleWithPartialData: IAdicional = {
  id: 31624,
  nombre: 'fictionalize ugh stump',
  descripcion: 'boohoo scarcely pish',
  precio: 6960.16,
  precioGratis: 11434.5,
};

export const sampleWithFullData: IAdicional = {
  id: 17822,
  nombre: 'between ack',
  descripcion: 'drat excepting left',
  precio: 21425.69,
  precioGratis: 17844.86,
};

export const sampleWithNewData: NewAdicional = {
  nombre: 'hold pace yuck',
  descripcion: 'yogurt where provided',
  precio: 10393.24,
  precioGratis: 29987.6,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
