Ext.define('Simple.view.navigation.SideBar', {
	alias: 'widget.sidebar',
	extend: 'Ext.panel.Panel',

	title: 'Navigation',
	collapsible: true,
	animCollapse: true,
	//margins: '5 0 5 5',
	layout: 'fit',

	initComponent: function() {
		Ext.apply(this, {
		items: [{
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
			}]
		});


		this.callParent(arguments);

	}
});