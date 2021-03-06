/*
 * @(#)DefaultSocketFactoryImpl.java	1.5 10/03/23
 * 
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.sun.corba.se.impl.transport;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.ServerSocket;
import java.nio.channels.SocketChannel;
import java.nio.channels.ServerSocketChannel;

import com.sun.corba.se.pept.transport.Acceptor;

import com.sun.corba.se.spi.orb.ORB;
import com.sun.corba.se.spi.transport.ORBSocketFactory;

import com.sun.corba.se.impl.orbutil.ORBConstants;

public class DefaultSocketFactoryImpl
    implements ORBSocketFactory
{
    private ORB orb;

    public void setORB(ORB orb)
    {
	this.orb = orb;
    }

    public ServerSocket createServerSocket(String type, 
					   InetSocketAddress inetSocketAddress)
        throws IOException
    {
	ServerSocketChannel serverSocketChannel = null;
	ServerSocket serverSocket = null;

	if (orb.getORBData().acceptorSocketType().equals(ORBConstants.SOCKETCHANNEL)) {
	    serverSocketChannel = ServerSocketChannel.open();
	    serverSocket = serverSocketChannel.socket();
	} else {
	    serverSocket = new ServerSocket();
	}
	serverSocket.bind(inetSocketAddress);
	return serverSocket;
    }

    public Socket createSocket(String type, 
			       InetSocketAddress inetSocketAddress)
        throws IOException
    {
	SocketChannel socketChannel = null;
	Socket socket = null;

	if (orb.getORBData().connectionSocketType().equals(ORBConstants.SOCKETCHANNEL)) {
	    socketChannel = SocketChannel.open(inetSocketAddress);
	    socket = socketChannel.socket();
	} else {
	    socket = new Socket(inetSocketAddress.getHostName(),
				inetSocketAddress.getPort());
	}

	// Disable Nagle's algorithm (i.e., always send immediately).
	socket.setTcpNoDelay(true);

	return socket;
    }

    public void setAcceptedSocketOptions(Acceptor acceptor,
					 ServerSocket serverSocket,
					 Socket socket)
	throws SocketException
    {
	// Disable Nagle's algorithm (i.e., always send immediately).
	socket.setTcpNoDelay(true);
    }
}

// End of file.
