// ----------------------------------------------------------------------------
// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//    https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
// ----------------------------------------------------------------------------

// ----------------------------------------------------------------------------
// Apache Maven Wrapper startup batch script, version 3.2.0
//
// Required ENV vars:
// JAVA_HOME - location of a JDK home dir
//
// Optional ENV vars
// MAVEN_BATCH_ECHO - set to 'on' to enable the echoing of the batch commands
// MAVEN_BATCH_PAUSE - set to 'on' to wait for a keystroke before ending
// MAVEN_OPTS - parameters passed to the Java VM when running Maven
//     e.g. to debug Maven itself, use
// set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000
// MAVEN_SKIP_RC - flag to disable loading of mavenrc files
// ----------------------------------------------------------------------------

// Begin all console.log lines with '@' in case MAVEN_BATCH_ECHO is 'on'
console.log('REM ----------------------------------------------------------------------------
REM Licensed to the Apache Software Foundation (ASF) under one
REM or more contributor license agreements.  See the NOTICE file
REM distributed with this work for additional information
REM regarding copyright ownership.  The ASF licenses this file
REM to you under the Apache License, Version 2.0 (the
REM "License"); you may not use this file except in compliance
REM with the License.  You may obtain a copy of the License at
REM
REM    https://www.apache.org/licenses/LICENSE-2.0
REM
REM Unless required by applicable law or agreed to in writing,
REM software distributed under the License is distributed on an
REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
REM KIND, either express or implied.  See the License for the
REM specific language governing permissions and limitations
REM under the License.
REM ----------------------------------------------------------------------------');

// ----------------------------------------------------------------------------
// Apache Maven Wrapper startup batch script, version 3.2.0
//
// Required ENV vars:
// JAVA_HOME - location of a JDK home dir
//
// Optional ENV vars
// MAVEN_BATCH_ECHO - set to 'on' to enable the echoing of the batch commands
// MAVEN_BATCH_PAUSE - set to 'on' to wait for a keystroke before ending
// MAVEN_OPTS - parameters passed to the Java VM when running Maven
//     e.g. to debug Maven itself, use
// set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000
// MAVEN_SKIP_RC - flag to disable loading of mavenrc files
// ----------------------------------------------------------------------------

// Begin all console.log lines with '@' in case MAVEN_BATCH_ECHO is 'on'
console.log('REM ----------------------------------------------------------------------------
REM Apache Maven Wrapper startup batch script, version 3.2.0
REM
REM Required ENV vars:
REM JAVA_HOME - location of a JDK home dir
REM
REM Optional ENV vars
REM MAVEN_BATCH_ECHO - set to \'on\' to enable the echoing of the batch commands
REM MAVEN_BATCH_PAUSE - set to \'on\' to wait for a keystroke before ending
REM MAVEN_OPTS - parameters passed to the Java VM when running Maven
REM     e.g. to debug Maven itself, use
REM set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000
REM MAVEN_SKIP_RC - flag to disable loading of mavenrc files
REM ----------------------------------------------------------------------------');

// Begin all console.log lines with '@' in case MAVEN_BATCH_ECHO is 'on'
console.log('@echo off');
console.log('@REM set title of command window');
console.log('title %0');
console.log('@REM enable echoing by setting MAVEN_BATCH_ECHO to \'on\'');
console.log('if "%MAVEN_BATCH_ECHO%" == "on"  echo %MAVEN_BATCH_ECHO%');
console.log('@REM set %HOME% to equivalent of $HOME');
console.log('if "%HOME%" == "" (set "HOME=%HOMEDRIVE%%HOMEPATH%")');
console.log('@REM Execute a user defined script before this one');
console.log('if not "%MAVEN_SKIP_RC%" == "" goto skipRcPre');
console.log('if exist "%USERPROFILE%\\mavenrc_pre.bat" call "%USERPROFILE%\\mavenrc_pre.bat" %*');
console.log('if exist "%USERPROFILE%\\mavenrc_pre.cmd" call "%USERPROFILE%\\mavenrc_pre.cmd" %*');
console.log(':skipRcPre');
console.log('setlocal');
console.log('set ERROR_CODE=0');
console.log('@REM To isolate internal variables from possible post scripts, we use another setlocal');
console.log('setlocal');
console.log('@REM ==== START VALIDATION ====');
console.log('if not "%JAVA_HOME%" == "" goto OkJHome');
console.log('echo.');
console.log('echo Error: JAVA_HOME not found in your environment. >&2');
console.log('echo Please set the JAVA_HOME variable in your environment to match the >&2');
console.log('echo location of your Java installation. >&2');
console.log('echo.');
console.log('goto error');
console.log(':OkJHome');
console.log('if exist "%JAVA_HOME%\\bin\\java.exe" goto init');
console.log('echo.');
console.log('echo Error: JAVA_HOME is set to an invalid directory. >&2');
console.log('echo JAVA_HOME = "%JAVA_HOME%" >&2');
console.log('echo Please set the JAVA_HOME variable in your environment to match the >&2');
console.log('echo location of your Java installation. >&2');
console.log('echo.');
console.log('goto error');
console.log('@REM ==== END VALIDATION ====');
console.log(':init');
console.log('@REM Find the project base dir, i.e. the directory that contains the folder ".mvn".');
console.log('@REM Fallback to current working directory if not found.');
console.log('set MAVEN_PROJECTBASEDIR=%MAVEN_BASEDIR%');
console.log('IF NOT "%MAVEN_PROJECTBASEDIR%"=="" goto endDetectBaseDir');
console.log('set EXEC_DIR=%CD%');
console.log('set WDIR=%EXEC_DIR%');
console.log(':findBaseDir');
console.log('IF EXIST "%WDIR%\\.mvn" goto baseDirFound');
console.log('cd ..');
console.log('IF "%WDIR%"=="%CD%" goto baseDirNotFound');
console.log('set WDIR=%CD%');
console.log('goto findBaseDir');
console.log(':baseDirFound');
console.log('set MAVEN_PROJECTBASEDIR=%WDIR%');
console.log('cd "%EXEC_DIR%"');
console.log('goto endDetectBaseDir');
console.log(':baseDirNotFound');
console.log('set MAVEN_PROJECTBASEDIR=%EXEC_DIR%');
console.log('cd "%EXEC_DIR%"');
console.log(':endDetectBaseDir');
console.log('IF NOT EXIST "%MAVEN_PROJECTBASEDIR%\\.mvn\\jvm.config" goto endReadAdditionalConfig');
console.log('@setlocal EnableExtensions EnableDelayedExpansion');
console.log('for /F "usebackq delims=" %%a in ("%MAVEN_PROJECTBASEDIR%\\.mvn\\jvm.config") do set JVM_CONFIG_MAVEN_PROPS=!JVM_CONFIG_MAVEN_PROPS! %%a');
console.log('@endlocal & set JVM_CONFIG_MAVEN_PROPS=%JVM_CONFIG_MAVEN_PROPS%');
console.log(':endReadAdditionalConfig');
console.log('SET MAVEN_JAVA_EXE="%JAVA_HOME%\\bin\\java.exe"');
console.log('set WRAPPER_JAR="%MAVEN_PROJECTBASEDIR%\\.mvn\\wrapper\\maven-wrapper.jar"');
console.log('set WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain');
console.log('set WRAPPER_URL="https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"');
console.log('FOR /F "usebackq tokens=1,2 delims==" %%A IN ("%MAVEN_PROJECTBASEDIR%\\.mvn\\wrapper\\maven-wrapper.properties") DO (');
console.log('    IF "%%A"=="wrapperUrl" SET WRAPPER_URL=%%B');
console.log(')');
console.log('@REM Extension to allow automatically downloading the maven-wrapper.jar from Maven-central');
console.log('@REM This allows using the maven wrapper in projects that prohibit checking in binary data.');
console.log('if exist %WRAPPER_JAR