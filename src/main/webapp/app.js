Ext.require('Ext.direct.*', function() {
	var poller = new Ext.direct.PollingProvider({
		id : 'chartdatapoller',
		type : 'polling',
		interval : 2000,
		url : Ext.app.POLLING_URLS.chartdata,
	});
	Ext.direct.Manager.addProvider(Ext.app.REMOTING_API, poller);
	Ext.direct.Manager.getProvider('chartdatapoller').disconnect();
});

Ext.application({
	name : 'E4ds',
	appFolder : 'app',
	controllers : [ 'Users', 'Navigation', 'PollChart', 'LoggingEvents' ],
	autoCreateViewport : true,
	launch : function() {
		
	    Ext.apply(Ext.form.field.VTypes, {
	        password: function(val, field) {
	            if (field.initialPassField) {
	                var pwd = field.up('form').down('#' + field.initialPassField);
	                return (val == pwd.getValue());
	            }
	            return true;
	        },

	        passwordText: 'Passwords do not match'
	    });
	    
	    //todo
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
	    	 initialize: function(){
	    	  this.callOverridden();
	    	  this.owner.on('beforerender', this.checkValidity, this);
	    	 }
	    	});  
		
	}
});
