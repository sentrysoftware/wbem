/*
  (C) Copyright IBM Corp. 2006, 2009

  THIS FILE IS PROVIDED UNDER THE TERMS OF THE ECLIPSE PUBLIC LICENSE
  ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS FILE
  CONSTITUTES RECIPIENTS ACCEPTANCE OF THE AGREEMENT.

  You can obtain a current copy of the Eclipse Public License from
  http://www.opensource.org/licenses/eclipse-1.0.php

  @author : Endre Bak, ebak@de.ibm.com
 * 
 * Flag       Date        Prog         Description
 * -------------------------------------------------------------------------------
 * 1660756    2007-02-22  ebak         Embedded object support
 * 1848607    2007-12-11  ebak         Strict EmbeddedObject types
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 */

package org.sentrysoftware.wbem.sblim.cimclient.internal.cimxml.sax;

/*-
 * ╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲
 * WbeM Java Client
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

import org.sentrysoftware.wbem.javax.cim.CIMObjectPath;

import org.sentrysoftware.wbem.sblim.cimclient.internal.util.WBEMConfiguration;

/**
 * This class is intended to store variables which are common for a whole
 * CIM-XML SAX parsing session.
 */
public class SAXSession {

	private CIMObjectPath iDefLocalPath;

	private boolean iStrictEmbObjParsing = WBEMConfiguration.getGlobalConfiguration()
			.strictEmbObjTypes();

	/**
	 * Ctor.
	 * 
	 * @param pDefLocalPath
	 */
	public SAXSession(CIMObjectPath pDefLocalPath) {
		this.iDefLocalPath = pDefLocalPath;
	}

	/**
	 * getDefLocalPath
	 * 
	 * @return CIMObjectPath
	 */
	public CIMObjectPath getDefLocalPath() {
		return this.iDefLocalPath;
	}

	/**
	 * strictEmbObjParsing
	 * 
	 * @return boolean
	 */
	public boolean strictEmbObjParsing() {
		return this.iStrictEmbObjParsing;
	}

}