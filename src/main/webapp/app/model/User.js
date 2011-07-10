Ext.define('Simple.model.User', {
	extend : 'Ext.data.Model',
	fields : [ 'id', 'userName', 'name', 'firstName', 'email'],

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