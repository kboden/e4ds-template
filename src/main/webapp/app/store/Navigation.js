Ext.define('Simple.store.Navigation', {
	extend: 'Ext.data.ArrayStore',
	fields: ['id', 'name'],
	data: [
		[1, 'User Management'],
		[2, 'Polling Chart']
	]
});