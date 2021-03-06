import { Component, Input } from '@angular/core';
import { SimpleInputFormBase } from '../../../../../../forms/modules/generic-form/base/simple-input-form-base';
import { ParameterAssignment } from '../../../../../../../model/ParameterAssignment';
import { SpecmateDataService } from '../../../../../../data/modules/data-service/services/specmate-data.service';

@Component({
    moduleId: module.id.toString(),
    selector: 'test-case-condition-form',
    templateUrl: 'test-case-condition-form.component.html',
    styleUrls: ['test-case-condition-form.component.css']
})
export class TestCaseConditionForm extends SimpleInputFormBase {

    @Input()
    set paramAssignment(paramAssignment: ParameterAssignment) {
        this.modelElement = paramAssignment;
    }

    get fields(): string[] {
        return ['condition'];
    }

    constructor(protected dataService: SpecmateDataService) {
        super();
    }
}
