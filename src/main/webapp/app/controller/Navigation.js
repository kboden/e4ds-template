Ext.define('E4ds.controller.Navigation', {
	extend: 'Ext.app.Controller',

	stores: [ 'Navigation' ],
	views: [ 'navigation.SideBar', 'navigation.Header' ],

	refs: [ {
		ref: 'tabpanel',
		selector: 'viewport tabpanel'
	}, {
		ref: 'navigationData',
		selector: 'sidebar dataview'
	}, {
		ref: 'loggedOnLabel',
		selector: 'viewport header label'
	}],

	init: function() {
		this.control({
			'sidebar dataview': {
				itemclick: this.onSideBarItemClick
			},
			'tabpanel': {
				tabchange: this.syncNavigation
			}
		});
		securityService.getLoggedOnUser(this.showLoggedOnUser, this);
	},

	showLoggedOnUser: function(fullname) {
		this.getLoggedOnLabel().setText('Logged on: ' + fullname);
	},
	
	onSideBarItemClick: function(dataview, record, item, index, event) {
		var view = record.data.view, tab = this.getTabpanel().child(view);
		if (!tab) {
			tab = this.getTabpanel().add({
				xtype: record.data.view,
				navigationId: record.data.id
			});
		}
		this.getTabpanel().setActiveTab(tab);
	},

	syncNavigation: function() {
		var activeTabId = this.getTabpanel().getActiveTab().navigationId, currentNavigationId = this
				.getNavigationData().getSelectionModel().getSelection()[0].data.id;

		if (activeTabId !== currentNavigationId) {
			var record = this.getNavigationStore().find('id', activeTabId);
			this.getNavigationData().getSelectionModel().select(record);
		}
	},

});
