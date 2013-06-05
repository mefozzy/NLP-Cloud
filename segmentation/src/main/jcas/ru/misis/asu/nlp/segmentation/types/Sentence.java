

/* First created by JCasGen Sat Jun 01 21:28:54 MSK 2013 */
package ru.misis.asu.nlp.segmentation.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import ru.misis.asu.nlp.tokenization.types.PM;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sat Jun 01 21:28:54 MSK 2013
 * XML source: C:/Users/Valter/new_uima/UIMA-Ext/segmentation/src/main/resources/uima.xml/segmentation-ts.xml
 * @generated */
public class Sentence extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Sentence.class);
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
  protected Sentence() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Sentence(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Sentence(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Sentence(JCas jcas, int begin, int end) {
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
  //* Feature: eos

  /** getter for eos - gets 
   * @generated */
  public PM getEos() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_eos == null)
      jcasType.jcas.throwFeatMissing("eos", "ru.misis.asu.nlp.segmentation.types.Sentence");
    return (PM)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_eos)));}
    
  /** setter for eos - sets  
   * @generated */
  public void setEos(PM v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_eos == null)
      jcasType.jcas.throwFeatMissing("eos", "ru.misis.asu.nlp.segmentation.types.Sentence");
    jcasType.ll_cas.ll_setRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_eos, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    