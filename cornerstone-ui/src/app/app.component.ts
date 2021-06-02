import { Component } from '@angular/core';
import { DataService } from './service/data.service';


@Component({
  selector: 'app-root',
  template: '<router-outlet></router-outlet>',
})
export class AppComponent {
  title = 'cornerstone-ui';


  constructor(public dataService: DataService) {
  }

}
