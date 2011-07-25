Ext.define('E4ds.view.loggingevent.List', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.loggingeventlist',
	store: 'LoggingEvents',

	title: i18n.logevents,
	closable: true,

	requires: [ 'Ext.ux.RowExpander', 'Ext.ux.form.field.ClearButton' ],

	constructor: function() {

		this.columns = [ {
			header: i18n.logevents_timestamp,
			dataIndex: 'dateTime',
			width: 200
		}, {
			header: i18n.logevents_level,
			dataIndex: 'level',
			width: 70
		}, {
			header: i18n.logevents_message,
			dataIndex: 'message',
			flex: 1
		}, {
			header: i18n.logevents_callerclass,
			dataIndex: 'callerClass',
			sortable: false,
			flex: 1
		}, {
			header: i18n.logevents_callerline,
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
				text: i18n.logevents_excelexport,
				action: 'export',
				iconCls: 'icon-excel',
				href: 'loggingEventExport.xls',				
				target: '_self',
			}, '-', {
				text: i18n.logevents_delete,
				action: 'deleteall',
				iconCls: 'icon-delete'
			}, '-', {
				text: i18n.logevents_addtest,
				action: 'test',
				iconCls: 'icon-add'
			}, '->',{
				xtype: 'combobox',
				fieldLabel: i18n.filter,
				labelWidth: 40,
				name: 'logLevelFilter',
				store: 'LogLevels',
				valueField: 'level',
				displayField: 'level',
				queryMode: 'local',
				forceSelection: true,
				plugins: [ 'clearbutton' ]
			} ]
		}, {
			xtype: 'pagingtoolbar',
			dock: 'bottom',
			store: 'LoggingEvents',
			displayInfo: true,
			displayMsg: i18n.logevents_display,
			emptyMsg: i18n.logevents_no
		} ];

		this.callParent(arguments);

	}

});