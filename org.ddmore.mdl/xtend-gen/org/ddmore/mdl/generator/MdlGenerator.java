package org.ddmore.mdl.generator;

import com.google.common.collect.Iterables;
import org.ddmore.mdl.generator.Mdl2PharmML;
import org.ddmore.mdl.mdl.mcl;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@SuppressWarnings("all")
public class MdlGenerator extends Mdl2PharmML implements IGenerator {
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<mcl> _filter = Iterables.<mcl>filter(_iterable, mcl.class);
    for (final mcl m : _filter) {
      {
        String _fileName = this.fileName(resource);
        String _plus = (_fileName + ".ctl");
        CharSequence _convertToNonmem = this.convertToNonmem(m);
        fsa.generateFile(_plus, _convertToNonmem);
        String _fileName_1 = this.fileName(resource);
        String _plus_1 = (_fileName_1 + ".xml");
        CharSequence _convertToPharmML = this.convertToPharmML(m);
        fsa.generateFile(_plus_1, _convertToPharmML);
      }
    }
  }
  
  public void doGenerateNonmem(final Resource resource, final IFileSystemAccess fsa) {
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<mcl> _filter = Iterables.<mcl>filter(_iterable, mcl.class);
    for (final mcl m : _filter) {
      String _fileName = this.fileName(resource);
      String _plus = (_fileName + ".ctl");
      CharSequence _convertToNonmem = this.convertToNonmem(m);
      fsa.generateFile(_plus, _convertToNonmem);
    }
  }
  
  public void doGeneratePharmML(final Resource resource, final IFileSystemAccess fsa) {
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<mcl> _filter = Iterables.<mcl>filter(_iterable, mcl.class);
    for (final mcl m : _filter) {
      String _fileName = this.fileName(resource);
      String _plus = (_fileName + ".xml");
      CharSequence _convertToPharmML = this.convertToPharmML(m);
      fsa.generateFile(_plus, _convertToPharmML);
    }
  }
}
