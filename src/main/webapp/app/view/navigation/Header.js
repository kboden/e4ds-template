Ext.define('E4ds.view.navigation.Header', {
	alias: 'widget.navigationheader',
	extend: 'Ext.container.Container',
	height: 35,
	layout: {
		type: 'hbox',
		align: 'stretch'
	},

	items: [ {
		html: 'e4ds-template v 0.0.1',
		cls: 'appHeader'
	}, {
		xtype: 'tbspacer',
		flex: 1
	}, {
		xtype: 'label',
		text: 'Logged on: ',
		width: 200,
		margins: {
			top: 6,
			right: 0,
			bottom: 0,
			left: 0
		}
	}, {
		xtype: 'tbspacer',
		width: 20,
	}, {
		xtype: 'button',
		text: 'Logout',
		href: '/j_spring_security_logout',
		target: '_self',
		margins: {
			top: 2,
			right: 0,
			bottom: 10,
			left: 0
		}
	} ]
});