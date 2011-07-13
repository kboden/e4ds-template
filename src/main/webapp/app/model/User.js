Ext.define('E4ds.model.User', {
	extend : 'Ext.data.Model',
	fields : [ 'id', 'userName', 'name', 'firstName', 'email', 'passwordHash', 
	           { name: 'enabled', type: 'bool' }],

	proxy : {
		type : 'direct',
		api : {
			read : userService.load,
			create : userService.create,
			update : userService.update,
			destroy : userService.destroy
		},
		reader : {
			root : 'records'
		}
	}
});