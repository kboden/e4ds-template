Ext.define('E4ds.model.Role', {
	extend: 'Ext.data.Model',
	fields: [ 'id', 'name' ],

	proxy: {
		type: 'direct',
		directFn: userService.loadAllRoles
	}
});