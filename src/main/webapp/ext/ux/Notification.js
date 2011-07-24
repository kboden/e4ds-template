Ext.define('Ext.ux.Notification', {
	extend: 'Ext.Window',

	statics: {
		positions: []
	},
	
	initComponent: function() {
		Ext.apply(this, {
			iconCls: this.iconCls || 'x-icon-information',
			cls: 'x-notification',
			width: 200,
			draggable: false,
			bodyStyle: 'text-align: center'
		});
		
		if (this.autoDestroy) {
			this.task = new Ext.util.DelayedTask(this.hide, this);
		} 
		
		this.callParent(arguments);
	},

	setMessage: function(msg) {
		this.body.update(msg);
	},

	setTitle: function(title, iconCls) {
		this.setTitle(title);
		this.setIconCls(iconCls || this.iconCls);
	},
	
	onDestroy: function() {
		this.self.positions.splice(this.pos);
		this.callParent();
	},
	
	cancelHiding: function() {		
		if (this.autoDestroy) {
			this.task.cancel();
		}
	},
	
	afterShow: function() {
		this.callParent();
		Ext.fly(this.body.dom).on('click', this.cancelHiding, this);
		if (this.autoDestroy) {
			this.task.delay(this.hideDelay || 5000);
		}
	},

	beforeShow: function() {
		this.el.hide();
	},

	onShow: function() {
		var me = this;

		this.pos = 0;
		while (this.self.positions.indexOf(this.pos) > -1)
			this.pos++;
		this.self.positions.push(this.pos);

		this.el.alignTo(document, "br-br", [ -20, -20 - ((this.getSize().height + 10) * this.pos) ]);
		this.el.slideIn('b', {
			duration: 500,
			listeners: {
				afteranimate: {
					fn: function() {
						me.el.show();
					}
				}
			}
		});

	},
	
	onHide: function() {
		this.el.disableShadow();
		this.el.ghost("b", {
			duration: 500,
			remove: true
		});
		this.self.positions.splice(this.pos);
	},
	
	focus: Ext.emptyFn
});