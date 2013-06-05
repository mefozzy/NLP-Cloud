
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
public class PMSegment_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (PMSegment_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = PMSegment_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new PMSegment(addr, PMSegment_Type.this);
  			   PMSegment_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new PMSegment(addr, PMSegment_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = PMSegment.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ru.misis.asu.nlp.segmentation.types.PMSegment");
 
  /** @generated */
  final Feature casFeat_sentence;
  /** @generated */
  final int     casFeatCode_sentence;
  /** @generated */ 
  public int getSentence(int addr) {
        if (featOkTst && casFeat_sentence == null)
      jcas.throwFeatMissing("sentence", "ru.misis.asu.nlp.segmentation.types.PMSegment");
    return ll_cas.ll_getRefValue(addr, casFeatCode_sentence);
  }
  /** @generated */    
  public void setSentence(int addr, int v) {
        if (featOkTst && casFeat_sentence == null)
      jcas.throwFeatMissing("sentence", "ru.misis.asu.nlp.segmentation.types.PMSegment");
    ll_cas.ll_setRefValue(addr, casFeatCode_sentence, v);}
    
  
 
  /** @generated */
  final Feature casFeat_eopm;
  /** @generated */
  final int     casFeatCode_eopm;
  /** @generated */ 
  public int getEopm(int addr) {
        if (featOkTst && casFeat_eopm == null)
      jcas.throwFeatMissing("eopm", "ru.misis.asu.nlp.segmentation.types.PMSegment");
    return ll_cas.ll_getRefValue(addr, casFeatCode_eopm);
  }
  /** @generated */    
  public void setEopm(int addr, int v) {
        if (featOkTst && casFeat_eopm == null)
      jcas.throwFeatMissing("eopm", "ru.misis.asu.nlp.segmentation.types.PMSegment");
    ll_cas.ll_setRefValue(addr, casFeatCode_eopm, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public PMSegment_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_sentence = jcas.getRequiredFeatureDE(casType, "sentence", "ru.misis.asu.nlp.segmentation.types.Sentence", featOkTst);
    casFeatCode_sentence  = (null == casFeat_sentence) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentence).getCode();

 
    casFeat_eopm = jcas.getRequiredFeatureDE(casType, "eopm", "ru.misis.asu.nlp.tokenization.types.PM", featOkTst);
    casFeatCode_eopm  = (null == casFeat_eopm) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_eopm).getCode();

  }
}



    