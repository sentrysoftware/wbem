/*
  (C) Copyright IBM Corp. 2006, 2009

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
 * 1565892    2006-11-14  lupusalex    Make SBLIM client JSR48 compliant
 * 1711092    2006-05-02  lupusalex    Some fixes/additions of log&trace messages
 * 2003590    2008-06-30  blaschke-oss Change licensing from CPL to EPL
 * 2524131    2009-01-21  raman_arora  Upgrade client to JDK 1.5 (Phase 1)
 */

package org.sentrysoftware.wbem.sblim.cimclient;

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

import java.util.logging.Level;

/**
 * The interface TraceListener must be implemented if you want to attach your
 * own logging framework to the CIM Client.
 * 
 * @see LogAndTraceManager
 */
public interface TraceListener {

	/**
	 * Receive a trace message.
	 * 
	 * @param pLevel
	 *            One of the message level identifiers, e.g. FINE
	 * @param pOrigin
	 *            The java class/method/line-of-code sending the message. Might
	 *            be <code>null</code> if algorithm failed to determine origin.
	 * @param pMessage
	 *            The message text
	 */
	public void trace(Level pLevel, StackTraceElement pOrigin, String pMessage);

	/**
	 * Receive a trace message.
	 * 
	 * @param pLevel
	 *            One of the message level identifiers, e.g. SEVERE
	 * @param pOrigin
	 *            The java class/method/line-of-code sending the message
	 * @param pMessage
	 *            The message text
	 * @param pThrown
	 *            The throwable associated with the message
	 */
	public void trace(Level pLevel, StackTraceElement pOrigin, String pMessage, Throwable pThrown);

}
