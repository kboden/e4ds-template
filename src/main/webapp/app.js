//Uncomment this line if you use ext-debug.js in index.html
//Ext.Loader.setPath('Ext', 'http://www.ralscha.ch/ext-4.0.2a/src');

Ext.Loader.setConfig({
	enabled : true
});
Ext.Loader.setPath('Ext.ux', 'http://www.ralscha.ch/ext-4.0.2a/examples/ux');

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
	name : 'Simple',
	appFolder : 'app',
	controllers : [ 'Users', 'Navigation', 'PollChart', 'LoggingEvents' ],
	autoCreateViewport : true
});
