Ext.define('E4ds.controller.Navigation', {
	extend: 'Ext.app.Controller',

	stores: ['Navigation'],
	views: ['navigation.SideBar'],
	
	refs: [
		{ref: 'tabpanel', selector: 'viewport tabpanel'},
		{ref: 'navigationData', selector: 'sidebar dataview'}
	],

	init: function() {
		this.control({
			'sidebar dataview': {
				itemclick: this.onSideBarItemClick
			 },
			'tabpanel': {
				tabchange: this.syncNavigation
			}
		});
	},

	onSideBarItemClick: function(view, record, item, index, event) {
		var view = record.data.view;
		var tab = this.getTabpanel().child(view);
		if (!tab) {		
			tab = this.getTabpanel().add({xtype: record.data.view, navigationId: record.data.id});
		}
		this.getTabpanel().setActiveTab(tab);
    },

	syncNavigation: function() {
		var activeTabId = this.getTabpanel().getActiveTab().navigationId;
		var currentNavigationId = this.getNavigationData().getSelectionModel().getSelection()[0].data.id;

		if (activeTabId !== currentNavigationId) {
			var record = this.getNavigationStore().find('id', activeTabId);
			this.getNavigationData().getSelectionModel().select(record);
		}
	},

});
