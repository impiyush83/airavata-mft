/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.airavata.mft.agent.transport;

import org.apache.airavata.mft.core.api.MetadataCollector;

import java.util.Optional;

public final class MetadataCollectorResolver {

    public static Optional<MetadataCollector> resolveMetadataCollector(String type, TransportClassLoaderCache cache) throws Exception {
        String className = null;
        switch (type) {
            case "SCP":
                className = "org.apache.airavata.mft.transport.scp.SCPMetadataCollector";
                break;
            case "LOCAL":
                className = "org.apache.airavata.mft.transport.local.LocalMetadataCollector";
                break;
            case "S3":
                className = "org.apache.airavata.mft.transport.s3.S3MetadataCollector";
                break;
            case "BOX":
                className = "org.apache.airavata.mft.transport.box.BoxMetadataCollector";
                break;
            case "AZURE":
                className = "org.apache.airavata.mft.transport.azure.AzureMetadataCollector";
                break;
            case "GCS":
                className = "org.apache.airavata.mft.transport.gcp.GCSMetadataCollector";
                break;
            case "DROPBOX":
                className = "org.apache.airavata.mft.transport.dropbox.DropboxMetadataCollector";
                break;
            case "FTP":
                className = "org.apache.airavata.mft.transport.ftp.FTPMetadataCollector";
                break;
            case "SWIFT":
                className = "org.apache.airavata.mft.transport.swift.SwiftMetadataCollector";
                break;
            case "ODATA":
                className = "org.apache.airavata.mft.transport.odata.ODataMetadataCollector";
                break;
        }

        if (className != null) {
            TransportClassLoader transportClassLoader = cache.fetchClassLoader(type.toLowerCase());
            Class<?> aClass = transportClassLoader.loadClass(className, true);
            return Optional.of((MetadataCollector) aClass.getDeclaredConstructor().newInstance());
        } else {
            return Optional.empty();
        }
    }
}
