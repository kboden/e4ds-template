Ext.onReady(function() {
	Ext.QuickTips.init();

	var login = Ext.create('Ext.form.Panel', {
		frame: true,
		title: 'Please Login',
		url: 'j_spring_security_check',
		width: 300,
		margin: "60px, 0, 0, 100px",
		renderTo: Ext.getBody(),

		standardSubmit: true,

		defaults: {
			anchor: '100%'
		},
		defaultType: 'textfield',
		items: [ {
			fieldLabel: 'Username',
			name: 'j_username',
			allowBlank: false
		}, {
			fieldLabel: 'Password',
			name: 'j_password',
			inputType: 'password',
			allowBlank: false
		} ],

		buttons: [ {
			text: 'Login with user',
			handler: function() {
				var form = this.up('form').getForm();
				form.setValues({
					j_username: 'user',
					j_password: 'user'
				});
				form.submit();
			}
		}, {
			text: 'Login with admin',
			handler: function() {
				var form = this.up('form').getForm();
				form.setValues({
					j_username: 'admin',
					j_password: 'admin'
				});
				form.submit();
			}
		}, {
			text: 'Login',
			handler: function() {
				var form = this.up('form').getForm();
				if (form.isValid()) {
					form.submit();
				}
			}
		} ]
	});

	login.show();
	login.getForm().findField('j_username').focus();

});