Ext.define('Simple.store.Users', {
    extend: 'Ext.data.Store',
    model: 'Simple.model.User',
    autoLoad: false,
    remoteSort: true,
    pageSize : 30,
    autoSync : true,
    sorters: [ {
        property: 'name',
        direction: 'ASC'
    }]
});