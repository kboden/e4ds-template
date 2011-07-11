Ext.define('Simple.model.LoggingEvent', {
	extend : 'Ext.data.Model',
	fields : [ 'dateTime', 'message', 'level', 'callerClass', 'callerLine', 'ip', 'stacktrace'],

	proxy : {
		type : 'direct',
		directFn : loggingEventService.load,
		reader : {
			root : 'records'
		}
	}
});