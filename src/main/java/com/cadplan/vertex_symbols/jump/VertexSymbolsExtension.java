package com.cadplan.vertex_symbols.jump;

import com.cadplan.vertex_symbols.jump.plugins.StylerMenuPlugIn;
import com.vividsolutions.jump.I18N;
import com.vividsolutions.jump.workbench.plugin.Extension;
import com.vividsolutions.jump.workbench.plugin.PlugInContext;


public class VertexSymbolsExtension extends Extension {

	private static final I18N I18N = com.vividsolutions.jump.I18N.getInstance("com.cadplan.vertex_symbols");

	@Override
	public void configure(PlugInContext context) throws Exception {
		//	new VertexSymbolsPlugIn().initialize(context);
		//	new VertexNotePlugin().initialize(context);
		new StylerMenuPlugIn().initialize(context);
	}

	@Override
	public String getVersion() {
		return i18n("VertexSymbols.Version");
	}
	
	@Override
	public String getName() {
		return "Vertex-Symbols - ©2005 Geoffrey G Roy, 2020 Giuseppe Aruta, 2021 OJ2 Team";
	}

	/**
	 * one method to translate them all :)
	 */
	public static String i18n( String key, Object... o) {
		return I18N.get(key, o);
	}
}
