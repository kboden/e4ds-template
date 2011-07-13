//Start: This is needed if you use ext-all-debug.js
Ext.Loader.setConfig({
	enabled : true
});
Ext.Loader.setPath('Ext.ux', 'http://www.ralscha.ch/ext-4.0.2a/examples/ux');
//End

Ext.require('Ext.direct.*', function() {
	var poller = new Ext.direct.PollingProvider({
		id : 'chartdatapoller',
		type : 'polling',
		interval : 2000,
		url : Ext.app.POLLING_URLS.chartdata,
	});
	Ext.direct.Manager.addProvider(Ext.app.REMOTING_API, poller);
	Ext.direct.Manager.getProvider('chartdatapoller').disconnect();
});

Ext.application({
	name : 'E4ds',
	appFolder : 'app',
	controllers : [ 'Users', 'Navigation', 'PollChart', 'LoggingEvents' ],
	autoCreateViewport : true,
	launch : function() {
		
	    Ext.apply(Ext.form.field.VTypes, {
	        password: function(val, field) {
	            if (field.initialPassField) {
	                var pwd = field.up('form').down('#' + field.initialPassField);
	                return (val == pwd.getValue());
	            }
	            return true;
	        },

	        passwordText: 'Passwords do not match'
	    });
		
	}
});
