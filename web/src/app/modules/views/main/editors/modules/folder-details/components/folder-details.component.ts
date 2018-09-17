import { Component, OnInit } from '@angular/core';
import { SpecmateViewBase } from '../../../base/specmate-view-base';
import { SpecmateDataService } from '../../../../../../data/modules/data-service/services/specmate-data.service';
import { NavigatorService } from '../../../../../../navigation/modules/navigator/services/navigator.service';
import { ActivatedRoute } from '@angular/router';
import { ConfirmationModal } from '../../../../../../notification/modules/modals/services/confirmation-modal.service';
import { TranslateService } from '@ngx-translate/core';
import { IContainer } from '../../../../../../../model/IContainer';
import { Folder } from '../../../../../../../model/Folder';
import { CEGModel } from '../../../../../../../model/CEGModel';
import { Process } from '../../../../../../../model/Process';
import { Type } from '../../../../../../../util/type';
import { Requirement } from '../../../../../../../model/Requirement';

@Component({
    moduleId: module.id.toString(),
    selector: 'folder-details',
    templateUrl: 'folder-details.component.html',
    styleUrls: ['folder-details.component.css']
})

export class FolderDetails extends SpecmateViewBase {
    cegModelType = CEGModel;
    processModelType = Process;
    folderType = Folder;
    reqType = Requirement;

    /** Constructor */
    constructor(
        dataService: SpecmateDataService,
        navigator: NavigatorService,
        route: ActivatedRoute,
        modal: ConfirmationModal,
        translate: TranslateService) {
        super(dataService, navigator, route, modal, translate);
    }

    private folder: Folder;
    private contents: IContainer[];
    private transitiveContents: IContainer[];

    private async updateTransitiveContent() {
        this.transitiveContents = [];
        if (this.contents) {
            for (const element of this.contents) {
                if (Type.is(element, this.folderType) || Type.is(element, this.reqType)) {
                    let subFolderContent = await this.dataService.readContents(element.url);
                    this.transitiveContents.push(...subFolderContent);
                } else {
                    this.transitiveContents.push(element);
                }
            }
        }
    }

    protected onElementResolved(element: IContainer): void {
        // The timeout create a macrotask to prevent uncheckt update errors in Angular.
        setTimeout(() => {
            this.folder = element as Folder;
            this.dataService.readContents(this.folder.url)
                .then((contents: IContainer[]) => this.contents = contents)
                .then(() => this.updateTransitiveContent());
        });
    }

    public get cegModels(): CEGModel[] {
        if (!this.transitiveContents) {
            return [];
        }
        return <CEGModel[]>this.transitiveContents.filter((element: IContainer) => Type.is(element, this.cegModelType));
    }

    public get processModels(): Process[] {
        if (!this.transitiveContents) {
            return [];
        }
        return this.transitiveContents.filter((element: IContainer) => Type.is(element, this.processModelType));
    }

    protected get isValid(): boolean {
        return true;
    }

    public get hasData(): boolean {
        return (this.folder && this.transitiveContents !== undefined);
    }
}
