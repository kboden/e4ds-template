Ext.define('E4ds.view.user.List', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.userlist',
	store: 'Users',

	title: 'Users',
	closable: true,

	columns: [ {
		header: 'ID',
		dataIndex: 'id',
		width: 50,
		sortable: false
	}, {
		header: 'Username',
		dataIndex: 'userName',
		flex: 1
	}, {
		header: 'First Name',
		dataIndex: 'firstName',
		flex: 1
	}, {
		header: 'Last Name',
		dataIndex: 'name',
		flex: 1
	}, {
		header: 'Email',
		dataIndex: 'email',
		flex: 1
	}, {
		header: 'Enabled',
		dataIndex: 'enabled',
		width: 70,
		renderer: function(value) {
			if (value === true) {
				return 'yes';
			}
			return '';
		}
	} ],

	initComponent: function() {

		this.dockedItems = [ {
			xtype: 'toolbar',
			dock: 'top',
			items: [ {
				text: 'New User',
				disabled: false,
				action: 'add',
				iconCls: 'icon-add'
			}, {
				text: 'Edit User',
				disabled: true,
				action: 'edit',
				iconCls: 'icon-edit'
			}, {
				text: 'Delete User',
				disabled: true,
				action: 'delete',
				iconCls: 'icon-delete'
			}, '->', {
				fieldLabel: 'Filter',
				labelWidth: 40,
				xtype: 'trigger',
				triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
				onTriggerClick: function() {
					this.fireEvent('filter', this, this.getValue());
				},
				listeners: {
					specialkey: function(f, e) {
						if (e.getKey() == e.ENTER) {
							this.fireEvent('filter', this, this.getValue());
						}
					},
					change: function(f, val) {
						if (!val) {
							this.fireEvent('filter', this, this.getValue());
						}
					}
				},

				plugins: [ 'clearbutton' ]
			} ]
		}, {
			xtype: 'pagingtoolbar',
			dock: 'bottom',
			store: 'Users',
			displayInfo: true,
			displayMsg: 'Displaying Users {0} - {1} of {2}',
			emptyMsg: 'No Users to display'
		} ];

		this.callParent(arguments);

	}

});