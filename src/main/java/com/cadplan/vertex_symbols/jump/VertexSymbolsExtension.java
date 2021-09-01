package com.cadplan.vertex_symbols.jump;

import java.io.File;
import java.net.URL;

import com.cadplan.vertex_symbols.jump.plugins.StylerMenuPlugIn;
import com.vividsolutions.jump.I18N;
import com.vividsolutions.jump.util.FileUtil;
import com.vividsolutions.jump.workbench.Logger;
import com.vividsolutions.jump.workbench.WorkbenchContext;
import com.vividsolutions.jump.workbench.plugin.Extension;
import com.vividsolutions.jump.workbench.plugin.PlugInContext;
import com.vividsolutions.jump.workbench.plugin.PlugInManager;


public class VertexSymbolsExtension extends Extension {

	private static final I18N I18N = com.vividsolutions.jump.I18N.getInstance("com.cadplan.vertex_symbols");
	private File symbolFolder = null;
	
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

	/**
	 * provide the folder where we search/store/expect symbol files
	 * 
	 * @param workbenchcontext
	 * @return folder or null (if not found)
	 */
	public static File getVertexImagesFolder(WorkbenchContext context) {
		File folder = context.getWorkbench().getPlugInManager()
				.findFileOrFolderInExtensionDirs("VertexImages");
		if (folder.isDirectory()) {
			return folder;
		} else {
			Logger.error("Cannot find folder 'VertexImages'!");
		}
		return null;
	}
}
