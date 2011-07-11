Ext.define('E4ds.view.Viewport', {
	extend : 'Ext.Viewport',
	id : 'viewport',

	layout : {
		type : 'border',
		padding : 5
	},
	defaults : {
		split : true
	},

	initComponent : function() {
		this.items = [ {
			region : 'north',
			html : '<h3 class="header">e4ds-template: Sample project with ExtJs4 and ExtDirectSpring</h3>',
			height : 40,
			cls : 'appHeader',
			split : false
		}, {
			region : 'center',
			xtype : 'tabpanel'
		}, {
			region : 'west',
			width : 180,
			xtype : 'sidebar'
		} ];

		this.callParent(arguments);
	}

});