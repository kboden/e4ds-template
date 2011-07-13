Ext.onReady(function() {
	Ext.QuickTips.init();

	var login = Ext.create('Ext.form.Panel', {
		frame : true,
		title : 'Please Login',
		url : 'j_spring_security_check',
		width : 300,
		
		renderTo : Ext.getBody(),
		
		standardSubmit : true,
		
		defaults : {
			anchor : '100%'
		},
		defaultType : 'textfield',
		items : [ {
			fieldLabel : 'Username',
			name : 'j_username',
			allowBlank : false
		}, {
			fieldLabel : 'Password',
			name : 'j_password',
			allowBlank : false
		} ],
		buttons : [ {
			text : 'Login',
			handler: function() {
	            var form = this.up('form').getForm();
	            if (form.isValid()) {
	                form.submit();
	            }
	        }			
		} ]
	});
	
	login.show();
	login.center();

});