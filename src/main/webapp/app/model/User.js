Ext.define('E4ds.model.User', {
	extend: 'Ext.data.Model',
	fields: [ 'id', 'locale', 'userName', 'name', 'firstName', 'email', 'passwordHash', {
		name: 'enabled',
		type: 'bool'
	} ],

	proxy: {
		type: 'direct',
		api: {
			read: userService.load,
			destroy: userService.destroy
		},
		reader: {
			root: 'records'
		}
	}
});