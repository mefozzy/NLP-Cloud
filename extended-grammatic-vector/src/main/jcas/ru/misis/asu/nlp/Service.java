

/* First created by JCasGen Sun Jun 09 00:35:52 MSK 2013 */
package ru.misis.asu.nlp;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import ru.misis.asu.nlp.morphoanalysis.types.Word;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.StringArray;


/** 
 * Updated by JCasGen Sun Jun 09 00:35:52 MSK 2013
 * XML source: D:/Java/workspace2/extended-grammatic-vector/src/main/resources/typeSystemDescriptor.xml
 * @generated */
public class Service extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Service.class);
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
  protected Service() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Service(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Service(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Service(JCas jcas, int begin, int end) {
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
    if (Service_Type.featOkTst && ((Service_Type)jcasType).casFeat_word == null)
      jcasType.jcas.throwFeatMissing("word", "ru.misis.asu.nlp.Service");
    return (Word)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Service_Type)jcasType).casFeatCode_word)));}
    
  /** setter for word - sets  
   * @generated */
  public void setWord(Word v) {
    if (Service_Type.featOkTst && ((Service_Type)jcasType).casFeat_word == null)
      jcasType.jcas.throwFeatMissing("word", "ru.misis.asu.nlp.Service");
    jcasType.ll_cas.ll_setRefValue(addr, ((Service_Type)jcasType).casFeatCode_word, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: grammemes

  /** getter for grammemes - gets 
   * @generated */
  public StringArray getGrammemes() {
    if (Service_Type.featOkTst && ((Service_Type)jcasType).casFeat_grammemes == null)
      jcasType.jcas.throwFeatMissing("grammemes", "ru.misis.asu.nlp.Service");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Service_Type)jcasType).casFeatCode_grammemes)));}
    
  /** setter for grammemes - sets  
   * @generated */
  public void setGrammemes(StringArray v) {
    if (Service_Type.featOkTst && ((Service_Type)jcasType).casFeat_grammemes == null)
      jcasType.jcas.throwFeatMissing("grammemes", "ru.misis.asu.nlp.Service");
    jcasType.ll_cas.ll_setRefValue(addr, ((Service_Type)jcasType).casFeatCode_grammemes, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for grammemes - gets an indexed value - 
   * @generated */
  public String getGrammemes(int i) {
    if (Service_Type.featOkTst && ((Service_Type)jcasType).casFeat_grammemes == null)
      jcasType.jcas.throwFeatMissing("grammemes", "ru.misis.asu.nlp.Service");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Service_Type)jcasType).casFeatCode_grammemes), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Service_Type)jcasType).casFeatCode_grammemes), i);}

  /** indexed setter for grammemes - sets an indexed value - 
   * @generated */
  public void setGrammemes(int i, String v) { 
    if (Service_Type.featOkTst && ((Service_Type)jcasType).casFeat_grammemes == null)
      jcasType.jcas.throwFeatMissing("grammemes", "ru.misis.asu.nlp.Service");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Service_Type)jcasType).casFeatCode_grammemes), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Service_Type)jcasType).casFeatCode_grammemes), i, v);}
  }

    