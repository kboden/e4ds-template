var poller = new Ext.direct.PollingProvider({
	id: 'chartdatapoller',
	type: 'polling',
	interval: 2000,
	url: Ext.app.POLLING_URLS.chartdata,
});
Ext.direct.Manager.addProvider(Ext.app.REMOTING_API, poller);
Ext.direct.Manager.getProvider('chartdatapoller').disconnect();
