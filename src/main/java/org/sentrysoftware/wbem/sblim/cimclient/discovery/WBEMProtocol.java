/*
  (C) Copyright IBM Corp. 2007, 2009

  THIS FILE IS PROVIDED UNDER THE TERMS OF THE ECLIPSE PUBLIC LICENSE
  ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS FILE
  CONSTITUTES RECIPIENTS ACCEPTANCE OF THE AGREEMENT.

  You can obtain a current copy of the Eclipse Public License from
  http://www.opensource.org/licenses/eclipse-1.0.php

  @author : Alexander Wolf-Reber, IBM, a.wolf-reber@de.ibm.com
 * 
 * Change History
 * Flag       Date        Prog         Description
 *------------------------------------------------------------------------------- 
 * 1678915    2007-03-29  lupusalex    Integrated WBEM service discovery via SLP
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 */

package org.sentrysoftware.wbem.sblim.cimclient.discovery;

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

/**
 * Class WBEMProtocol encapsulates a transport/presentation protocol pair. E.g.
 * "HTTPS/CIM-XML"
 * 
 * <code>iTransport != null<br />iPresentation != null</code>
 * @since 2.0.2
 */
public class WBEMProtocol {

	private String iTransport;

	private String iPresentation;

	/**
	 * Ctor.
	 * 
	 * @param pTransport
	 *            The transport protocol (e.g. HTTP, HTTPS, RMI)
	 * @param pPresentation
	 *            The presentation protocol (e.g. CIM-XML)
	 */
	public WBEMProtocol(String pTransport, String pPresentation) {
		this.iTransport = pTransport != null ? pTransport.toUpperCase() : "";
		this.iPresentation = pPresentation != null ? pPresentation.toUpperCase() : "";
	}

	/**
	 * Returns the presentation protocol (e.g. CIM-XML)
	 * 
	 * @return The value.
	 */
	public String getPresentation() {
		return this.iPresentation;
	}

	/**
	 * Sets the presentation protocol
	 * 
	 * @param pPresentation
	 *            The new value (e.g. CIM-XML)
	 */
	public void setPresentation(String pPresentation) {
		this.iPresentation = pPresentation != null ? pPresentation : "";
	}

	/**
	 * Returns transport protocol (e.g. HTTP)
	 * 
	 * @return The value.
	 */
	public String getTransport() {
		return this.iTransport;
	}

	/**
	 * Sets the transport protocol
	 * 
	 * @param pTransport
	 *            The new value (e.g. HTTP).
	 */
	public void setTransport(String pTransport) {
		this.iTransport = pTransport != null ? pTransport : "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object pObj) {
		if (pObj instanceof WBEMProtocol) {
			WBEMProtocol that = (WBEMProtocol) pObj;
			return (this.iTransport.equals(that.iTransport))
					&& (this.iPresentation.equals(that.iPresentation));
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.iTransport.hashCode() + this.iPresentation.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.iTransport + "/" + this.iPresentation;
	}

}
