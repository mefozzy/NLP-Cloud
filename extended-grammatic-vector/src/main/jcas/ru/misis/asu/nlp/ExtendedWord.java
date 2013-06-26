

/* First created by JCasGen Sun Jun 09 00:35:52 MSK 2013 */
package ru.misis.asu.nlp;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import ru.misis.asu.nlp.morphoanalysis.types.Word;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sun Jun 09 00:35:52 MSK 2013
 * XML source: D:/Java/workspace2/extended-grammatic-vector/src/main/resources/typeSystemDescriptor.xml
 * @generated */
public class ExtendedWord extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ExtendedWord.class);
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
  protected ExtendedWord() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ExtendedWord(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ExtendedWord(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ExtendedWord(JCas jcas, int begin, int end) {
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
  //* Feature: word

  /** getter for word - gets 
   * @generated */
  public Word getWord() {
    if (ExtendedWord_Type.featOkTst && ((ExtendedWord_Type)jcasType).casFeat_word == null)
      jcasType.jcas.throwFeatMissing("word", "ru.misis.asu.nlp.ExtendedWord");
    return (Word)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ExtendedWord_Type)jcasType).casFeatCode_word)));}
    
  /** setter for word - sets  
   * @generated */
  public void setWord(Word v) {
    if (ExtendedWord_Type.featOkTst && ((ExtendedWord_Type)jcasType).casFeat_word == null)
      jcasType.jcas.throwFeatMissing("word", "ru.misis.asu.nlp.ExtendedWord");
    jcasType.ll_cas.ll_setRefValue(addr, ((ExtendedWord_Type)jcasType).casFeatCode_word, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: preposition

  /** getter for preposition - gets 
   * @generated */
  public Preposition getPreposition() {
    if (ExtendedWord_Type.featOkTst && ((ExtendedWord_Type)jcasType).casFeat_preposition == null)
      jcasType.jcas.throwFeatMissing("preposition", "ru.misis.asu.nlp.ExtendedWord");
    return (Preposition)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ExtendedWord_Type)jcasType).casFeatCode_preposition)));}
    
  /** setter for preposition - sets  
   * @generated */
  public void setPreposition(Preposition v) {
    if (ExtendedWord_Type.featOkTst && ((ExtendedWord_Type)jcasType).casFeat_preposition == null)
      jcasType.jcas.throwFeatMissing("preposition", "ru.misis.asu.nlp.ExtendedWord");
    jcasType.ll_cas.ll_setRefValue(addr, ((ExtendedWord_Type)jcasType).casFeatCode_preposition, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: negativeParticle

  /** getter for negativeParticle - gets 
   * @generated */
  public NegativeParticle getNegativeParticle() {
    if (ExtendedWord_Type.featOkTst && ((ExtendedWord_Type)jcasType).casFeat_negativeParticle == null)
      jcasType.jcas.throwFeatMissing("negativeParticle", "ru.misis.asu.nlp.ExtendedWord");
    return (NegativeParticle)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ExtendedWord_Type)jcasType).casFeatCode_negativeParticle)));}
    
  /** setter for negativeParticle - sets  
   * @generated */
  public void setNegativeParticle(NegativeParticle v) {
    if (ExtendedWord_Type.featOkTst && ((ExtendedWord_Type)jcasType).casFeat_negativeParticle == null)
      jcasType.jcas.throwFeatMissing("negativeParticle", "ru.misis.asu.nlp.ExtendedWord");
    jcasType.ll_cas.ll_setRefValue(addr, ((ExtendedWord_Type)jcasType).casFeatCode_negativeParticle, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: inclinationParticle

  /** getter for inclinationParticle - gets 
   * @generated */
  public InclinationParticle getInclinationParticle() {
    if (ExtendedWord_Type.featOkTst && ((ExtendedWord_Type)jcasType).casFeat_inclinationParticle == null)
      jcasType.jcas.throwFeatMissing("inclinationParticle", "ru.misis.asu.nlp.ExtendedWord");
    return (InclinationParticle)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ExtendedWord_Type)jcasType).casFeatCode_inclinationParticle)));}
    
  /** setter for inclinationParticle - sets  
   * @generated */
  public void setInclinationParticle(InclinationParticle v) {
    if (ExtendedWord_Type.featOkTst && ((ExtendedWord_Type)jcasType).casFeat_inclinationParticle == null)
      jcasType.jcas.throwFeatMissing("inclinationParticle", "ru.misis.asu.nlp.ExtendedWord");
    jcasType.ll_cas.ll_setRefValue(addr, ((ExtendedWord_Type)jcasType).casFeatCode_inclinationParticle, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    