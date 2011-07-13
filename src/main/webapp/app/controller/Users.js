Ext.define('E4ds.controller.Users', {
	extend : 'Ext.app.Controller',

	views : [ 'user.List', 'user.Edit' ],
	stores : [ 'Users' ],
	models : [ 'User' ],
	refs : [ {
		ref : 'userList',
		selector : 'userlist'
	}, {
		ref : 'userEditForm',
		selector : 'useredit form'
	}, {
		ref : 'userEditWindow',
		selector : 'useredit'
	}],

	init : function() {
		this.control({
			'userlist' : {
				itemdblclick : this.editUserFromDblClick,
				itemclick : this.enableActions,
				beforeactivate : this.onBeforeActivate,
			},
			'useredit button[action=save]' : {
				click : this.updateUser
			},
			'userlist button[action=add]': {
				click: this.createUser
			},	
			'userlist button[action=edit]': {
				click: this.editUserFromButton
			},				
			'userlist button[action=delete]' : {
				click : this.deleteUser
			}
		});
	},

	editUserFromDblClick : function(grid, record) {
		this.editUser(record);
	},
	
	editUserFromButton : function() {
		this.editUser(this.getUserList().getSelectionModel().getSelection()[0]);
	},
	
	editUser : function(record) {
		var view = Ext.widget('useredit');
		view.down('form').loadRecord(record);
	},
	
	createUser : function() {
		Ext.widget('useredit');
	},
	
	deleteUser : function(button) {
		var record = this.getUserList().getSelectionModel().getSelection()[0];
		if (record) {
			this.getUsersStore().remove(record);
			this.getUsersStore().sync();
			this.doGridRefresh();
			this.toggleDeleteButton(false);
			this.toogleEditButton(false);
		}
	},
		

	enableActions : function(button, record) {
		this.toggleDeleteButton(true);
		this.toggleEditButton(true);
	},

	toggleDeleteButton : function(enable) {
		var button = this.getUserList().down('button[action=delete]');
		if (enable) {
			button.enable();
		} else {
			button.disable();
		}
	},
	
	toggleEditButton : function(enable) {
		var button = this.getUserList().down('button[action=edit]');
		if (enable) {
			button.enable();
		} else {
			button.disable();
		}
	},	
	
	updateUser : function(button) {
		var form = this.getUserEditForm();
		var record = form.getRecord(); 
		var values = form.getValues();

		if (form.getForm().isValid()) {
			if (record) {
				record.set(values);	
				this.getUsersStore().sync();
			} else {
				var newUser = this.getUserModel().create(values);
				this.getUsersStore().add(newUser);
				this.getUsersStore().sync();
				this.doGridRefresh();
			}
			this.getUserEditWindow().close();
		}				
	},
	
	onBeforeActivate : function(cmp, options) {
		if (options) {
			this.doGridRefresh();
		}
	},
	
	doGridRefresh : function() {
		this.getUserList().down('pagingtoolbar').doRefresh();
	}
	
});