

/* First created by JCasGen Sun Jun 02 19:43:30 MSK 2013 */
package ru.misis.asu.nlp.morphoanalysis.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.LongArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.StringArray;


/** 
 * Updated by JCasGen Sun Jun 09 00:35:52 MSK 2013
 * XML source: D:/Java/workspace2/extended-grammatic-vector/src/main/resources/typeSystemDescriptor.xml
 * @generated */
public class Wordform extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Wordform.class);
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
  protected Wordform() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Wordform(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Wordform(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Wordform(JCas jcas, int begin, int end) {
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
  //* Feature: pos

  /** getter for pos - gets 
   * @generated */
  public String getPos() {
    if (Wordform_Type.featOkTst && ((Wordform_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Wordform_Type)jcasType).casFeatCode_pos);}
    
  /** setter for pos - sets  
   * @generated */
  public void setPos(String v) {
    if (Wordform_Type.featOkTst && ((Wordform_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    jcasType.ll_cas.ll_setStringValue(addr, ((Wordform_Type)jcasType).casFeatCode_pos, v);}    
   
    
  //*--------------*
  //* Feature: lemma

  /** getter for lemma - gets 
   * @generated */
  public String getLemma() {
    if (Wordform_Type.featOkTst && ((Wordform_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Wordform_Type)jcasType).casFeatCode_lemma);}
    
  /** setter for lemma - sets  
   * @generated */
  public void setLemma(String v) {
    if (Wordform_Type.featOkTst && ((Wordform_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    jcasType.ll_cas.ll_setStringValue(addr, ((Wordform_Type)jcasType).casFeatCode_lemma, v);}    
   
    
  //*--------------*
  //* Feature: lemmaId

  /** getter for lemmaId - gets 
   * @generated */
  public int getLemmaId() {
    if (Wordform_Type.featOkTst && ((Wordform_Type)jcasType).casFeat_lemmaId == null)
      jcasType.jcas.throwFeatMissing("lemmaId", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Wordform_Type)jcasType).casFeatCode_lemmaId);}
    
  /** setter for lemmaId - sets  
   * @generated */
  public void setLemmaId(int v) {
    if (Wordform_Type.featOkTst && ((Wordform_Type)jcasType).casFeat_lemmaId == null)
      jcasType.jcas.throwFeatMissing("lemmaId", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    jcasType.ll_cas.ll_setIntValue(addr, ((Wordform_Type)jcasType).casFeatCode_lemmaId, v);}    
   
    
  //*--------------*
  //* Feature: grammems

  /** getter for grammems - gets 
   * @generated */
  public StringArray getGrammems() {
    if (Wordform_Type.featOkTst && ((Wordform_Type)jcasType).casFeat_grammems == null)
      jcasType.jcas.throwFeatMissing("grammems", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Wordform_Type)jcasType).casFeatCode_grammems)));}
    
  /** setter for grammems - sets  
   * @generated */
  public void setGrammems(StringArray v) {
    if (Wordform_Type.featOkTst && ((Wordform_Type)jcasType).casFeat_grammems == null)
      jcasType.jcas.throwFeatMissing("grammems", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    jcasType.ll_cas.ll_setRefValue(addr, ((Wordform_Type)jcasType).casFeatCode_grammems, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for grammems - gets an indexed value - 
   * @generated */
  public String getGrammems(int i) {
    if (Wordform_Type.featOkTst && ((Wordform_Type)jcasType).casFeat_grammems == null)
      jcasType.jcas.throwFeatMissing("grammems", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Wordform_Type)jcasType).casFeatCode_grammems), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Wordform_Type)jcasType).casFeatCode_grammems), i);}

  /** indexed setter for grammems - sets an indexed value - 
   * @generated */
  public void setGrammems(int i, String v) { 
    if (Wordform_Type.featOkTst && ((Wordform_Type)jcasType).casFeat_grammems == null)
      jcasType.jcas.throwFeatMissing("grammems", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Wordform_Type)jcasType).casFeatCode_grammems), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Wordform_Type)jcasType).casFeatCode_grammems), i, v);}
   
    
  //*--------------*
  //* Feature: grammemBits

  /** getter for grammemBits - gets 
   * @generated */
  public LongArray getGrammemBits() {
    if (Wordform_Type.featOkTst && ((Wordform_Type)jcasType).casFeat_grammemBits == null)
      jcasType.jcas.throwFeatMissing("grammemBits", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    return (LongArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Wordform_Type)jcasType).casFeatCode_grammemBits)));}
    
  /** setter for grammemBits - sets  
   * @generated */
  public void setGrammemBits(LongArray v) {
    if (Wordform_Type.featOkTst && ((Wordform_Type)jcasType).casFeat_grammemBits == null)
      jcasType.jcas.throwFeatMissing("grammemBits", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    jcasType.ll_cas.ll_setRefValue(addr, ((Wordform_Type)jcasType).casFeatCode_grammemBits, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for grammemBits - gets an indexed value - 
   * @generated */
  public long getGrammemBits(int i) {
    if (Wordform_Type.featOkTst && ((Wordform_Type)jcasType).casFeat_grammemBits == null)
      jcasType.jcas.throwFeatMissing("grammemBits", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Wordform_Type)jcasType).casFeatCode_grammemBits), i);
    return jcasType.ll_cas.ll_getLongArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Wordform_Type)jcasType).casFeatCode_grammemBits), i);}

  /** indexed setter for grammemBits - sets an indexed value - 
   * @generated */
  public void setGrammemBits(int i, long v) { 
    if (Wordform_Type.featOkTst && ((Wordform_Type)jcasType).casFeat_grammemBits == null)
      jcasType.jcas.throwFeatMissing("grammemBits", "ru.misis.asu.nlp.morphoanalysis.types.Wordform");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Wordform_Type)jcasType).casFeatCode_grammemBits), i);
    jcasType.ll_cas.ll_setLongArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Wordform_Type)jcasType).casFeatCode_grammemBits), i, v);}
  }

    