
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
public class ExtendedWord_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ExtendedWord_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ExtendedWord_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ExtendedWord(addr, ExtendedWord_Type.this);
  			   ExtendedWord_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ExtendedWord(addr, ExtendedWord_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = ExtendedWord.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ru.misis.asu.nlp.ExtendedWord");
 
  /** @generated */
  final Feature casFeat_word;
  /** @generated */
  final int     casFeatCode_word;
  /** @generated */ 
  public int getWord(int addr) {
        if (featOkTst && casFeat_word == null)
      jcas.throwFeatMissing("word", "ru.misis.asu.nlp.ExtendedWord");
    return ll_cas.ll_getRefValue(addr, casFeatCode_word);
  }
  /** @generated */    
  public void setWord(int addr, int v) {
        if (featOkTst && casFeat_word == null)
      jcas.throwFeatMissing("word", "ru.misis.asu.nlp.ExtendedWord");
    ll_cas.ll_setRefValue(addr, casFeatCode_word, v);}
    
  
 
  /** @generated */
  final Feature casFeat_preposition;
  /** @generated */
  final int     casFeatCode_preposition;
  /** @generated */ 
  public int getPreposition(int addr) {
        if (featOkTst && casFeat_preposition == null)
      jcas.throwFeatMissing("preposition", "ru.misis.asu.nlp.ExtendedWord");
    return ll_cas.ll_getRefValue(addr, casFeatCode_preposition);
  }
  /** @generated */    
  public void setPreposition(int addr, int v) {
        if (featOkTst && casFeat_preposition == null)
      jcas.throwFeatMissing("preposition", "ru.misis.asu.nlp.ExtendedWord");
    ll_cas.ll_setRefValue(addr, casFeatCode_preposition, v);}
    
  
 
  /** @generated */
  final Feature casFeat_negativeParticle;
  /** @generated */
  final int     casFeatCode_negativeParticle;
  /** @generated */ 
  public int getNegativeParticle(int addr) {
        if (featOkTst && casFeat_negativeParticle == null)
      jcas.throwFeatMissing("negativeParticle", "ru.misis.asu.nlp.ExtendedWord");
    return ll_cas.ll_getRefValue(addr, casFeatCode_negativeParticle);
  }
  /** @generated */    
  public void setNegativeParticle(int addr, int v) {
        if (featOkTst && casFeat_negativeParticle == null)
      jcas.throwFeatMissing("negativeParticle", "ru.misis.asu.nlp.ExtendedWord");
    ll_cas.ll_setRefValue(addr, casFeatCode_negativeParticle, v);}
    
  
 
  /** @generated */
  final Feature casFeat_inclinationParticle;
  /** @generated */
  final int     casFeatCode_inclinationParticle;
  /** @generated */ 
  public int getInclinationParticle(int addr) {
        if (featOkTst && casFeat_inclinationParticle == null)
      jcas.throwFeatMissing("inclinationParticle", "ru.misis.asu.nlp.ExtendedWord");
    return ll_cas.ll_getRefValue(addr, casFeatCode_inclinationParticle);
  }
  /** @generated */    
  public void setInclinationParticle(int addr, int v) {
        if (featOkTst && casFeat_inclinationParticle == null)
      jcas.throwFeatMissing("inclinationParticle", "ru.misis.asu.nlp.ExtendedWord");
    ll_cas.ll_setRefValue(addr, casFeatCode_inclinationParticle, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public ExtendedWord_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_word = jcas.getRequiredFeatureDE(casType, "word", "ru.misis.asu.nlp.morphoanalysis.types.Word", featOkTst);
    casFeatCode_word  = (null == casFeat_word) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_word).getCode();

 
    casFeat_preposition = jcas.getRequiredFeatureDE(casType, "preposition", "ru.misis.asu.nlp.Preposition", featOkTst);
    casFeatCode_preposition  = (null == casFeat_preposition) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_preposition).getCode();

 
    casFeat_negativeParticle = jcas.getRequiredFeatureDE(casType, "negativeParticle", "ru.misis.asu.nlp.NegativeParticle", featOkTst);
    casFeatCode_negativeParticle  = (null == casFeat_negativeParticle) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_negativeParticle).getCode();

 
    casFeat_inclinationParticle = jcas.getRequiredFeatureDE(casType, "inclinationParticle", "ru.misis.asu.nlp.InclinationParticle", featOkTst);
    casFeatCode_inclinationParticle  = (null == casFeat_inclinationParticle) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_inclinationParticle).getCode();

  }
}



    