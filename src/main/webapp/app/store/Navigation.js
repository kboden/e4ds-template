Ext.define('Simple.store.Navigation', {
	extend: 'Ext.data.ArrayStore',
	fields: ['id', 'name', 'view'],
	data: [
		[1, 'Users', 'userlist'],
		[2, 'Polling Chart', 'pollchart']
	]
});