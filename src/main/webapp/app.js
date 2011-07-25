Ext.require('Ext.ux.Notification');

Ext.application({
	name: 'E4ds',
	appFolder: 'app',
	controllers: [ 'Users', 'Navigation', 'PollChart', 'LoggingEvents', 'Config' ],
	autoCreateViewport: true,
	launch: function() {

		Ext.direct.Manager.on('event', function(e) {
			//todo: need a better method to handle session timeouts
			if (e.code && e.code === 'parse') {
				window.location.reload();
			}
		});
		
		Ext.direct.Manager.on('exception', function(e) {	
			if (e.message === 'accessdenied') {
				Ext.ux.Notification.error('Error', 'Access is denied');
			} else {
				Ext.ux.Notification.error('Error', e.message);
			}
		});		
		
		
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
