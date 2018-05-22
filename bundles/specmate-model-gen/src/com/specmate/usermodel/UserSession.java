/**
 */
package com.specmate.usermodel;

import org.eclipse.emf.cdo.CDOObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Session</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.specmate.usermodel.UserSession#getId <em>Id</em>}</li>
 *   <li>{@link com.specmate.usermodel.UserSession#getAllowedPathPattern <em>Allowed Path Pattern</em>}</li>
 *   <li>{@link com.specmate.usermodel.UserSession#getLastActive <em>Last Active</em>}</li>
 *   <li>{@link com.specmate.usermodel.UserSession#getAccessRights <em>Access Rights</em>}</li>
 * </ul>
 *
 * @see com.specmate.usermodel.UsermodelPackage#getUserSession()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface UserSession extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.specmate.usermodel.UsermodelPackage#getUserSession_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.specmate.usermodel.UserSession#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Allowed Path Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allowed Path Pattern</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allowed Path Pattern</em>' attribute.
	 * @see #setAllowedPathPattern(String)
	 * @see com.specmate.usermodel.UsermodelPackage#getUserSession_AllowedPathPattern()
	 * @model
	 * @generated
	 */
	String getAllowedPathPattern();

	/**
	 * Sets the value of the '{@link com.specmate.usermodel.UserSession#getAllowedPathPattern <em>Allowed Path Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Allowed Path Pattern</em>' attribute.
	 * @see #getAllowedPathPattern()
	 * @generated
	 */
	void setAllowedPathPattern(String value);

	/**
	 * Returns the value of the '<em><b>Last Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Last Active</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Active</em>' attribute.
	 * @see #setLastActive(long)
	 * @see com.specmate.usermodel.UsermodelPackage#getUserSession_LastActive()
	 * @model
	 * @generated
	 */
	long getLastActive();

	/**
	 * Sets the value of the '{@link com.specmate.usermodel.UserSession#getLastActive <em>Last Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Active</em>' attribute.
	 * @see #getLastActive()
	 * @generated
	 */
	void setLastActive(long value);

	/**
	 * Returns the value of the '<em><b>Access Rights</b></em>' attribute.
	 * The literals are from the enumeration {@link com.specmate.usermodel.AccessRights}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Access Rights</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Access Rights</em>' attribute.
	 * @see com.specmate.usermodel.AccessRights
	 * @see #setAccessRights(AccessRights)
	 * @see com.specmate.usermodel.UsermodelPackage#getUserSession_AccessRights()
	 * @model
	 * @generated
	 */
	AccessRights getAccessRights();

	/**
	 * Sets the value of the '{@link com.specmate.usermodel.UserSession#getAccessRights <em>Access Rights</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Access Rights</em>' attribute.
	 * @see com.specmate.usermodel.AccessRights
	 * @see #getAccessRights()
	 * @generated
	 */
	void setAccessRights(AccessRights value);

} // UserSession