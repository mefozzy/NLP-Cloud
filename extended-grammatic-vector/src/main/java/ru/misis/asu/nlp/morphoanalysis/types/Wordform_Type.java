
/* First created by JCasGen Sun Jun 02 19:43:30 MSK 2013 */
package ru.misis.asu.nlp.morphoanalysis.types;

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
public class Wordform_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Wordform_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Wordform_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Wordform(addr, Wordform_Type.this);
  			   Wordform_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Wordform(addr, Wordform_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Wordform.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ru.misis.asu.nlp.morphoanalysis.types.Wordform");
 
  /** @generated */
  final Feature casFeat_pos;
  /** @generated */
  final int     casFeatCode_pos;
  /** @generated */ 
  public String getPos(int addr) {
        if (featOkTst && casFeat_pos == null)
      jcas.throwFeatMissing("pos", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    return ll_cas.ll_getStringValue(addr, casFeatCode_pos);
  }
  /** @generated */    
  public void setPos(int addr, String v) {
        if (featOkTst && casFeat_pos == null)
      jcas.throwFeatMissing("pos", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    ll_cas.ll_setStringValue(addr, casFeatCode_pos, v);}
    
  
 
  /** @generated */
  final Feature casFeat_lemma;
  /** @generated */
  final int     casFeatCode_lemma;
  /** @generated */ 
  public String getLemma(int addr) {
        if (featOkTst && casFeat_lemma == null)
      jcas.throwFeatMissing("lemma", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    return ll_cas.ll_getStringValue(addr, casFeatCode_lemma);
  }
  /** @generated */    
  public void setLemma(int addr, String v) {
        if (featOkTst && casFeat_lemma == null)
      jcas.throwFeatMissing("lemma", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    ll_cas.ll_setStringValue(addr, casFeatCode_lemma, v);}
    
  
 
  /** @generated */
  final Feature casFeat_lemmaId;
  /** @generated */
  final int     casFeatCode_lemmaId;
  /** @generated */ 
  public int getLemmaId(int addr) {
        if (featOkTst && casFeat_lemmaId == null)
      jcas.throwFeatMissing("lemmaId", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    return ll_cas.ll_getIntValue(addr, casFeatCode_lemmaId);
  }
  /** @generated */    
  public void setLemmaId(int addr, int v) {
        if (featOkTst && casFeat_lemmaId == null)
      jcas.throwFeatMissing("lemmaId", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    ll_cas.ll_setIntValue(addr, casFeatCode_lemmaId, v);}
    
  
 
  /** @generated */
  final Feature casFeat_grammems;
  /** @generated */
  final int     casFeatCode_grammems;
  /** @generated */ 
  public int getGrammems(int addr) {
        if (featOkTst && casFeat_grammems == null)
      jcas.throwFeatMissing("grammems", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    return ll_cas.ll_getRefValue(addr, casFeatCode_grammems);
  }
  /** @generated */    
  public void setGrammems(int addr, int v) {
        if (featOkTst && casFeat_grammems == null)
      jcas.throwFeatMissing("grammems", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    ll_cas.ll_setRefValue(addr, casFeatCode_grammems, v);}
    
   /** @generated */
  public String getGrammems(int addr, int i) {
        if (featOkTst && casFeat_grammems == null)
      jcas.throwFeatMissing("grammems", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_grammems), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_grammems), i);
  return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_grammems), i);
  }
   
  /** @generated */ 
  public void setGrammems(int addr, int i, String v) {
        if (featOkTst && casFeat_grammems == null)
      jcas.throwFeatMissing("grammems", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_grammems), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_grammems), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_grammems), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_grammemBits;
  /** @generated */
  final int     casFeatCode_grammemBits;
  /** @generated */ 
  public int getGrammemBits(int addr) {
        if (featOkTst && casFeat_grammemBits == null)
      jcas.throwFeatMissing("grammemBits", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    return ll_cas.ll_getRefValue(addr, casFeatCode_grammemBits);
  }
  /** @generated */    
  public void setGrammemBits(int addr, int v) {
        if (featOkTst && casFeat_grammemBits == null)
      jcas.throwFeatMissing("grammemBits", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    ll_cas.ll_setRefValue(addr, casFeatCode_grammemBits, v);}
    
   /** @generated */
  public long getGrammemBits(int addr, int i) {
        if (featOkTst && casFeat_grammemBits == null)
      jcas.throwFeatMissing("grammemBits", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getLongArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_grammemBits), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_grammemBits), i);
  return ll_cas.ll_getLongArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_grammemBits), i);
  }
   
  /** @generated */ 
  public void setGrammemBits(int addr, int i, long v) {
        if (featOkTst && casFeat_grammemBits == null)
      jcas.throwFeatMissing("grammemBits", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    if (lowLevelTypeChecks)
      ll_cas.ll_setLongArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_grammemBits), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_grammemBits), i);
    ll_cas.ll_setLongArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_grammemBits), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Wordform_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_pos = jcas.getRequiredFeatureDE(casType, "pos", "uima.cas.String", featOkTst);
    casFeatCode_pos  = (null == casFeat_pos) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_pos).getCode();

 
    casFeat_lemma = jcas.getRequiredFeatureDE(casType, "lemma", "uima.cas.String", featOkTst);
    casFeatCode_lemma  = (null == casFeat_lemma) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_lemma).getCode();

 
    casFeat_lemmaId = jcas.getRequiredFeatureDE(casType, "lemmaId", "uima.cas.Integer", featOkTst);
    casFeatCode_lemmaId  = (null == casFeat_lemmaId) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_lemmaId).getCode();

 
    casFeat_grammems = jcas.getRequiredFeatureDE(casType, "grammems", "uima.cas.StringArray", featOkTst);
    casFeatCode_grammems  = (null == casFeat_grammems) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_grammems).getCode();

 
    casFeat_grammemBits = jcas.getRequiredFeatureDE(casType, "grammemBits", "uima.cas.LongArray", featOkTst);
    casFeatCode_grammemBits  = (null == casFeat_grammemBits) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_grammemBits).getCode();

  }
}



    