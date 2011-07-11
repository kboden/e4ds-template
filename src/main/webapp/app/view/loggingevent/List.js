Ext.define('Simple.view.loggingevent.List', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.loggingeventlist',
	store : 'LoggingEvents',

	title : 'Log Events',
	closable : true,

	columns : [ {
		header : 'Timestamp',
		dataIndex : 'dateTime',
		width : 200
	}, {
		header : 'Level',
		dataIndex : 'level',
		width : 70
	}, {
		header : 'Message',
		dataIndex : 'message',
		flex : 1
	}, {
		header : 'Caller Class',
		dataIndex : 'callerClass',
		sortable : false,
		flex : 1
	}, {
		header : 'Caller Line',
		dataIndex : 'callerLine',
		align: 'right',
		sortable : false,
		width : 70
	}],

	initComponent : function() {

		this.dockedItems = [ {
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store : 'LoggingEvents',
			displayInfo : true,
			displayMsg : 'Displaying Log Events {0} - {1} of {2}',
			emptyMsg : 'No Log Events to display'
		} ];

		this.callParent(arguments);
	}

});