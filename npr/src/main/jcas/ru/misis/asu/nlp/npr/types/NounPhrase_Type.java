
/* First created by JCasGen Sat Jun 01 21:55:31 MSK 2013 */
package ru.misis.asu.nlp.npr.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** Represents Noun Phrase
 * Updated by JCasGen Sat Jun 01 21:55:31 MSK 2013
 * @generated */
public class NounPhrase_Type extends Phrase_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (NounPhrase_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = NounPhrase_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new NounPhrase(addr, NounPhrase_Type.this);
  			   NounPhrase_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new NounPhrase(addr, NounPhrase_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = NounPhrase.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ru.misis.asu.nlp.npr.types.NounPhrase");
 
  /** @generated */
  final Feature casFeat_preposition;
  /** @generated */
  final int     casFeatCode_preposition;
  /** @generated */ 
  public int getPreposition(int addr) {
        if (featOkTst && casFeat_preposition == null)
      jcas.throwFeatMissing("preposition", "ru.misis.asu.nlp.npr.types.NounPhrase");
    return ll_cas.ll_getRefValue(addr, casFeatCode_preposition);
  }
  /** @generated */    
  public void setPreposition(int addr, int v) {
        if (featOkTst && casFeat_preposition == null)
      jcas.throwFeatMissing("preposition", "ru.misis.asu.nlp.npr.types.NounPhrase");
    ll_cas.ll_setRefValue(addr, casFeatCode_preposition, v);}
    
  
 
  /** @generated */
  final Feature casFeat_particle;
  /** @generated */
  final int     casFeatCode_particle;
  /** @generated */ 
  public int getParticle(int addr) {
        if (featOkTst && casFeat_particle == null)
      jcas.throwFeatMissing("particle", "ru.misis.asu.nlp.npr.types.NounPhrase");
    return ll_cas.ll_getRefValue(addr, casFeatCode_particle);
  }
  /** @generated */    
  public void setParticle(int addr, int v) {
        if (featOkTst && casFeat_particle == null)
      jcas.throwFeatMissing("particle", "ru.misis.asu.nlp.npr.types.NounPhrase");
    ll_cas.ll_setRefValue(addr, casFeatCode_particle, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public NounPhrase_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_preposition = jcas.getRequiredFeatureDE(casType, "preposition", "ru.misis.asu.nlp.morphoanalysis.types.Word", featOkTst);
    casFeatCode_preposition  = (null == casFeat_preposition) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_preposition).getCode();

 
    casFeat_particle = jcas.getRequiredFeatureDE(casType, "particle", "ru.misis.asu.nlp.morphoanalysis.types.Word", featOkTst);
    casFeatCode_particle  = (null == casFeat_particle) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_particle).getCode();

  }
}



    