package com.specmate.migration.test.support;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.Dictionary;

import org.junit.Assert;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTracker;

import com.specmate.common.SpecmateException;
import com.specmate.dbprovider.api.IDBProvider;
import com.specmate.dbprovider.api.migration.IAttributeToSQLMapper;
import com.specmate.dbprovider.api.migration.IDataType;
import com.specmate.dbprovider.api.migration.IObjectToSQLMapper;
import com.specmate.migration.api.IMigrator;
import com.specmate.migration.test.AddAttributeTest;
import com.specmate.migration.test.AddObjectTest;
import com.specmate.migration.test.AddSeveralAttributesTest;
import com.specmate.migration.test.ChangedTypesTest;
import com.specmate.migration.test.OnlyMetaChangeTest;
import com.specmate.migration.test.RenamedAttributeTest;

import specmate.dbprovider.h2.H2DataType;

@Component(property = "sourceVersion=0")
public class TestMigratorImpl implements IMigrator {
	public static final String PID = "com.specmate.migration.test.support.TestMigratorImpl";
	public static final String KEY_MIGRATOR_TEST = "testcase";
	public static final Date DEFAULT_DATE = new Date(118, 5, 10);
	private String packageName = "testmodel/artefact";
	private IDBProvider dbProvider;

	@Override
	public String getSourceVersion() {
		return "0";
	}

	@Override
	public String getTargetVersion() {
		return "1";
	}

	@Override
	public void migrate(Connection connection) throws SpecmateException {
		BundleContext context = FrameworkUtil.getBundle(TestMigratorImpl.class).getBundleContext();
		try {
			ConfigurationAdmin ca = getConfigurationAdmin(context);
			Dictionary<String, Object> props = ca.getConfiguration(PID).getProperties();
			String testcase = (String) props.get(KEY_MIGRATOR_TEST);
			if (testcase.equals(AddAttributeTest.class.getName())) {
				migrateAttributeAdded(connection);
			} else if (testcase.equals(AddSeveralAttributesTest.class.getName())) {
				migrateSeveralAttributesAdded(connection);
			} else if (testcase.equals(AddObjectTest.class.getName())) {
				migrateObjectAdded(connection);
			} else if (testcase.equals(RenamedAttributeTest.class.getName())) {
				migrateAttributeRenamed(connection);
			} else if (testcase.equals(ChangedTypesTest.class.getName())) {
				migrateTypesChanged(connection);
			} else if (testcase.equals(OnlyMetaChangeTest.class.getName())) {
				migrateOnlyMetaDataChange();
			}

		} catch (InterruptedException | IOException e) {
			throw new SpecmateException(e.getMessage());
		}
	}

	private void migrateAttributeAdded(Connection connection) throws SpecmateException {
		IAttributeToSQLMapper aAdded = dbProvider.getAttributeToSQLMapper(packageName, getSourceVersion(),
				getTargetVersion());
		aAdded.migrateNewStringAttribute("folder", "name", "");
		aAdded.migrateNewStringAttribute("diagram", "name", null);
		aAdded.migrateNewStringAttribute("sketch", "name", null);
		aAdded.migrateNewDateAttribute("diagram", "created", DEFAULT_DATE);
	}

	private void migrateSeveralAttributesAdded(Connection connection) throws SpecmateException {
		IAttributeToSQLMapper aAdded = dbProvider.getAttributeToSQLMapper(packageName, getSourceVersion(),
				getTargetVersion());
		aAdded.migrateNewStringAttribute("folder", "name", "");
		aAdded.migrateNewStringAttribute("diagram", "name", null);
		aAdded.migrateNewStringAttribute("sketch", "name", null);
		aAdded.migrateNewBooleanAttribute("diagram", "linked", false);
		aAdded.migrateNewDoubleAttribute("diagram", "length", null);
		aAdded.migrateNewIntegerAttribute("diagram", "amount", -1);
		aAdded.migrateNewIntegerAttribute("diagram", "intamount", -1);
		aAdded.migrateNewDoubleAttribute("diagram", "doublelength", 0.0);
		aAdded.migrateNewBooleanAttribute("diagram", "booleanlinked", false);
	}

