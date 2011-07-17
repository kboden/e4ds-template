Ext.application({
	name: 'E4ds',
	appFolder: 'app',
	controllers: [ 'Users', 'Navigation', 'PollChart', 'LoggingEvents' ],
	autoCreateViewport: true,
	launch: function() {

		Ext.apply(Ext.form.field.VTypes, {
			password: function(val, field) {
				if (field.initialPassField) {
					var pwd = field.up('form').down('#' + field.initialPassField);
					return (val == pwd.getValue());
				}
				return true;
			},

			passwordText: 'Passwords do not match'
		});

	}
});
