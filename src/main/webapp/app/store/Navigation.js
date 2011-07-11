Ext.define('E4ds.store.Navigation', {
	extend: 'Ext.data.ArrayStore',
	fields: ['id', 'name', 'view'],
	data: [
		[1, 'Users', 'userlist'],
		[2, 'Polling Chart', 'pollchart'],
		[3, 'Log Events', 'loggingeventlist']
	]
});