Ext.define('Simple.view.Viewport', {
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
			html : '<h3 class="header">e4ds-template: Simple project with ExtJs4 and ExtDirectSpring</h3>',
			height : 70,
			cls : 'appHeader',
			split : false
		}, {
			region : 'center',
			xtype : 'tabpanel',
			items : [ {
				xtype : 'userlist'
			} ]
		}, {
			region : 'west',
			width : 180,
			xtype : 'sidebar'
		} ];

		this.callParent(arguments);
	}

});