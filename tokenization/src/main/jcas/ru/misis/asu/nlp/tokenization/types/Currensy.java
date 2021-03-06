

/* First created by JCasGen Tue Mar 26 13:55:53 SAMT 2013 */
package ru.misis.asu.nlp.tokenization.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Tue Mar 26 13:55:53 SAMT 2013
 * XML source: /home/fsqcds/idea-projects/UIMA-Ext/UIMA.Ext.Tokenizer/src/main/resources/ru/kfu/cll/uima/tokenizer/jflex-tokenizer-ts.xml
 * @generated */
public class Currensy extends Token {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Currensy.class);
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
  protected Currensy() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Currensy(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Currensy(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Currensy(JCas jcas, int begin, int end) {
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
  //* Feature: Value

  /** getter for Value - gets 
   * @generated */
  public String getValue() {
    if (Currensy_Type.featOkTst && ((Currensy_Type)jcasType).casFeat_Value == null)
      jcasType.jcas.throwFeatMissing("Value", "ru.kfu.cll.uima.tokenizer.types.Currensy");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Currensy_Type)jcasType).casFeatCode_Value);}
    
  /** setter for Value - sets  
   * @generated */
  public void setValue(String v) {
    if (Currensy_Type.featOkTst && ((Currensy_Type)jcasType).casFeat_Value == null)
      jcasType.jcas.throwFeatMissing("Value", "ru.kfu.cll.uima.tokenizer.types.Currensy");
    jcasType.ll_cas.ll_setStringValue(addr, ((Currensy_Type)jcasType).casFeatCode_Value, v);}    
   
    
  //*--------------*
  //* Feature: CurrensySymbol

  /** getter for CurrensySymbol - gets 
   * @generated */
  public String getCurrensySymbol() {
    if (Currensy_Type.featOkTst && ((Currensy_Type)jcasType).casFeat_CurrensySymbol == null)
      jcasType.jcas.throwFeatMissing("CurrensySymbol", "ru.kfu.cll.uima.tokenizer.types.Currensy");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Currensy_Type)jcasType).casFeatCode_CurrensySymbol);}
    
  /** setter for CurrensySymbol - sets  
   * @generated */
  public void setCurrensySymbol(String v) {
    if (Currensy_Type.featOkTst && ((Currensy_Type)jcasType).casFeat_CurrensySymbol == null)
      jcasType.jcas.throwFeatMissing("CurrensySymbol", "ru.kfu.cll.uima.tokenizer.types.Currensy");
    jcasType.ll_cas.ll_setStringValue(addr, ((Currensy_Type)jcasType).casFeatCode_CurrensySymbol, v);}    
  }

    