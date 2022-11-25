package com.cheatbreaker.bridge.client.resources;

import com.cheatbreaker.bridge.util.ResourceLocationBridge;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface IResourceManagerBridge {
    Set bridge$getResourceDomains();
    IResourceBridge bridge$getResource(ResourceLocationBridge p_110536_1_) throws IOException;
    List bridge$getAllResources(ResourceLocationBridge p_135056_1_) throws IOException;
}
