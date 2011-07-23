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
			},
			'loggingeventlist button[action=deleteall]': {
				click: this.deleteAll
			},
			'loggingeventlist button[action=test]': {
				click: this.addTestData
			},
			'loggingeventlist combobox[name=logLevelFilter]': {
				change: this.filterLogLevel
			}			
		});
	},
	
	filterLogLevel: function(field, newValue, oldValue) {
		var myStore = this.getLoggingEventsStore();
		if (newValue) {
			myStore.remoteFilter = false;
			myStore.clearFilter(true);
			myStore.remoteFilter = true;
			myStore.filter('level', newValue);
		} else {
			myStore.clearFilter();
		}
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
			this.getLoggingEventsStore().clearFilter();
		}
	},

	doGridRefresh: function() {
		this.getLoggingeventList().down('pagingtoolbar').doRefresh();
	}

});