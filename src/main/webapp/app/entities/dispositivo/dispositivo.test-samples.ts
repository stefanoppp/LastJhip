import { IDispositivo, NewDispositivo } from './dispositivo.model';

export const sampleWithRequiredData: IDispositivo = {
  id: 20205,
  codigo: 'near',
  nombre: 'artistic unabashedly ick',
  descripcion: 'muddy insert',
  precioBase: 18622.49,
  moneda: 'EUR',
};

export const sampleWithPartialData: IDispositivo = {
  id: 29922,
  codigo: 'aha',
  nombre: 'during hovel',
  descripcion: 'option distinct when',
  precioBase: 25430.57,
  moneda: 'ARS',
};

export const sampleWithFullData: IDispositivo = {
  id: 10267,
  codigo: 'last',
  nombre: 'however duh',
  descripcion: 'egg',
  precioBase: 11015.5,
  moneda: 'USD',
};

export const sampleWithNewData: NewDispositivo = {
  codigo: 'yahoo meh',
  nombre: 'delirious birth',
  descripcion: 'huzzah distinct',
  precioBase: 31022.61,
  moneda: 'ARS',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
