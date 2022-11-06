package com.cadplan.vertex_symbols.jump.plugins;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.cadplan.vertex_symbols.icon.IconLoader;
import com.cadplan.vertex_symbols.jump.utils.VertexStyler;
import com.cadplan.vertex_symbols.jump.VertexSymbolsExtension;
import com.cadplan.vertex_symbols.jump.utils.LoadSymbolFiles;
import com.cadplan.vertex_symbols.jump.utils.VertexParams;
import com.vividsolutions.jump.workbench.model.Layer;
import com.vividsolutions.jump.workbench.plugin.AbstractPlugIn;
import com.vividsolutions.jump.workbench.plugin.EnableCheckFactory;
import com.vividsolutions.jump.workbench.plugin.MultiEnableCheck;
import com.vividsolutions.jump.workbench.plugin.PlugInContext;
import com.vividsolutions.jump.workbench.ui.GUIUtil;
import com.vividsolutions.jump.workbench.ui.MenuNames;

public class VertexSymbolsPlugIn extends AbstractPlugIn {

	public static ImageIcon ICON = IconLoader.icon("vsicon.gif");

	@Override
	public void initialize(PlugInContext context) throws Exception {
		context.getFeatureInstaller().addMainMenuPlugin(this, 
				new String[]{MenuNames.PLUGINS, MenuNames.STYLE}, 
				this.getName(), 
				false, 
				ICON, 
				createEnableCheck(context.getWorkbenchContext()));
		//load symbol files (wkt and images) from OJ/lib/ext/VertexImages folsed

		LoadSymbolFiles loadSymbols = new LoadSymbolFiles(context);
		loadSymbols.start();
		VertexParams.context = context.getWorkbenchContext();
	}

	public static MultiEnableCheck createEnableCheck(
	            WorkbenchContext workbenchContext) {
		 EnableCheckFactory checkFactory = EnableCheckFactory.getInstance(workbenchContext);
		 MultiEnableCheck multiEnableCheck = new MultiEnableCheck();
		 multiEnableCheck.add(checkFactory.createWindowWithLayerViewPanelMustBeActiveCheck())
		 .add(checkFactory.createAtLeastNLayerablesOfTypeMustExistCheck(1, Layer.class))
		 .add(checkFactory.createAtLeastNLayersMustBeSelectedCheck(1));
		 return multiEnableCheck;
	 }
	
	
	@Override
	public String getName() {
		return VertexSymbolsExtension.i18n("VertexSymbols.MenuItem");
	}

	@Override
	public boolean execute(PlugInContext context) {
		//Layer layer ;
		if (context.getSelectedLayers().length ==0) {
			JOptionPane.showMessageDialog(null,
					VertexSymbolsExtension.i18n("VertexNote.Dialog.Message2"), "Warning...", 2);
			return false;
		}
		final Layer layer = context.getSelectedLayer(0);

		//Read previous style from layer
		VertexStyler symbols = new VertexStyler();
		symbols.setLayer(layer);
		symbols.readStyle(); 

		//Activate dialog
		VertexSymbolsDialog vertexDialog = new VertexSymbolsDialog();
		//[Giuseppe Aruta 2020-05-28]  vertexDialog.updateSideBarIconAndDescription();
		if (vertexDialog.cancelled) {
			return false;
		} else {
			GUIUtil.centreOnWindow(vertexDialog);
			symbols.changeStyle();
			return true;
		}
	}
}
