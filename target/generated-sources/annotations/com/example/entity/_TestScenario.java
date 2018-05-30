package com.example.entity;

/** */
@javax.annotation.Generated(value = { "Doma", "2.16.1" }, date = "2018-05-30T15:34:14.930+0700")
public final class _TestScenario extends org.seasar.doma.jdbc.entity.AbstractEntityType<com.example.entity.TestScenario> {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("2.16.1");
    }

    private static final _TestScenario __singleton = new _TestScenario();

    private final org.seasar.doma.jdbc.entity.NamingType __namingType = null;

    /** the scenarioCode */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<java.lang.Object, com.example.entity.TestScenario, java.lang.String, Object> $scenarioCode = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.example.entity.TestScenario.class, java.lang.String.class, java.lang.String.class, () -> new org.seasar.doma.wrapper.StringWrapper(), null, null, "scenarioCode", "scenario_code", __namingType, true, true, false);

    /** the scenarioName */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<java.lang.Object, com.example.entity.TestScenario, java.lang.String, Object> $scenarioName = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.example.entity.TestScenario.class, java.lang.String.class, java.lang.String.class, () -> new org.seasar.doma.wrapper.StringWrapper(), null, null, "scenarioName", "scenario_name", __namingType, true, true, false);

    /** the scenarioType */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<java.lang.Object, com.example.entity.TestScenario, java.lang.String, Object> $scenarioType = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.example.entity.TestScenario.class, java.lang.String.class, java.lang.String.class, () -> new org.seasar.doma.wrapper.StringWrapper(), null, null, "scenarioType", "scenario_type", __namingType, true, true, false);

    /** the errorMessage */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<java.lang.Object, com.example.entity.TestScenario, java.lang.String, Object> $errorMessage = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.example.entity.TestScenario.class, java.lang.String.class, java.lang.String.class, () -> new org.seasar.doma.wrapper.StringWrapper(), null, null, "errorMessage", "error_message", __namingType, true, true, false);

    private final java.util.function.Supplier<org.seasar.doma.jdbc.entity.NullEntityListener<com.example.entity.TestScenario>> __listenerSupplier;

    private final boolean __immutable;

    private final String __catalogName;

    private final String __schemaName;

    private final String __tableName;

    private final boolean __isQuoteRequired;

    private final String __name;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.example.entity.TestScenario, ?>> __idPropertyTypes;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.example.entity.TestScenario, ?>> __entityPropertyTypes;

    private final java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<com.example.entity.TestScenario, ?>> __entityPropertyTypeMap;

    private _TestScenario() {
        __listenerSupplier = () -> ListenerHolder.listener;
        __immutable = false;
        __name = "TestScenario";
        __catalogName = "";
        __schemaName = "";
        __tableName = "";
        __isQuoteRequired = false;
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.example.entity.TestScenario, ?>> __idList = new java.util.ArrayList<>();
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.example.entity.TestScenario, ?>> __list = new java.util.ArrayList<>(4);
        java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<com.example.entity.TestScenario, ?>> __map = new java.util.HashMap<>(4);
        __list.add($scenarioCode);
        __map.put("scenarioCode", $scenarioCode);
        __list.add($scenarioName);
        __map.put("scenarioName", $scenarioName);
        __list.add($scenarioType);
        __map.put("scenarioType", $scenarioType);
        __list.add($errorMessage);
        __map.put("errorMessage", $errorMessage);
        __idPropertyTypes = java.util.Collections.unmodifiableList(__idList);
        __entityPropertyTypes = java.util.Collections.unmodifiableList(__list);
        __entityPropertyTypeMap = java.util.Collections.unmodifiableMap(__map);
    }

    @Override
    public org.seasar.doma.jdbc.entity.NamingType getNamingType() {
        return __namingType;
    }

    @Override
    public boolean isImmutable() {
        return __immutable;
    }

    @Override
    public String getName() {
        return __name;
    }

    @Override
    public String getCatalogName() {
        return __catalogName;
    }

    @Override
    public String getSchemaName() {
        return __schemaName;
    }

    @Override
    public String getTableName() {
        return getTableName(org.seasar.doma.jdbc.Naming.DEFAULT::apply);
    }

    @Override
    public String getTableName(java.util.function.BiFunction<org.seasar.doma.jdbc.entity.NamingType, String, String> namingFunction) {
        if (__tableName.isEmpty()) {
            return namingFunction.apply(__namingType, __name);
        }
        return __tableName;
    }

    @Override
    public boolean isQuoteRequired() {
        return __isQuoteRequired;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preInsert(com.example.entity.TestScenario entity, org.seasar.doma.jdbc.entity.PreInsertContext<com.example.entity.TestScenario> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preInsert(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preUpdate(com.example.entity.TestScenario entity, org.seasar.doma.jdbc.entity.PreUpdateContext<com.example.entity.TestScenario> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preUpdate(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preDelete(com.example.entity.TestScenario entity, org.seasar.doma.jdbc.entity.PreDeleteContext<com.example.entity.TestScenario> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preDelete(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postInsert(com.example.entity.TestScenario entity, org.seasar.doma.jdbc.entity.PostInsertContext<com.example.entity.TestScenario> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postInsert(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postUpdate(com.example.entity.TestScenario entity, org.seasar.doma.jdbc.entity.PostUpdateContext<com.example.entity.TestScenario> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postUpdate(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postDelete(com.example.entity.TestScenario entity, org.seasar.doma.jdbc.entity.PostDeleteContext<com.example.entity.TestScenario> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postDelete(entity, context);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.example.entity.TestScenario, ?>> getEntityPropertyTypes() {
        return __entityPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.EntityPropertyType<com.example.entity.TestScenario, ?> getEntityPropertyType(String __name) {
        return __entityPropertyTypeMap.get(__name);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.example.entity.TestScenario, ?>> getIdPropertyTypes() {
        return __idPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<java.lang.Object, com.example.entity.TestScenario, ?, ?> getGeneratedIdPropertyType() {
        return null;
    }

    @Override
    public org.seasar.doma.jdbc.entity.VersionPropertyType<java.lang.Object, com.example.entity.TestScenario, ?, ?> getVersionPropertyType() {
        return null;
    }

    @Override
    public com.example.entity.TestScenario newEntity(java.util.Map<String, org.seasar.doma.jdbc.entity.Property<com.example.entity.TestScenario, ?>> __args) {
        com.example.entity.TestScenario entity = new com.example.entity.TestScenario();
        if (__args.get("scenarioCode") != null) __args.get("scenarioCode").save(entity);
        if (__args.get("scenarioName") != null) __args.get("scenarioName").save(entity);
        if (__args.get("scenarioType") != null) __args.get("scenarioType").save(entity);
        if (__args.get("errorMessage") != null) __args.get("errorMessage").save(entity);
        return entity;
    }

    @Override
    public Class<com.example.entity.TestScenario> getEntityClass() {
        return com.example.entity.TestScenario.class;
    }

    @Override
    public com.example.entity.TestScenario getOriginalStates(com.example.entity.TestScenario __entity) {
        return null;
    }

    @Override
    public void saveCurrentStates(com.example.entity.TestScenario __entity) {
    }

    /**
     * @return the singleton
     */
    public static _TestScenario getSingletonInternal() {
        return __singleton;
    }

    /**
     * @return the new instance
     */
    public static _TestScenario newInstance() {
        return new _TestScenario();
    }

    private static class ListenerHolder {
        private static org.seasar.doma.jdbc.entity.NullEntityListener<com.example.entity.TestScenario> listener = new org.seasar.doma.jdbc.entity.NullEntityListener<>();
    }

}
