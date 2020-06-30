package com.emptyxkl;

import com.emptyxkl.featurescoretype.MySciptEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.plugins.ScriptPlugin;
import org.elasticsearch.script.ScriptContext;
import org.elasticsearch.script.ScriptEngine;

import java.util.Arrays;
import java.util.Collection;

public class FeatureScorePlugin extends Plugin implements ScriptPlugin {

    private final Logger logger = LogManager.getLogger(FeatureScorePlugin.class);

    @Override
    public ScriptEngine getScriptEngine(Settings settings, Collection<ScriptContext<?>> contexts) {
        logger.info("contexts: {}", Arrays.toString(contexts.toArray()));
        return new MySciptEngine();
    }
}
