package com.cheatbreaker.bridge.client.resources;

import com.cheatbreaker.bridge.client.resources.data.IMetadataSectionBridge;

import java.io.InputStream;

public interface IResourceBridge {
    InputStream bridge$getInputStream();
    boolean bridge$hasMetadata();
    IMetadataSectionBridge bridge$getMetadata(String p_110526_1_);
}
