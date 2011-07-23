Ext.define('E4ds.controller.Config', {
	extend: 'Ext.app.Controller',

	views: [ 'config.Edit' ],

	refs: [{
		ref: 'configedit',
		selector: 'configedit'
	}],

	init: function() {

		this.control({
			'configedit': {
				activate: Ext.bind(loggingEventService.getCurrentLevel, this, [this.showCurrentLevel, this])
			},
			'configedit combobox[name=logLevel]': {
				change: this.logLevelChange
			}
		});

	},
	
	showCurrentLevel: function(logLevel) {
		this.getConfigedit().down('combobox[name=logLevel]').setValue(logLevel);
	},
	
	logLevelChange: function(field, newValue, oldValue) {
		loggingEventService.changeLogLevel(newValue);
	},

});
