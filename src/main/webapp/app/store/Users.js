Ext.define('E4ds.store.Users', {
    extend: 'Ext.data.Store',
    model: 'E4ds.model.User',
    autoLoad: false,
    remoteSort: true,
    pageSize : 30,
    autoSync : true,
    sorters: [ {
        property: 'name',
        direction: 'ASC'
    }]
});