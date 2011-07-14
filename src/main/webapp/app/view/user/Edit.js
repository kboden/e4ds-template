Ext.define('E4ds.view.user.Edit', {
	extend: 'Ext.window.Window',
	alias: 'widget.useredit',

	title: 'Edit User',
	layout: 'fit',
	autoShow: true,
	resizable: true,
	width: 400,
	modal: true,

	initComponent: function() {
		this.items = [ {
			xtype: 'form',
			padding: 5,
			bodyPadding: 10,
			bodyBorder: true,

			defaultType: 'textfield',
			defaults: {
				anchor: '100%'
			},
			
			bodyStyle: {
			    background: 'transparent'
			},
			
			fieldDefaults: {
				msgTarget: 'side'
			},

			items: [ {
				name: 'userName',
				fieldLabel: 'Username'
			}, {
				name: 'firstName',
				fieldLabel: 'First Name',
				allowBlank: false
			}, {
				name: 'name',
				fieldLabel: 'Last Name',
				allowBlank: false
			}, {
				name: 'email',
				fieldLabel: 'Email',
				vtype: 'email',
				allowBlank: false
			}, {
				fieldLabel: 'Password',
				name: 'passwordHash',
				id: 'pass'
			}, {
				fieldLabel: 'Confirm Password',
				name: 'password-confirm',
				vtype: 'password',
				initialPassField: 'pass'
			}, {
				fieldLabel: 'Enabled',
				name: 'enabled',
				xtype: 'checkboxfield',
				inputValue: 'true',
				uncheckedValue: 'false'
			} ],

			buttons: [ {
				xtype: 'button',
				text: 'Save',
				disabled: true,
				formBind: true
			}, {
				text: 'Cancel',
				scope: this,
				handler: this.close
			} ]
		} ];

		this.callParent(arguments);
	}
});