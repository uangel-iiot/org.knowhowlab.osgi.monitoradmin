/*
 * Copyright (c) 2009-2016 Dmytro Pishchukhin (http://knowhowlab.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.knowhowlab.osgi.monitoradmin.it;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.knowhowlab.osgi.testing.assertions.ServiceAssert.assertServiceAvailable;
import static org.ops4j.pax.exam.CoreOptions.*;

/**
 * @author dmytro.pishchukhin
 */
@RunWith(PaxExam.class)
@ExamReactorStrategy(PerMethod.class)
public class IT_Simple {
    @Configuration
    public Option[] config() {
        return options(
            systemPackages("javax.xml.parsers", "javax.net.ssl", "org.w3c.dom", "org.xml.sax"),

            mavenBundle().groupId("org.knowhowlab.osgi")
                .artifactId("org.knowhowlab.osgi.testing.all")
                .version("1.3.0"),

            mavenBundle().groupId("org.knowhowlab.osgi")
                .artifactId("monitoradmin").
                version(System.getProperty("project.version")).start(),

            junitBundles()
        );
    }

    @Test
    public void testMonitorAdminServiceAvailable() throws Exception {
        assertServiceAvailable("org.osgi.service.monitor.MonitorAdmin", 5, SECONDS);
    }
}
