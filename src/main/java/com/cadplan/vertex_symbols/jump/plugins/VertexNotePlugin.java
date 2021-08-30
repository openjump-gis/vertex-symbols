package com.cadplan.vertex_symbols.jump.plugins;

import javax.swing.ImageIcon;

import com.cadplan.vertex_symbols.icon.IconLoader;
import com.cadplan.vertex_symbols.jump.VertexSymbolsExtension;
import com.vividsolutions.jump.I18N;
import com.vividsolutions.jump.workbench.plugin.AbstractPlugIn;
import com.vividsolutions.jump.workbench.plugin.EnableCheck;
import com.vividsolutions.jump.workbench.plugin.EnableCheckFactory;
import com.vividsolutions.jump.workbench.plugin.MultiEnableCheck;
import com.vividsolutions.jump.workbench.plugin.PlugInContext;
import com.vividsolutions.jump.workbench.ui.LayerViewPanel;
import com.vividsolutions.jump.workbench.ui.MenuNames;

public class VertexNotePlugin extends AbstractPlugIn {
	public static ImageIcon ICON = IconLoader.icon("noteicon.gif");

	@Override
	public void initialize(PlugInContext paramPlugInContext) throws Exception {
		EnableCheckFactory check = paramPlugInContext.getCheckFactory();
		EnableCheck scheck = check.createAtLeastNFeaturesMustBeSelectedCheck(1);
		MultiEnableCheck mcheck = new MultiEnableCheck();
		mcheck.add(check.createAtLeastNLayersMustExistCheck(1));
		mcheck.add(check.createAtLeastNLayersMustBeEditableCheck(1));
		String str = MenuNames.PLUGINS;
		paramPlugInContext.getFeatureInstaller().addMainMenuPlugin(this, new String[]{str, MenuNames.STYLE}, this.getName(), false, ICON, mcheck);
		// paramPlugInContext.getWorkbenchFrame().getToolBar().addPlugIn(ICON, this, mcheck, paramPlugInContext.getWorkbenchContext());
		paramPlugInContext.getFeatureInstaller().addPopupMenuPlugin(LayerViewPanel.popupMenu(), this, this.getName(), false, ICON, scheck);
	}

	@Override
	public String getName() {
		return VertexSymbolsExtension.i18n("VertexNote.MenuItem");
	}

	@Override
	public boolean execute(PlugInContext context) {
		new VertexNoteDialog(context);
		return true;
	}
}
