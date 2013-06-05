

/* First created by JCasGen Sat Jun 01 21:55:31 MSK 2013 */
package ru.misis.asu.nlp.npr.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import ru.misis.asu.nlp.morphoanalysis.types.Word;


/** Represents Noun Phrase
 * Updated by JCasGen Sat Jun 01 21:55:31 MSK 2013
 * XML source: C:/Users/Valter/new_uima/UIMA-Ext/npr/src/main/resources/uima.xml/npr-ts.xml
 * @generated */
public class NounPhrase extends Phrase {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NounPhrase.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected NounPhrase() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public NounPhrase(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public NounPhrase(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public NounPhrase(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: preposition

  /** getter for preposition - gets 
   * @generated */
  public Word getPreposition() {
    if (NounPhrase_Type.featOkTst && ((NounPhrase_Type)jcasType).casFeat_preposition == null)
      jcasType.jcas.throwFeatMissing("preposition", "ru.misis.asu.nlp.npr.types.NounPhrase");
    return (Word)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((NounPhrase_Type)jcasType).casFeatCode_preposition)));}
    
  /** setter for preposition - sets  
   * @generated */
  public void setPreposition(Word v) {
    if (NounPhrase_Type.featOkTst && ((NounPhrase_Type)jcasType).casFeat_preposition == null)
      jcasType.jcas.throwFeatMissing("preposition", "ru.misis.asu.nlp.npr.types.NounPhrase");
    jcasType.ll_cas.ll_setRefValue(addr, ((NounPhrase_Type)jcasType).casFeatCode_preposition, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: particle

  /** getter for particle - gets 
   * @generated */
  public Word getParticle() {
    if (NounPhrase_Type.featOkTst && ((NounPhrase_Type)jcasType).casFeat_particle == null)
      jcasType.jcas.throwFeatMissing("particle", "ru.misis.asu.nlp.npr.types.NounPhrase");
    return (Word)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((NounPhrase_Type)jcasType).casFeatCode_particle)));}
    
  /** setter for particle - sets  
   * @generated */
  public void setParticle(Word v) {
    if (NounPhrase_Type.featOkTst && ((NounPhrase_Type)jcasType).casFeat_particle == null)
      jcasType.jcas.throwFeatMissing("particle", "ru.misis.asu.nlp.npr.types.NounPhrase");
    jcasType.ll_cas.ll_setRefValue(addr, ((NounPhrase_Type)jcasType).casFeatCode_particle, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    