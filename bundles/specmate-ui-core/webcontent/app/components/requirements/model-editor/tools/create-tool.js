"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Id_1 = require("../../../../util/Id");
var CreateTool = (function () {
    function CreateTool(parent, dataService) {
        this.parent = parent;
        this.dataService = dataService;
        this.selectedElements = [];
    }
    CreateTool.prototype.activate = function () {
        this.done = false;
        this.selectedElements = [];
    };
    CreateTool.prototype.deactivate = function () {
        this.selectedElements = [];
    };
    CreateTool.prototype.getNewId = function (idBase) {
        return Id_1.Id.uuid;
    };
    CreateTool.prototype.createAndSelect = function (element) {
        var _this = this;
        return this.dataService.createElement(element, true, Id_1.Id.uuid).then(function () {
            _this.selectedElements = [element];
            _this.done = true;
        });
    };
    return CreateTool;
}());
exports.CreateTool = CreateTool;
//# sourceMappingURL=create-tool.js.map