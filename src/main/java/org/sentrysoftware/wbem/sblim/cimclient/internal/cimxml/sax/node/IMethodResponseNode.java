/*
  (C) Copyright IBM Corp. 2006, 2013

  THIS FILE IS PROVIDED UNDER THE TERMS OF THE ECLIPSE PUBLIC LICENSE
  ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS FILE
  CONSTITUTES RECIPIENTS ACCEPTANCE OF THE AGREEMENT.

  You can obtain a current copy of the Eclipse Public License from
  http://www.opensource.org/licenses/eclipse-1.0.php

  @author : Endre Bak, ebak@de.ibm.com
 * 
 * Flag       Date        Prog         Description
 * -------------------------------------------------------------------------------
 * 1565892    2006-12-04  ebak         Make SBLIM client JSR48 compliant
 * 1663270    2007-02-19  ebak         Minor performance problems
 * 1660756    2007-02-22  ebak         Embedded object support
 * 1720707    2007-05-17  ebak         Conventional Node factory for CIM-XML SAX parser
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 * 2763216    2009-04-14  blaschke-oss Code cleanup: visible spelling/grammar errors
 * 2845211    2009-08-27  raman_arora  Pull Enumeration Feature (SAX Parser)
 * 3511454    2012-03-27  blaschke-oss SAX nodes not reinitialized properly
 *    2697    2012-10-30  blaschke-oss (I)MethodResponseNode allows ERROR with PARAMVALUE
 */

package org.sentrysoftware.wbem.sblim.cimclient.internal.cimxml.sax.node;

/*-
 * ╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲
 * WBEM Java Client
 * ჻჻჻჻჻჻
 * Copyright (C) 2023 Sentry Software
 * ჻჻჻჻჻჻
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * ╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱
 */

import java.util.ArrayList;

import org.sentrysoftware.wbem.javax.cim.CIMArgument;

import org.sentrysoftware.wbem.sblim.cimclient.internal.cimxml.sax.SAXSession;
import org.sentrysoftware.wbem.sblim.cimclient.internal.wbem.CIMError;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * <pre>
 * ELEMENT IMETHODRESPONSE (ERROR|IRETURNVALUE?)
 * ATTLIST IMETHODRESPONSE
 *   %CIMName; 
 * </pre>
 */
/**
 * Class IMethodResponseNode is responsible for
 * 
 */
public class IMethodResponseNode extends Node implements ErrorIf, RetValPipeIf, NonVolatileIf {

	private String iName;

	private ErrorNode iErrorNode;

	private IReturnValueNode iRetValNode;

	private ArrayList<CIMArgument<Object>> iCIMArgAL;

	private static final CIMArgument<?>[] EMPTY_ARG_A = new CIMArgument[0];

	private boolean iHasError;

	private boolean iHasRetVal;

	/**
	 * Ctor.
	 */
	public IMethodResponseNode() {
		super(IMETHODRESPONSE);
	}

	public void addChild(Node pChild) {
		if (pChild instanceof IReturnValueNode) {
			this.iRetValNode = (IReturnValueNode) pChild;
		} else if (pChild instanceof ErrorNode) {
			this.iErrorNode = (ErrorNode) pChild;
		}
	}

	/**
	 * @param pSession
	 */
	@Override
	public void init(Attributes pAttribs, SAXSession pSession) throws SAXException {
		this.iName = getCIMName(pAttribs);
		this.iErrorNode = null;
		this.iRetValNode = null;
		if (this.iCIMArgAL != null) this.iCIMArgAL.clear();
		this.iHasError = false;
		this.iHasRetVal = false;
	}

	/**
	 * @param pData
	 */
	@Override
	public void parseData(String pData) {
	// no data
	}

	@Override
	public void testChild(String pNodeNameEnum) throws SAXException {
		if (pNodeNameEnum == ERROR) {
			String ownedNodeName;
			if (this.iHasRetVal) ownedNodeName = IRETURNVALUE;
			else if (this.iHasError) ownedNodeName = ERROR;
			else if (this.iCIMArgAL != null && this.iCIMArgAL.size() > 0) ownedNodeName = PARAMVALUE;
			else ownedNodeName = null;
			if (ownedNodeName != null) throw new SAXException(pNodeNameEnum
					+ " child node is invalid for " + getNodeName()
					+ " node, since it already has a " + ownedNodeName + " child node!");
		} else if (pNodeNameEnum == IRETURNVALUE) {
			String ownedNodeName;
			if (this.iHasRetVal) ownedNodeName = IRETURNVALUE;
			else if (this.iHasError) ownedNodeName = ERROR;
			else ownedNodeName = null;
			if (ownedNodeName != null) throw new SAXException(pNodeNameEnum
					+ " child node is invalid for " + getNodeName()
					+ " node, since it already has a " + ownedNodeName + " child node!");
		} else if (pNodeNameEnum == PARAMVALUE) {
			if (this.iHasError) throw new SAXException(pNodeNameEnum
					+ " child node is invalid for " + getNodeName()
					+ " node, since it already has an ERROR child node!");
		} else throw new SAXException(getNodeName() + " node cannot have " + pNodeNameEnum
				+ " child node!");
	}

	/**
	 * @param pChild
	 */
	@Override
	public void childParsed(Node pChild) {
		if (pChild instanceof ErrorNode) {
			this.iHasError = true;
			this.iErrorNode = (ErrorNode) pChild;
		} else if (pChild instanceof IReturnValueNode) {
			this.iHasRetVal = true;
			this.iRetValNode = (IReturnValueNode) pChild;
		} else
		// Values of parameters should be stored in array
		if (pChild instanceof ParamValueNode) {
			if (this.iCIMArgAL == null) this.iCIMArgAL = new ArrayList<CIMArgument<Object>>();
			this.iCIMArgAL.add(((ParamValueNode) pChild).getCIMArgument());
		}
	}

	@Override
	public void testCompletness() {
	// no mandatory child nodes
	}

	public CIMError getCIMError() {
		return this.iErrorNode == null ? null : this.iErrorNode.getCIMError();
	}

	public int getReturnValueCount() {
		return this.iRetValNode == null ? 0 : this.iRetValNode.getReturnValueCount();
	}

	public Object readReturnValue() {
		return this.iRetValNode.readReturnValue();
	}

	/**
	 * getName
	 * 
	 * @return String
	 */
	public String getName() {
		return this.iName;
	}

	/**
	 * getCIMArguments : returns the array of parsed parameters and their values
	 * : String name, CIMDataType type, Object value
	 * 
	 * @return CIMArgument&lt;?&gt;[]
	 */
	public CIMArgument<?>[] getCIMArguments() {
		if (this.iCIMArgAL == null || this.iCIMArgAL.size() == 0) return null;
		return this.iCIMArgAL.toArray(EMPTY_ARG_A);
	}

}
