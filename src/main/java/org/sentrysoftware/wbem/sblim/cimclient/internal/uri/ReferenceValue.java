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
 * 1565892    2006-11-05  ebak         Make SBLIM client JSR48 compliant
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 */

package org.sentrysoftware.wbem.sblim.cimclient.internal.uri;

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

import org.sentrysoftware.wbem.sblim.cimclient.internal.util.MOF;
import org.sentrysoftware.wbem.sblim.cimclient.internal.util.Util;

/**
 * Class UntypedReferenceValue encapsulates an untyped reference value.
 */
public class ReferenceValue extends Value implements QuotedValue {

	private URI iRef;

	/**
	 * Ctor.
	 * 
	 * @param pRef
	 */
	public ReferenceValue(URI pRef) {
		this.iRef = pRef;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.iRef.toString();
	}

	/**
	 * @see QuotedValue#toQuotedString()
	 */
	public String toQuotedString() {
		return Util.quote(this.iRef.toString());
	}

	/**
	 * getRef
	 * 
	 * @return <code>URI</code>
	 */
	public URI getRef() {
		return this.iRef;
	}

	/**
	 * @see Value#getTypeInfo()
	 */
	@Override
	public String getTypeInfo() {
		return MOF.REFERENCE;
	}

}
