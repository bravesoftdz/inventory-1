import { Pipe, PipeTransform } from '@angular/core';
import { SystemUnit } from '../models/systemUnit';
import { Model } from '../models/model';

@Pipe({
  name: 'systemUnit'
})
export class SystemUnitPipe implements PipeTransform {

  transform(values: SystemUnit[], term?: string): any {

    if ( term === undefined) { return values; }

    return values.filter(

      systemUnit => {

        let search = systemUnit.assetNumber + ' '
                    +systemUnit.macAddress + ' '
                    +systemUnit.serialNumber+ ' '
                    +systemUnit.name+ ' '
                    +systemUnit.model.name;

        return search.toLowerCase().includes(term.toLowerCase());
      }

    );
  }

}
