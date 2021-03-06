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

package org.knowhowlab.osgi.monitoradmin;

import org.junit.Assert;
import org.junit.Test;
import org.knowhowlab.osgi.monitoradmin.util.StatusVariablePathFilter;

/**
 * @author dmytro.pishchukhin
 */
public class StatusVariablePathFilterTest {
    @Test
    public void testParse_ValidFilter1() {
        StatusVariablePathFilter filter = new StatusVariablePathFilter("aaa.*/aaa.aaa.*");
        Assert.assertEquals("aaa.", filter.getMonitorableId());
        Assert.assertEquals("aaa.aaa.", filter.getStatusVariableId());
    }

    @Test
    public void testParse_ValidFilter2() {
        StatusVariablePathFilter filter = new StatusVariablePathFilter("*/*");
        Assert.assertEquals("", filter.getMonitorableId());
        Assert.assertEquals("", filter.getStatusVariableId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParse_InvalidFilter1() {
        new StatusVariablePathFilter("aaa/aaa/aaa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParse_InvalidFilter2() {
        new StatusVariablePathFilter("/aaa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParse_InvalidFilter3() {
        new StatusVariablePathFilter("aaa*.aaa./aaa");
    }
}