	private void migrateObjectAdded(Connection connection) throws SpecmateException {
		String objectName = "Document";
		IObjectToSQLMapper oAdded = dbProvider.getObjectToSQLMapper(packageName, getSourceVersion(),
				getTargetVersion());
		oAdded.newObject(objectName);

		IAttributeToSQLMapper aAdded = dbProvider.getAttributeToSQLMapper(packageName, getSourceVersion(),
				getTargetVersion());
		aAdded.migrateNewStringAttribute(objectName, "id", "");
		aAdded.migrateNewBooleanAttribute(objectName, "tested", false);
		aAdded.migrateNewLongAttribute(objectName, "length", null);
		aAdded.migrateNewStringAttribute(objectName, "owner", null);
		aAdded.migrateNewReference(objectName, "contents");
	}

	private void migrateAttributeRenamed(Connection connection) throws SpecmateException {
		IAttributeToSQLMapper aRenamed = dbProvider.getAttributeToSQLMapper(packageName, getSourceVersion(),
				getTargetVersion());
		aRenamed.migrateRenameAttribute("Diagram", "tested", "istested");
		aRenamed.migrateRenameAttribute("Sketch", "tested", "istested");
	}

	private void migrateTypesChanged(Connection connection) throws SpecmateException {
		IAttributeToSQLMapper aTypeChanged = dbProvider.getAttributeToSQLMapper(packageName, getSourceVersion(),
				getTargetVersion());

		aTypeChanged.migrateChangeType("Sketch", "shortVar1", H2DataType.INT);
		aTypeChanged.migrateChangeType("Sketch", "shortVar2", H2DataType.LONG);
		aTypeChanged.migrateChangeType("Sketch", "shortVar3", H2DataType.FLOAT);
		aTypeChanged.migrateChangeType("Sketch", "shortVar4", H2DataType.DOUBLE);

		aTypeChanged.migrateChangeType("Sketch", "charVar1", H2DataType.INT);
		aTypeChanged.migrateChangeType("Sketch", "charVar2", H2DataType.LONG);
		aTypeChanged.migrateChangeType("Sketch", "charVar3", H2DataType.FLOAT);
		aTypeChanged.migrateChangeType("Sketch", "charVar4", H2DataType.DOUBLE);
		IDataType charVar5 = H2DataType.STRING;
		charVar5.setSize(1);
		aTypeChanged.migrateChangeType("Sketch", "charVar5", charVar5);

		aTypeChanged.migrateChangeType("Sketch", "intVar1", H2DataType.LONG);
		aTypeChanged.migrateChangeType("Sketch", "intVar2", H2DataType.FLOAT);
		aTypeChanged.migrateChangeType("Sketch", "intVar3", H2DataType.DOUBLE);

		aTypeChanged.migrateChangeType("Sketch", "longVar1", H2DataType.FLOAT);
		aTypeChanged.migrateChangeType("Sketch", "longVar2", H2DataType.DOUBLE);

		aTypeChanged.migrateChangeType("Sketch", "floatVar1", H2DataType.DOUBLE);

		IDataType booleanVar1 = H2DataType.STRING;
		booleanVar1.setSize(16);
		aTypeChanged.migrateChangeType("Sketch", "booleanVar1", booleanVar1);

		aTypeChanged.migrateChangeType("Sketch", "stringVar1", H2DataType.BOOLEAN);
		aTypeChanged.migrateChangeType("Sketch", "stringVar2", H2DataType.BOOLEAN);
		aTypeChanged.migrateChangeType("Sketch", "stringVar3", H2DataType.BOOLEAN);
		aTypeChanged.migrateChangeType("Sketch", "stringVar4", H2DataType.BOOLEAN);
		aTypeChanged.migrateChangeType("Sketch", "stringVar5", H2DataType.BOOLEAN);
	}

	private void migrateOnlyMetaDataChange() {
		// Nothing to do
	}

	private ConfigurationAdmin getConfigurationAdmin(BundleContext context) throws InterruptedException {
		ServiceTracker<ConfigurationAdmin, ConfigurationAdmin> configurationAdminTracker = new ServiceTracker<>(context,
				ConfigurationAdmin.class.getName(), null);

		configurationAdminTracker.open();
		ConfigurationAdmin configurationAdmin = configurationAdminTracker.waitForService(10000);
		Assert.assertNotNull(configurationAdmin);
		return configurationAdmin;
	}

	@Reference
	public void setDBProvider(IDBProvider dbProvider) {
		this.dbProvider = dbProvider;
	}
}
