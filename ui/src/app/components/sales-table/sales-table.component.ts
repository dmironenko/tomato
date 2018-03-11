import {Component} from '@angular/core';
import {TomatoSalesService} from '../../services/tomato-sales.service';
import {CollectionViewer, DataSource} from '@angular/cdk/collections';
import {Observable} from 'rxjs/Observable';
import {TomatoSale} from '../../model/tomato-sale.model';
import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-salesstable',
  templateUrl: './sales-table.component.html',
  styleUrls: ['./sales-table.component.css']
})
export class SalesTableComponent {

  displayedColumns = ['id', 'tomatoes', 'provider', 'timestamp'];

  datasource = new UserDataSource(this.salesService);

  public sizeFormControl = new FormControl('', [Validators.required, Validators.pattern('[1-9][0-9]*')]);

  constructor(private salesService: TomatoSalesService) {
  }

  public refresh() {
    this.datasource = new UserDataSource(this.salesService, this.sizeFormControl.value);
  }
}

export class UserDataSource implements DataSource<TomatoSale> {

  constructor(private salesService: TomatoSalesService, private size = 3) {
  }

  connect(collectionViewer: CollectionViewer): Observable<TomatoSale[]> {
    return this.salesService.getTomatoes(this.size);
  }

  disconnect(collectionViewer: CollectionViewer): void {
  }
}
