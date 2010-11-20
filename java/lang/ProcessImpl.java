/*
 * @(#)ProcessImpl.java	1.33 10/03/23
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.lang;

import java.io.*;

/* This class is for the exclusive use of ProcessBuilder.start() to
 * create new processes.
 *
 * @author Martin Buchholz
 * @version 1.33, 10/03/23
 * @since   1.5
 */

final class ProcessImpl extends Process {

    // System-dependent portion of ProcessBuilder.start()
    static Process start(String cmdarray[],
			 java.util.Map<String,String> environment,
			 String dir,
			 boolean redirectErrorStream)
	throws IOException
    {
	String envblock = ProcessEnvironment.toEnvironmentBlock(environment);
	return new ProcessImpl(cmdarray, envblock, dir, redirectErrorStream);
    }

    private long handle = 0;
    private FileDescriptor stdin_fd;
    private FileDescriptor stdout_fd;
    private FileDescriptor stderr_fd;
    private OutputStream stdin_stream;
    private InputStream stdout_stream;
    private InputStream stderr_stream;

    private ProcessImpl(String cmd[],
			String envblock,
			String path,
			boolean redirectErrorStream)
	throws IOException
    {
	// Win32 CreateProcess requires cmd[0] to be normalized
	cmd[0] = new File(cmd[0]).getPath();

	StringBuilder cmdbuf = new StringBuilder(80);
	for (int i = 0; i < cmd.length; i++) {
            if (i > 0) {
                cmdbuf.append(' ');
            }
	    String s = cmd[i];
	    if (s.indexOf(' ') >= 0 || s.indexOf('\t') >= 0) {
	        if (s.charAt(0) != '"') {
		    cmdbuf.append('"');
		    cmdbuf.append(s);
		    if (s.endsWith("\\")) {
			cmdbuf.append("\\");
		    }
		    cmdbuf.append('"');
                } else if (s.endsWith("\"")) {
		    /* The argument has already been quoted. */
		    cmdbuf.append(s);
		} else {
		    /* Unmatched quote for the argument. */
		    throw new IllegalArgumentException();
		}
	    } else {
	        cmdbuf.append(s);
	    }
	}
	String cmdstr = cmdbuf.toString();

	stdin_fd  = new FileDescriptor();
	stdout_fd = new FileDescriptor();
	stderr_fd = new FileDescriptor();

	handle = create(cmdstr, envblock, path, redirectErrorStream,
			stdin_fd, stdout_fd, stderr_fd);

	java.security.AccessController.doPrivileged(
	    new java.security.PrivilegedAction() {
	    public Object run() {
		stdin_stream =
		    new BufferedOutputStream(new FileOutputStream(stdin_fd));
		stdout_stream =
		    new BufferedInputStream(new FileInputStream(stdout_fd));
		stderr_stream =
		    new FileInputStream(stderr_fd);
		return null;
	    }
	});
    }

    public OutputStream getOutputStream() {
	return stdin_stream;
    }

    public InputStream getInputStream() {
	return stdout_stream;
    }

    public InputStream getErrorStream() {
	return stderr_stream;
    }

    public void finalize() {
	close();
    }

    public native int exitValue();
    public native int waitFor();
    public native void destroy();

    private native long create(String cmdstr,
			       String envblock,
			       String dir,
			       boolean redirectErrorStream,
			       FileDescriptor in_fd,
			       FileDescriptor out_fd,
			       FileDescriptor err_fd)
	throws IOException;

    private native void close();
}
