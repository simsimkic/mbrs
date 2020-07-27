package myplugin;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import myplugin.analyzer.AnalyzeException;
import myplugin.analyzer.ModelAnalyzer;
import myplugin.generator.AngularAddEntityGenerator;
import myplugin.generator.AngularEditEntityGenerator;
import myplugin.generator.ControllerGenerator;
import myplugin.generator.EJBGenerator;
import myplugin.generator.EditTypescriptGenerator;
import myplugin.generator.EnumGenerator;
import myplugin.generator.ModelLayerGenerator;
import myplugin.generator.ModuleGenerator;
import myplugin.generator.RepositoryGenerator;
import myplugin.generator.RoutingGenerator;
import myplugin.generator.ServiceFrontGenerator;
import myplugin.generator.ServiceGenerator;
import myplugin.generator.ServiceImplGenerator;
import myplugin.generator.TypescriptGenerator;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;

/** Action that activate code generation */
@SuppressWarnings("serial")
class GenerateAction extends MDAction {

	public GenerateAction(String name) {
		super("", name, null, null);
	}

	public void actionPerformed(ActionEvent evt) {
		if (Application.getInstance().getProject() == null)
			return;
		Package root = Application.getInstance().getProject().getModel();
		if (root == null)
			return;

		//
		// //paket aplikacije koju generisemo
		// String rootDirectory = "ftn.uns.mbrs";
		// ModelAnalyzer analyzer = new ModelAnalyzer(root, rootDirectory);

		ModelAnalyzer analyzer = null;
		GeneratorOptions generatorOptions = null;

		try {
			
			generateModel(analyzer, root, generatorOptions);
			generateEnumeration(analyzer, root, generatorOptions);
			generateController(analyzer, root, generatorOptions);
			generateService(analyzer, root, generatorOptions);
			generateServiceImpl(analyzer, root, generatorOptions);
			generateRepository(analyzer, root, generatorOptions);
			generateAdd(analyzer, root, generatorOptions);
			generateModule(analyzer, root, generatorOptions);
			generateRouting(analyzer, root, generatorOptions);
			generateServiceFront(analyzer, root, generatorOptions);
			generateAddTs(analyzer, root, generatorOptions);
			generateEdit(analyzer, root, generatorOptions);
			generateEditTs(analyzer, root, generatorOptions);

		} catch (AnalyzeException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void generateEnumeration(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions) throws AnalyzeException {
		// TODO Auto-generated method stub
		analyzer = new ModelAnalyzer(root, "model");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("EnumerationGenerator");
		EnumGenerator enumGenerator = new EnumGenerator(generatorOptions);
		enumGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		exportToXml();
		
	}

	private void generateModel(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "model");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ModelGenerator");
		ModelLayerGenerator ejbGenerator = new ModelLayerGenerator(generatorOptions);
		ejbGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}

	private void generateController(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "controller");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ControllerGenerator");
		ControllerGenerator controllerGenerator = new ControllerGenerator(generatorOptions);
		controllerGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}

	private void generateService(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "service");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ServiceGenerator");
		ServiceGenerator serviceGenerator = new ServiceGenerator(generatorOptions);
		serviceGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}

	private void generateServiceImpl(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "serviceimpl");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ServiceImplGenerator");
		ServiceImplGenerator serviceImplGenerator = new ServiceImplGenerator(generatorOptions);
		serviceImplGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}

	private void generateRepository(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "repository");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("RepositoryGenerator");
		RepositoryGenerator repositoryGenerator = new RepositoryGenerator(generatorOptions);
		repositoryGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}

	// ************************ ANGULAR GENERATOR **************************


	
	private void generateModule(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
			throws AnalyzeException, IOException {
		analyzer = new ModelAnalyzer(root, "");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ModuleGenerator");
		ModuleGenerator moduleGenerator = new ModuleGenerator(generatorOptions);
		moduleGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}
	
	private void generateRouting(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
			throws AnalyzeException, IOException {
		analyzer = new ModelAnalyzer(root, "");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("RoutingGenerator");
		RoutingGenerator routingGenerator = new RoutingGenerator(generatorOptions);
		routingGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}
	
	private void generateServiceFront(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
			throws AnalyzeException, IOException {
		analyzer = new ModelAnalyzer(root, "");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ServiceFrontGenerator");
		ServiceFrontGenerator serviceFrontGenerator = new ServiceFrontGenerator(generatorOptions);
		serviceFrontGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}
	
	private void generateAdd(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("AngularAddEntityGenerator");
		AngularAddEntityGenerator angularAddEditEntityGenerator = new AngularAddEntityGenerator(generatorOptions);
		angularAddEditEntityGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}
	
	private void generateAddTs(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("TypescriptGenerator");
		TypescriptGenerator typescriptGenerator = new TypescriptGenerator(generatorOptions);
		typescriptGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}
	
	private void generateEdit(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("AngularEditEntityGenerator");
		AngularEditEntityGenerator angularEditEntityGenerator = new AngularEditEntityGenerator(generatorOptions);
		angularEditEntityGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}
	
	private void generateEditTs(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("EditTypescriptGenerator");
		EditTypescriptGenerator editTypescriptGenerator = new EditTypescriptGenerator(generatorOptions);
		editTypescriptGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}

	private void exportToXml() {
		if (JOptionPane.showConfirmDialog(null, "Do you want to save FM Model?") == JOptionPane.OK_OPTION) {
			JFileChooser jfc = new JFileChooser();
			if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				String fileName = jfc.getSelectedFile().getAbsolutePath();

				XStream xstream = new XStream(new DomDriver());
				BufferedWriter out;
				try {
					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF8"));
					xstream.toXML(FMModel.getInstance().getClasses(), out);
					xstream.toXML(FMModel.getInstance().getEnumerations(), out);

				} catch (UnsupportedEncodingException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
	}

}