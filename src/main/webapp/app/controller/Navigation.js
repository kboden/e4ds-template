Ext.define('Simple.controller.Navigation', {
	extend: 'Ext.app.Controller',

	stores: ['Navigation'],

	views: ['navigation.SideBar', 'user.List', 'poll.PollChart'],

	refs: [
		{ref: 'tabpanel', selector: 'viewport tabpanel'},
		{ref: 'navigation', selector: 'sidebar'},
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

	onLaunch: function(app) {
		var nav = this.getNavigationData(),
			viewport = Ext.ComponentQuery.query('viewport')[0],
			centerView = viewport.getLayout().regions.center;
		this.tabPanel = centerView;
		nav.getSelectionModel().select(0);
	},

	onSideBarItemClick: function(view, record, item, index, event) {
		if (index == 1 && !this.chartTab) {
			 this.chartTab = this.tabPanel.add({
				xtype: 'pollchart'
			 });
		}
		this.tabPanel.setActiveTab(index);
		this.activeTab = index;

    },

	syncNavigation: function() {
		if (this.activeTab !== undefined) {
			this.activeTab = this.tabPanel.activeTab.xtype == 'userlist' ? 0 : 1;
			var nav = this.getNavigationData(),
				index = nav.getSelectionModel().getSelection()[0].index;

			if (this.activeTab !== index) {
				nav.getSelectionModel().select(this.activeTab);
			}
		}
	},

});
