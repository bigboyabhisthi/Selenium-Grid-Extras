/**
 * Copyright (c) 2013, Groupon, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * Neither the name of GROUPON nor the names of its contributors may be
 * used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 * Created with IntelliJ IDEA.
 * User: Dima Kovalenko (@dimacus) && Darko Marinov
 * Date: 5/10/13
 * Time: 4:06 PM
 */

package com.groupon.seleniumgridextras;

import com.google.gson.JsonParser;
import com.groupon.seleniumgridextras.tasks.DownloadWebdriver;
import com.groupon.seleniumgridextras.tasks.ExecuteOSTask;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class DownloadWebdriverTest {

  public ExecuteOSTask task;

  @Before
  public void setUp() throws Exception {
    RuntimeConfig.setConfig("download_test.json");
    WriteDefaultConfigs.writeConfig(RuntimeConfig.getConfigFile(), false);
    RuntimeConfig.loadConfig();
    task = new DownloadWebdriver();
  }

  @After
  public void tearDown() throws Exception {
    File config = new File(RuntimeConfig.getConfigFile());
    config.delete();
  }


  @Test
  public void testGetEndpoint() throws Exception {
    assertEquals("/download_webdriver", task.getEndpoint());
  }

  @Test
  public void testGetDescription() throws Exception {
    assertEquals("Downloads a version of WebDriver jar to local machine", task.getDescription());
  }


  @Test
  public void testGetJsonResponse() throws Exception {
    assertEquals(
        new JsonParser().parse("{\"exit_code\":0,\"error\":[],\"file\":[\"\"],\"source_url\":[\"\"],\"root_dir\":[\"webdriver\"],\"out\":[]}"),
        task.getJsonResponse().getJson());
  }

  @Test
  public void testGetAcceptedParams() throws Exception {
    assertEquals("Version of WebDriver to download, such as 2.33.0",
        task.getAcceptedParams().get("version"));

    assertEquals(1, task.getAcceptedParams().keySet().size());
  }
}
