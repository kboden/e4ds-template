Ext.define('E4ds.view.user.Edit', {
	extend : 'Ext.window.Window',
	alias : 'widget.useredit',

	title : 'Edit User',
	layout : 'fit',
	autoShow : true,
	resizable : false,

	initComponent : function() {
		this.items = [ {
			xtype : 'form',
			padding : '5 5 0 5',
			border : false,
			style : 'background-color: #fff;',
			defaults : {
				width : 400
			},
			items : [ {
				xtype : 'textfield',
				name : 'userName',
				fieldLabel : 'Username'
			}, {
				xtype : 'textfield',
				name : 'firstName',
				fieldLabel : 'First Name',
				allowBlank : false
			}, {
				xtype : 'textfield',
				name : 'name',
				fieldLabel : 'Last Name',
				allowBlank : false
			}, {
				xtype : 'textfield',
				name : 'email',
				fieldLabel : 'Email',
				vtype : 'email',
				allowBlank : false
			} ]
		} ];

		this.buttons = [ {
			text : 'Save',
			action : 'save'
		}, {
			text : 'Cancel',
			scope : this,
			handler : this.close
		} ];

		this.callParent(arguments);
	}
});