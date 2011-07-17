//override for formBind Bug in Ext4.0.2. Remove this if it's fixed in the framework
Ext.override(Ext.form.Basic, {
	getBoundItems: function() {
		var boundItems = this._boundItems;
		if (!boundItems || boundItems.getCount() == 0) {
			boundItems = this._boundItems = Ext.create('Ext.util.MixedCollection');
			boundItems.addAll(this.owner.query('[formBind]'));
		}
		return boundItems;
	},
	initialize: function() {
		this.callOverridden();
		this.owner.on('beforerender', this.checkValidity, this);
	}
});