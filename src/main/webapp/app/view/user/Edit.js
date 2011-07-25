Ext.define('E4ds.view.user.Edit', {
	extend: 'Ext.window.Window',
	alias: 'widget.useredit',

	title: 'Edit User',
	layout: 'fit',
	autoShow: true,
	resizable: true,
	width: 400,
	modal: true,

	requires: ['Ext.ux.form.ItemSelector'],
	
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
					
			api: {
			    submit: userService.userFormPost
			},
			
			fieldDefaults: {
				msgTarget: 'side'
			},

			items: [ {
				name: 'userName',
				fieldLabel: 'Username',
				allowBlank: false
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
				inputType: 'password',
				id: 'pass'
			}, {
				fieldLabel: 'Confirm Password',
				name: 'password-confirm',
				vtype: 'password',
				inputType: 'password',
				initialPassField: 'pass'
			}, {
                xtype: 'combobox',
                fieldLabel: 'Language',
                name: 'locale',
                store: Ext.create('Ext.data.ArrayStore', {
                    fields: ['code', 'language'],
                    data : [['de', 'German'], ['en', 'English']]
                }),
                valueField: 'code',
                displayField: 'language',
                queryMode: 'local',
                emptyText: 'Select a language...',
                allowBlank: false,
                forceSelection: true
            }, {
				fieldLabel: 'Enabled',
				name: 'enabled',
				xtype: 'checkboxfield',
				inputValue: 'true',
				uncheckedValue: 'false'
			},{
				xtype: 'itemselector',
	            name: 'roleIds',
	            fieldLabel: 'Roles',
	            store: 'Roles',
	            displayField: 'name',
	            valueField: 'id',
	            allowBlank: true
	        } ],

			buttons: [ {
				xtype: 'button',
				text: 'Save',
				action : 'save',
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