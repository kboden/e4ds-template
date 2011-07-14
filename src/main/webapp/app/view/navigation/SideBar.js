Ext.define('E4ds.view.navigation.SideBar', {
	alias: 'widget.sidebar',
	extend: 'Ext.panel.Panel',

	title: 'Navigation',
	collapsible: true,
	animCollapse: true,
	layout: 'fit',
	minWidth: 100,
	maxWidth: 200,

	initComponent: function() {
		this.items = [ {
			xtype: 'dataview',
			trackOver: true,
			store: 'Navigation',
			cls: 'feed-list',
			itemSelector: '.navi',
			overItemCls: 'item-navi-over',

			selModel: {
				deselectOnContainerClick: false
			},

			tpl: '<tpl for="."><div class="navi">{name}</div></tpl>'
		} ];

		this.callParent(arguments);

	}
});