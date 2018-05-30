package com.example.entity;

/** */
@javax.annotation.Generated(value = { "Doma", "2.16.1" }, date = "2018-05-30T15:34:14.973+0700")
public final class _TestCase extends org.seasar.doma.jdbc.entity.AbstractEntityType<com.example.entity.TestCase> {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("2.16.1");
    }

    private static final _TestCase __singleton = new _TestCase();

    private final org.seasar.doma.jdbc.entity.NamingType __namingType = null;

    /** the testCaseNo */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<java.lang.Object, com.example.entity.TestCase, java.lang.Integer, Object> $testCaseNo = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.example.entity.TestCase.class, java.lang.Integer.class, java.lang.Integer.class, () -> new org.seasar.doma.wrapper.IntegerWrapper(), null, null, "testCaseNo", "test_case_no", __namingType, true, true, false);

    /** the testCaseContent */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<java.lang.Object, com.example.entity.TestCase, java.lang.String, Object> $testCaseContent = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.example.entity.TestCase.class, java.lang.String.class, java.lang.String.class, () -> new org.seasar.doma.wrapper.StringWrapper(), null, null, "testCaseContent", "test_case_content", __namingType, true, true, false);

    /** the preCondition */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<java.lang.Object, com.example.entity.TestCase, java.lang.String, Object> $preCondition = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.example.entity.TestCase.class, java.lang.String.class, java.lang.String.class, () -> new org.seasar.doma.wrapper.StringWrapper(), null, null, "preCondition", "pre_condition", __namingType, true, true, false);

    /** the testStep */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<java.lang.Object, com.example.entity.TestCase, java.lang.String, Object> $testStep = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.example.entity.TestCase.class, java.lang.String.class, java.lang.String.class, () -> new org.seasar.doma.wrapper.StringWrapper(), null, null, "testStep", "test_step", __namingType, true, true, false);

    /** the testData */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<java.lang.Object, com.example.entity.TestCase, java.lang.String, Object> $testData = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.example.entity.TestCase.class, java.lang.String.class, java.lang.String.class, () -> new org.seasar.doma.wrapper.StringWrapper(), null, null, "testData", "test_data", __namingType, true, true, false);

    /** the expectResault */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<java.lang.Object, com.example.entity.TestCase, java.lang.String, Object> $expectResault = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.example.entity.TestCase.class, java.lang.String.class, java.lang.String.class, () -> new org.seasar.doma.wrapper.StringWrapper(), null, null, "expectResault", "expect_resault", __namingType, true, true, false);

    /** the scenarioCode */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<java.lang.Object, com.example.entity.TestCase, java.lang.String, Object> $scenarioCode = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.example.entity.TestCase.class, java.lang.String.class, java.lang.String.class, () -> new org.seasar.doma.wrapper.StringWrapper(), null, null, "scenarioCode", "scenario_code", __namingType, true, true, false);

    /** the testCaseType */
    public final org.seasar.doma.jdbc.entity.DefaultPropertyType<java.lang.Object, com.example.entity.TestCase, java.lang.String, Object> $testCaseType = new org.seasar.doma.jdbc.entity.DefaultPropertyType<>(com.example.entity.TestCase.class, java.lang.String.class, java.lang.String.class, () -> new org.seasar.doma.wrapper.StringWrapper(), null, null, "testCaseType", "test_case_type", __namingType, true, true, false);

    private final java.util.function.Supplier<org.seasar.doma.jdbc.entity.NullEntityListener<com.example.entity.TestCase>> __listenerSupplier;

    private final boolean __immutable;

    private final String __catalogName;

    private final String __schemaName;

    private final String __tableName;

    private final boolean __isQuoteRequired;

    private final String __name;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.example.entity.TestCase, ?>> __idPropertyTypes;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.example.entity.TestCase, ?>> __entityPropertyTypes;

    private final java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<com.example.entity.TestCase, ?>> __entityPropertyTypeMap;

    private _TestCase() {
        __listenerSupplier = () -> ListenerHolder.listener;
        __immutable = false;
        __name = "TestCase";
        __catalogName = "";
        __schemaName = "";
        __tableName = "";
        __isQuoteRequired = false;
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.example.entity.TestCase, ?>> __idList = new java.util.ArrayList<>();
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.example.entity.TestCase, ?>> __list = new java.util.ArrayList<>(8);
        java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<com.example.entity.TestCase, ?>> __map = new java.util.HashMap<>(8);
        __list.add($testCaseNo);
        __map.put("testCaseNo", $testCaseNo);
        __list.add($testCaseContent);
        __map.put("testCaseContent", $testCaseContent);
        __list.add($preCondition);
        __map.put("preCondition", $preCondition);
        __list.add($testStep);
        __map.put("testStep", $testStep);
        __list.add($testData);
        __map.put("testData", $testData);
        __list.add($expectResault);
        __map.put("expectResault", $expectResault);
        __list.add($scenarioCode);
        __map.put("scenarioCode", $scenarioCode);
        __list.add($testCaseType);
        __map.put("testCaseType", $testCaseType);
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
    public void preInsert(com.example.entity.TestCase entity, org.seasar.doma.jdbc.entity.PreInsertContext<com.example.entity.TestCase> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preInsert(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preUpdate(com.example.entity.TestCase entity, org.seasar.doma.jdbc.entity.PreUpdateContext<com.example.entity.TestCase> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preUpdate(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preDelete(com.example.entity.TestCase entity, org.seasar.doma.jdbc.entity.PreDeleteContext<com.example.entity.TestCase> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preDelete(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postInsert(com.example.entity.TestCase entity, org.seasar.doma.jdbc.entity.PostInsertContext<com.example.entity.TestCase> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postInsert(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postUpdate(com.example.entity.TestCase entity, org.seasar.doma.jdbc.entity.PostUpdateContext<com.example.entity.TestCase> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postUpdate(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postDelete(com.example.entity.TestCase entity, org.seasar.doma.jdbc.entity.PostDeleteContext<com.example.entity.TestCase> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postDelete(entity, context);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.example.entity.TestCase, ?>> getEntityPropertyTypes() {
        return __entityPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.EntityPropertyType<com.example.entity.TestCase, ?> getEntityPropertyType(String __name) {
        return __entityPropertyTypeMap.get(__name);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<com.example.entity.TestCase, ?>> getIdPropertyTypes() {
        return __idPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<java.lang.Object, com.example.entity.TestCase, ?, ?> getGeneratedIdPropertyType() {
        return null;
    }

    @Override
    public org.seasar.doma.jdbc.entity.VersionPropertyType<java.lang.Object, com.example.entity.TestCase, ?, ?> getVersionPropertyType() {
        return null;
    }

    @Override
    public com.example.entity.TestCase newEntity(java.util.Map<String, org.seasar.doma.jdbc.entity.Property<com.example.entity.TestCase, ?>> __args) {
        com.example.entity.TestCase entity = new com.example.entity.TestCase();
        if (__args.get("testCaseNo") != null) __args.get("testCaseNo").save(entity);
        if (__args.get("testCaseContent") != null) __args.get("testCaseContent").save(entity);
        if (__args.get("preCondition") != null) __args.get("preCondition").save(entity);
        if (__args.get("testStep") != null) __args.get("testStep").save(entity);
        if (__args.get("testData") != null) __args.get("testData").save(entity);
        if (__args.get("expectResault") != null) __args.get("expectResault").save(entity);
        if (__args.get("scenarioCode") != null) __args.get("scenarioCode").save(entity);
        if (__args.get("testCaseType") != null) __args.get("testCaseType").save(entity);
        return entity;
    }

    @Override
    public Class<com.example.entity.TestCase> getEntityClass() {
        return com.example.entity.TestCase.class;
    }

    @Override
    public com.example.entity.TestCase getOriginalStates(com.example.entity.TestCase __entity) {
        return null;
    }

    @Override
    public void saveCurrentStates(com.example.entity.TestCase __entity) {
    }

    /**
     * @return the singleton
     */
    public static _TestCase getSingletonInternal() {
        return __singleton;
    }

    /**
     * @return the new instance
     */
    public static _TestCase newInstance() {
        return new _TestCase();
    }

    private static class ListenerHolder {
        private static org.seasar.doma.jdbc.entity.NullEntityListener<com.example.entity.TestCase> listener = new org.seasar.doma.jdbc.entity.NullEntityListener<>();
    }

}
