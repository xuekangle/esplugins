package com.emptyxkl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.plugins.ActionPlugin;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.plugins.ScriptPlugin;
import org.elasticsearch.script.NativeScriptFactory;

import java.util.Collections;
import java.util.List;

public class FeatureScorePlugin extends Plugin implements ActionPlugin, ScriptPlugin {

    public FeatureScorePlugin(){
        super();
    }

    public List<NativeScriptFactory> getNativeScripts(){
        return Collections.<NativeScriptFactory>singletonList(new FeatureScorePluginHandle());
    }
}
