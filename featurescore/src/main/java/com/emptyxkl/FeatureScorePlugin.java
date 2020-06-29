package com.emptyxkl;

import org.elasticsearch.plugins.ActionPlugin;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.plugins.ScriptPlugin;
import org.elasticsearch.script.ScriptContext;

import java.util.Collections;
import java.util.List;

public class FeatureScorePlugin extends Plugin implements ActionPlugin, ScriptPlugin {

    public FeatureScorePlugin(){
    }

    @Override()
    public List<ScriptContext<?>> getContexts(){
        return Collections.singletonList(new FeatureScorePluginHandle());
    }
}
