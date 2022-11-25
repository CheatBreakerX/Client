package com.cheatbreaker.bridge.client.resources.data;

import java.io.InputStream;

public interface IMetadataSectionBridge {
    InputStream bridge$getInputStream();
    boolean bridge$hasMetadata();
    IMetadataSectionBridge bridge$getMetadata(String p_110526_1_);
}
