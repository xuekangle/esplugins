package com.emptyxkl;

import org.elasticsearch.plugins.ActionPlugin;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.plugins.ScriptPlugin;

import java.util.Collections;
import java.util.List;

public class FeatureScorePlugin extends Plugin implements ActionPlugin, ScriptPlugin {

    public FeatureScorePlugin(){
        super();
    }

    @Override()
    public List getContexts(){
        return Collections.singletonList(new FeatureScorePluginHandle());
    }
}
