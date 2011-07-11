Ext.define('E4ds.view.user.List', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.userlist',
	store : 'Users',

	title : 'Users',
	closable : true,
	
	columns : [ {
		header : 'ID',
		dataIndex : 'id',
		width : 50,
		sortable : false
	}, {
		header : 'Username',
		dataIndex : 'userName',
		flex : 1
	}, {
		header : 'First Name',
		dataIndex : 'firstName',
		flex : 1
	}, {
		header : 'Last Name',
		dataIndex : 'name',
		flex : 1
	}, {
		header : 'Email',
		dataIndex : 'email',
		flex : 1
	} ],

	initComponent : function() {
		
		this.dockedItems = [ {
			xtype : 'toolbar',
			dock : 'top',
			items : [ {
				text : 'New User',
				disabled : false,
				action : 'add',
				iconCls : 'icon-add'
			}, {
				text : 'Edit User',
				disabled : true,
				action : 'edit',
				iconCls : 'icon-edit'
			}, {
				text : 'Delete User',
				disabled : true,
				action : 'delete',
				iconCls : 'icon-delete'
			} ]
		}, {
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store : 'Users',
			displayInfo : true,
			displayMsg : 'Displaying Users {0} - {1} of {2}',
			emptyMsg : 'No Users to display'
		} ];
		
		this.callParent(arguments);
		
	}
	


});