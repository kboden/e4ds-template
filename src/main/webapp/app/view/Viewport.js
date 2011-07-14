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

	initComponent: function() {
		this.items = [ {
			region: 'north',
			xtype: 'header',
			split: false
		}, {
			region: 'center',
			xtype: 'tabpanel',
			plain: true
		}, {
			region: 'west',
			width: 180,
			xtype: 'sidebar'
		} ];

		this.callParent(arguments);
	}

});