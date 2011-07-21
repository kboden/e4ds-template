Ext.define('E4ds.view.loggingevent.List', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.loggingeventlist',
	store: 'LoggingEvents',

	title: 'Log Events',
	closable: true,

	requires: [ 'Ext.ux.RowExpander' ],

	constructor: function() {

		this.columns = [ {
			header: 'Timestamp',
			dataIndex: 'dateTime',
			width: 200
		}, {
			header: 'Level',
			dataIndex: 'level',
			width: 70
		}, {
			header: 'Message',
			dataIndex: 'message',
			flex: 1
		}, {
			header: 'Caller Class',
			dataIndex: 'callerClass',
			sortable: false,
			flex: 1
		}, {
			header: 'Caller Line',
			dataIndex: 'callerLine',
			align: 'right',
			sortable: false,
			width: 70
		} ];

		this.plugins = [ {
			ptype: 'rowexpander',
			rowBodyTpl: [ '<tpl if="stacktrace">', '<p>{stacktrace}</p>', '</tpl>', '<tpl if="!stacktrace">',
					'<p>{message}</p>', '</tpl>' ]
		} ];

		this.callParent(arguments);
	},

	initComponent: function() {

		this.dockedItems = [ {
			xtype: 'toolbar',
			dock: 'top',
			items: [ {
				text: 'Excel Export',
				iconCls: 'icon-excel',
				href: 'loggingEventExport.xls',
				target: '_self',
			}, {
				text: 'Delete All',
				action: 'deleteall',
				iconCls: 'icon-delete'
			}, {
				text: 'Add Test Logs',
				action: 'test',
				iconCls: 'icon-add'
			}, '-', {
				xtype: 'combobox',
				fieldLabel: 'Change Log Level',
				name: 'logLevel',
				store: Ext.create('Ext.data.ArrayStore', {
					fields: [ 'level' ],
					data: [ [ 'ERROR' ], [ 'WARN' ], [ 'INFO' ], [ 'DEBUG' ] ]
				}),
				valueField: 'level',
				displayField: 'level',
				queryMode: 'local',
				forceSelection: true,
				value: 'ERROR'
			} ]
		}, {
			xtype: 'pagingtoolbar',
			dock: 'bottom',
			store: 'LoggingEvents',
			displayInfo: true,
			displayMsg: 'Displaying Log Events {0} - {1} of {2}',
			emptyMsg: 'No Log Events to display'
		} ];

		this.callParent(arguments);

	}

});