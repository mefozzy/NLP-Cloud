
/* First created by JCasGen Sat Jun 01 21:28:54 MSK 2013 */
package ru.misis.asu.nlp.segmentation.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Sat Jun 01 21:28:54 MSK 2013
 * @generated */
public class Sentence_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Sentence_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Sentence_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Sentence(addr, Sentence_Type.this);
  			   Sentence_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Sentence(addr, Sentence_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Sentence.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ru.misis.asu.nlp.segmentation.types.Sentence");
 
  /** @generated */
  final Feature casFeat_eos;
  /** @generated */
  final int     casFeatCode_eos;
  /** @generated */ 
  public int getEos(int addr) {
        if (featOkTst && casFeat_eos == null)
      jcas.throwFeatMissing("eos", "ru.misis.asu.nlp.segmentation.types.Sentence");
    return ll_cas.ll_getRefValue(addr, casFeatCode_eos);
  }
  /** @generated */    
  public void setEos(int addr, int v) {
        if (featOkTst && casFeat_eos == null)
      jcas.throwFeatMissing("eos", "ru.misis.asu.nlp.segmentation.types.Sentence");
    ll_cas.ll_setRefValue(addr, casFeatCode_eos, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Sentence_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_eos = jcas.getRequiredFeatureDE(casType, "eos", "ru.misis.asu.nlp.tokenization.types.PM", featOkTst);
    casFeatCode_eos  = (null == casFeat_eos) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_eos).getCode();

  }
}



    