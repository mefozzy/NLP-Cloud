
/* First created by JCasGen Sun Jun 09 00:35:52 MSK 2013 */
package ru.misis.asu.nlp;

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
 * Updated by JCasGen Sun Jun 09 00:35:52 MSK 2013
 * @generated */
public class Service_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Service_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Service_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Service(addr, Service_Type.this);
  			   Service_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Service(addr, Service_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Service.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ru.misis.asu.nlp.Service");
 
  /** @generated */
  final Feature casFeat_word;
  /** @generated */
  final int     casFeatCode_word;
  /** @generated */ 
  public int getWord(int addr) {
        if (featOkTst && casFeat_word == null)
      jcas.throwFeatMissing("word", "ru.misis.asu.nlp.Service");
    return ll_cas.ll_getRefValue(addr, casFeatCode_word);
  }
  /** @generated */    
  public void setWord(int addr, int v) {
        if (featOkTst && casFeat_word == null)
      jcas.throwFeatMissing("word", "ru.misis.asu.nlp.Service");
    ll_cas.ll_setRefValue(addr, casFeatCode_word, v);}
    
  
 
  /** @generated */
  final Feature casFeat_grammemes;
  /** @generated */
  final int     casFeatCode_grammemes;
  /** @generated */ 
  public int getGrammemes(int addr) {
        if (featOkTst && casFeat_grammemes == null)
      jcas.throwFeatMissing("grammemes", "ru.misis.asu.nlp.Service");
    return ll_cas.ll_getRefValue(addr, casFeatCode_grammemes);
  }
  /** @generated */    
  public void setGrammemes(int addr, int v) {
        if (featOkTst && casFeat_grammemes == null)
      jcas.throwFeatMissing("grammemes", "ru.misis.asu.nlp.Service");
    ll_cas.ll_setRefValue(addr, casFeatCode_grammemes, v);}
    
   /** @generated */
  public String getGrammemes(int addr, int i) {
        if (featOkTst && casFeat_grammemes == null)
      jcas.throwFeatMissing("grammemes", "ru.misis.asu.nlp.Service");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_grammemes), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_grammemes), i);
	return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_grammemes), i);
  }
   
  /** @generated */ 
  public void setGrammemes(int addr, int i, String v) {
        if (featOkTst && casFeat_grammemes == null)
      jcas.throwFeatMissing("grammemes", "ru.misis.asu.nlp.Service");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_grammemes), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_grammemes), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_grammemes), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Service_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_word = jcas.getRequiredFeatureDE(casType, "word", "ru.misis.asu.nlp.morphoanalysis.types.Word", featOkTst);
    casFeatCode_word  = (null == casFeat_word) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_word).getCode();

 
    casFeat_grammemes = jcas.getRequiredFeatureDE(casType, "grammemes", "uima.cas.StringArray", featOkTst);
    casFeatCode_grammemes  = (null == casFeat_grammemes) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_grammemes).getCode();

  }
}



    