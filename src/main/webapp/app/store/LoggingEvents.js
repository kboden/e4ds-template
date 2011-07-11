Ext.define('Simple.store.LoggingEvents', {
    extend: 'Ext.data.Store',
    model: 'Simple.model.LoggingEvent',
    autoLoad: false,
    remoteSort: true,
    pageSize : 30
});