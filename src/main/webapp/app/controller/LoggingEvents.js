Ext.define('E4ds.controller.LoggingEvents', {
	extend: 'Ext.app.Controller',

	views: [ 'loggingevent.List' ],
	stores: [ 'LoggingEvents', 'LogLevels' ],
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
			this.getLoggingeventList().down('button[action=export]').setParams({
				level: newValue
			});
		} else {
			myStore.clearFilter();
			this.getLoggingeventList().down('button[action=export]').setParams();
		}
	},

	deleteAll: function() {
		var filter = this.getLoggingEventsStore().filters.get(0);
		loggingEventService.deleteAll(filter && filter.value, function() {
			Ext.ux.Notification.info(i18n.successful, i18n.logevents_deleted);
			this.doGridRefresh();
		}, this);		
	},

	addTestData: function() {
		loggingEventService.addTestData(function() {
			Ext.ux.Notification.info(i18n.successful, i18n.logevents_testinserted);
			this.doGridRefresh();	
		}, this);
		
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