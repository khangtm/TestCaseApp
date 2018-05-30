package com.example.dao;

/** */
@org.springframework.stereotype.Repository()
@javax.annotation.Generated(value = { "Doma", "2.16.1" }, date = "2018-05-30T15:34:15.086+0700")
public class TestCaseDAOImpl extends org.seasar.doma.internal.jdbc.dao.AbstractDao implements com.example.dao.TestCaseDAO {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("2.16.1");
    }

    private static final java.lang.reflect.Method __method0 = org.seasar.doma.internal.jdbc.dao.AbstractDao.getDeclaredMethod(com.example.dao.TestCaseDAO.class, "selectAll");

    private static final java.lang.reflect.Method __method1 = org.seasar.doma.internal.jdbc.dao.AbstractDao.getDeclaredMethod(com.example.dao.TestCaseDAO.class, "insert", com.example.entity.TestCase.class);

    /**
     * @param config the config
     */
    @org.springframework.beans.factory.annotation.Autowired()
    public TestCaseDAOImpl(org.seasar.doma.jdbc.Config config) {
        super(config);
    }

    @Override
    public java.util.List<com.example.entity.TestCase> selectAll() {
        entering("com.example.dao.TestCaseDAOImpl", "selectAll");
        try {
            org.seasar.doma.jdbc.query.SqlFileSelectQuery __query = getQueryImplementors().createSqlFileSelectQuery(__method0);
            __query.setMethod(__method0);
            __query.setConfig(__config);
            __query.setSqlFilePath("META-INF/com/example/dao/TestCaseDAO/selectAll.sql");
            __query.setEntityType(com.example.entity._TestCase.getSingletonInternal());
            __query.setCallerClassName("com.example.dao.TestCaseDAOImpl");
            __query.setCallerMethodName("selectAll");
            __query.setResultEnsured(false);
            __query.setResultMappingEnsured(false);
            __query.setFetchType(org.seasar.doma.FetchType.LAZY);
            __query.setQueryTimeout(-1);
            __query.setMaxRows(-1);
            __query.setFetchSize(-1);
            __query.setSqlLogType(org.seasar.doma.jdbc.SqlLogType.FORMATTED);
            __query.prepare();
            org.seasar.doma.jdbc.command.SelectCommand<java.util.List<com.example.entity.TestCase>> __command = getCommandImplementors().createSelectCommand(__method0, __query, new org.seasar.doma.internal.jdbc.command.EntityResultListHandler<com.example.entity.TestCase>(com.example.entity._TestCase.getSingletonInternal()));
            java.util.List<com.example.entity.TestCase> __result = __command.execute();
            __query.complete();
            exiting("com.example.dao.TestCaseDAOImpl", "selectAll", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("com.example.dao.TestCaseDAOImpl", "selectAll", __e);
            throw __e;
        }
    }

    @Override
    public int insert(com.example.entity.TestCase testCase) {
        entering("com.example.dao.TestCaseDAOImpl", "insert", testCase);
        try {
            if (testCase == null) {
                throw new org.seasar.doma.DomaNullPointerException("testCase");
            }
            org.seasar.doma.jdbc.query.AutoInsertQuery<com.example.entity.TestCase> __query = getQueryImplementors().createAutoInsertQuery(__method1, com.example.entity._TestCase.getSingletonInternal());
            __query.setMethod(__method1);
            __query.setConfig(__config);
            __query.setEntity(testCase);
            __query.setCallerClassName("com.example.dao.TestCaseDAOImpl");
            __query.setCallerMethodName("insert");
            __query.setQueryTimeout(-1);
            __query.setSqlLogType(org.seasar.doma.jdbc.SqlLogType.FORMATTED);
            __query.setNullExcluded(false);
            __query.setIncludedPropertyNames();
            __query.setExcludedPropertyNames();
            __query.prepare();
            org.seasar.doma.jdbc.command.InsertCommand __command = getCommandImplementors().createInsertCommand(__method1, __query);
            int __result = __command.execute();
            __query.complete();
            exiting("com.example.dao.TestCaseDAOImpl", "insert", __result);
            return __result;
        } catch (java.lang.RuntimeException __e) {
            throwing("com.example.dao.TestCaseDAOImpl", "insert", __e);
            throw __e;
        }
    }

}
