Ext.define('E4ds.view.Viewport', {
	extend: 'Ext.Viewport',
	id: 'viewport',

	layout: {
		type: 'border',
		padding: 5
	},
	defaults: {
		split: true
	},
	
	requires: [ 'Ext.ux.TabReorderer', 'Ext.ux.TabCloseMenu' ],
	
	initComponent: function() {
		this.items = [ {
			region: 'north',
			xtype: 'navigationheader',
			split: false
		}, {
			region: 'center',
			xtype: 'tabpanel',
			plugins: [Ext.create('Ext.ux.TabReorderer'), Ext.create('Ext.ux.TabCloseMenu')],
			plain: true
		}, {
			region: 'west',
			width: 180,
			xtype: 'sidebar'
		} ];

		this.callParent(arguments);
	}

});