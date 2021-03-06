

/* First created by JCasGen Tue Mar 26 13:55:53 SAMT 2013 */
package ru.misis.asu.nlp.tokenization.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Tue Mar 26 13:55:53 SAMT 2013
 * XML source: /home/fsqcds/idea-projects/UIMA-Ext/UIMA.Ext.Tokenizer/src/main/resources/ru/kfu/cll/uima/tokenizer/jflex-tokenizer-ts.xml
 * @generated */
public class ComplexWord extends Token {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ComplexWord.class);
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
  protected ComplexWord() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ComplexWord(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ComplexWord(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ComplexWord(JCas jcas, int begin, int end) {
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
  //* Feature: Left

  /** getter for Left - gets 
   * @generated */
  public String getLeft() {
    if (ComplexWord_Type.featOkTst && ((ComplexWord_Type)jcasType).casFeat_Left == null)
      jcasType.jcas.throwFeatMissing("Left", "ru.kfu.cll.uima.tokenizer.types.ComplexWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ComplexWord_Type)jcasType).casFeatCode_Left);}
    
  /** setter for Left - sets  
   * @generated */
  public void setLeft(String v) {
    if (ComplexWord_Type.featOkTst && ((ComplexWord_Type)jcasType).casFeat_Left == null)
      jcasType.jcas.throwFeatMissing("Left", "ru.kfu.cll.uima.tokenizer.types.ComplexWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((ComplexWord_Type)jcasType).casFeatCode_Left, v);}    
   
    
  //*--------------*
  //* Feature: Right

  /** getter for Right - gets 
   * @generated */
  public String getRight() {
    if (ComplexWord_Type.featOkTst && ((ComplexWord_Type)jcasType).casFeat_Right == null)
      jcasType.jcas.throwFeatMissing("Right", "ru.kfu.cll.uima.tokenizer.types.ComplexWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ComplexWord_Type)jcasType).casFeatCode_Right);}
    
  /** setter for Right - sets  
   * @generated */
  public void setRight(String v) {
    if (ComplexWord_Type.featOkTst && ((ComplexWord_Type)jcasType).casFeat_Right == null)
      jcasType.jcas.throwFeatMissing("Right", "ru.kfu.cll.uima.tokenizer.types.ComplexWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((ComplexWord_Type)jcasType).casFeatCode_Right, v);}    
  }

    