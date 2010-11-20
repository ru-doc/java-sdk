/*
 * @(#)java_md.h	1.19 10/03/23
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

#ifndef JAVA_MD_H
#define JAVA_MD_H

#include <jni.h>
#include <windows.h>
#include <io.h>
#include "manifest_info.h"
#include "jli_util.h"

#define PATH_SEPARATOR	';'
#define FILESEP		"\\"
#define FILE_SEPARATOR	'\\'
#define IS_FILE_SEPARATOR(c) ((c) == '\\' || (c) == '/')
#define MAXPATHLEN      MAX_PATH
#define MAXNAMELEN	MAX_PATH

#ifdef JAVA_ARGS
/*
 * ApplicationHome is prepended to each of these entries; the resulting
 * strings are concatenated (separated by PATH_SEPARATOR) and used as the
 * value of -cp option to the launcher.
 */
#ifndef APP_CLASSPATH
#define APP_CLASSPATH        { "\\lib\\tools.jar", "\\classes" }
#endif
#endif

/*
 * Support for doing cheap, accurate interval timing.
 */
extern jlong CounterGet(void);
extern jlong Counter2Micros(jlong counts);

#ifdef JAVAW
#define main _main
extern int _main(int argc, char **argv);
#endif

/*
 * Function prototypes.
 */
char *LocateJRE(manifest_info *info);
void ExecJRE(char *jre, char **argv);
int UnsetEnv(char *name);

#endif
