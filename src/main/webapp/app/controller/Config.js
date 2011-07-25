Ext.define('E4ds.controller.Config', {
	extend: 'Ext.app.Controller',

	views: [ 'config.Edit' ],
	stores: [ 'LogLevels' ],

	refs: [ {
		ref: 'configedit',
		selector: 'configedit'
	} ],

	init: function() {

		this.control({
			'configedit': {
				activate: Ext.bind(loggingEventService.getCurrentLevel, this, [ this.showCurrentLevel, this ])
			}
		});

	},

	showCurrentLevel: function(logLevel) {
		var cb = this.getConfigedit().down('combobox[name=logLevel]');
		cb.setValue(logLevel);
		cb.on('change', this.logLevelChange);
	},

	logLevelChange: function(field, newValue, oldValue) {
		loggingEventService.changeLogLevel(newValue);
		Ext.create('Ext.ux.Notification', {
			title: 'My title',
			html: 'a simple message',
			autoDestroy: true
		}).show(document);
	},

});
