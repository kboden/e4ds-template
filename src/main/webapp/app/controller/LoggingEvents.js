Ext.define('E4ds.controller.LoggingEvents', {
	extend: 'Ext.app.Controller',

	views: [ 'loggingevent.List' ],
	stores: [ 'LoggingEvents' ],
	models: [ 'LoggingEvent' ],

	refs: [ {
		ref: 'loggingeventList',
		selector: 'loggingeventlist'
	} ],

	init: function() {
		this.control({
			'loggingeventlist': {
				beforeactivate: this.onBeforeActivate,
				activate: Ext.bind(loggingEventService.getCurrentLevel, this, [this.showCurrentLevel, this])
			},
			'loggingeventlist button[action=deleteall]': {
				click: this.deleteAll
			},
			'loggingeventlist button[action=test]': {
				click: this.addTestData
			},
			'loggingeventlist combobox[name=logLevel]': {
				change: this.logLevelChange
			},
			'loggingeventlist combobox[name=logLevelFilter]': {
				change: this.filterLogLevel
			}			
		});
	},
	
	filterLogLevel: function(field, newValue, oldValue) {
		var myStore = this.getLoggingEventsStore();
//		myStore.remoteFilter = false;
//		myStore.clearFilter(true);
//		myStore.remoteFilter = true;
		myStore.filter('level', newValue);
	},
	
	showCurrentLevel: function(logLevel) {
		this.getLoggingeventList().down('combobox[name=logLevel]').setValue(logLevel);
	},
	
	logLevelChange: function(field, newValue, oldValue) {
		loggingEventService.changeLogLevel(newValue);
	},
	
	deleteAll: function() {
		loggingEventService.deleteAll();
		this.doGridRefresh();
	},
	
	addTestData: function() {
		loggingEventService.addTestData();
		this.doGridRefresh();
	},
	
	onBeforeActivate: function(cmp, options) {
		if (options) {
			this.doGridRefresh();
		}
	},

	doGridRefresh: function() {
		this.getLoggingeventList().down('pagingtoolbar').doRefresh();
	}

});