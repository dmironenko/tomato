import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {TomatoSale} from '../model/tomato-sale.model';
import {environment} from '../../environments/environment';

@Injectable()
export class TomatoSalesService {

  private serviceUrl = environment.apiUrl + 'sales/data';

  constructor(private http: HttpClient) {
  }

  public getTomatoes(size = 3): Observable<TomatoSale[]> {
    return this.http.get<TomatoSale[]>(this.serviceUrl, {
      params: new HttpParams()
        .set('size', size.toString())
    });
  }
}
